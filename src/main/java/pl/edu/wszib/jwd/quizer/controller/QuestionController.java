package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.jwd.quizer.CustomUserDetails;
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

    private CustomUserDetails customUserDetails;

    private final int NUMBER_OF_QUESTIONS = 5;
    private Long questionID;
    private int questionNumber = 1;
    private String redirectUrl;
    private List<Question> quizQuestions;

    public QuestionController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @GetMapping({"/questions/{questionNumber}", "/questions"})
    public String getAll(@RequestParam(required = false) Long questionNumber, Model model) {

        model.addAttribute("newUserAnswer", new UserAnswer());
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionCount", NUMBER_OF_QUESTIONS);

        Optional<Question> question = questionDao.findById(questionNumber);
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

        userAnswerDao.save(new UserAnswer(userId, questionID, 1L));

        if (questionNumber < NUMBER_OF_QUESTIONS) {
            questionNumber++;
            redirectUrl = "/questions?questionNumber=" + questionNumber;
        } else {
            redirectUrl = "/quiz_panel";
        }

        return "redirect:" + redirectUrl;
    }

//    private List<Question> getRandomQuestions() {
//        Random rd = new Random();
//        Set<Integer> questionIdSet = new HashSet<>();
//        while (questionIdSet.size() < 5) {
//            int x = rd.nextInt(20) + 1;
//            questionIdSet.add(x);
//        }
//        System.out.println(questionIdSet);
//        for (Integer i : questionIdSet) {
//            Optional<Question> question = questionDao.findById((long) i);
//            if(question.isPresent()) {
//
//            }
//        }
//        return questionIdSet;
//    }
}
