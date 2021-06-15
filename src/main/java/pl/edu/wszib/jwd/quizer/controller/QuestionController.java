package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.jwd.quizer.dao.AnswerDao;
import pl.edu.wszib.jwd.quizer.dao.QuestionDao;
import pl.edu.wszib.jwd.quizer.dao.UserAnswerDao;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.model.Answer;
import pl.edu.wszib.jwd.quizer.model.Question;
import pl.edu.wszib.jwd.quizer.model.User;
import pl.edu.wszib.jwd.quizer.model.UserAnswer;

import java.util.*;

@Controller
public class
QuestionController {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    AnswerDao answerDao;

    @Autowired
    UserAnswerDao userAnswerDao;

    @Autowired
    UserDao userDao;

    private final int NUMBER_OF_QUESTIONS = 5;
    private Long questionID;
    private int questionNumber = 1;
    List<Integer> quizQuestionIds = new ArrayList<>();

    public QuestionController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @GetMapping({"/questions/{questionNumber}", "/questions"})
    public String getAll(@RequestParam(required = false) Long questionNumber, Model model) {

        model.addAttribute("newUserAnswer", new UserAnswer());
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionCount", NUMBER_OF_QUESTIONS);

        if (questionNumber == 1) {
            quizQuestionIds.clear();
            getRandomIdList();
        }

        int i = Math.toIntExact(questionNumber);
        long id = (long) quizQuestionIds.get(i - 1);
        Optional<Question> question = questionDao.findById(id);
        if (question.isPresent()) {
            questionID = question.get().getId();
            String questionText = question.get().getQuestionText();
            List<Answer> answers = question.get().getAnswers();

            model.addAttribute("questionText", questionText);
            model.addAttribute("answers", answers);
        }

        return "questions";
    }

    @PostMapping("/add-user-answer")
    public String addAnswer(@ModelAttribute UserAnswer userAnswer) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userDao.findByEmail(username);
        Long userId = user.getId();

        userAnswerDao.save(new UserAnswer(userId, questionID, userAnswer.getAnswerNumber()));

        String redirectUrl;
        if (questionNumber < NUMBER_OF_QUESTIONS) {
            questionNumber++;
            redirectUrl = "/questions?questionNumber=" + questionNumber;
        } else {
            questionNumber = 1;
            redirectUrl = "/quiz_panel";
        }

        return "redirect:" + redirectUrl;
    }

    private void getRandomIdList() {
        Random rd = new Random();
        int questionCount = (int) questionDao.count();
        Set<Integer> questionIdSet = new HashSet<>();
        while (questionIdSet.size() < NUMBER_OF_QUESTIONS) {
            int x = rd.nextInt(questionCount) + 1;
            questionIdSet.add(x);
        }
        quizQuestionIds.addAll(questionIdSet);
    }
}
