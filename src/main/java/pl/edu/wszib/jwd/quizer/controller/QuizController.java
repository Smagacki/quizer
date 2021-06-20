package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.jwd.quizer.dao.*;
import pl.edu.wszib.jwd.quizer.model.*;
import pl.edu.wszib.jwd.quizer.service.UserService;

import java.util.*;

@Controller
public class
QuizController {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    AnswerDao answerDao;

    @Autowired
    UserAnswerDao userAnswerDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserStatDao userStatDao;

    @Autowired
    UserStatTotalDao userStatTotalDao;

    @Autowired
    UserService userService;

    private final int NUMBER_OF_QUESTIONS = 5;
    private User user;
    private Long questionID;
    private int questionNumber = 1;
    private int userQuizCount = 0;
    private int correctAnswerNumber = 0;
    private int correctAnswerCount = 0;
    private int wrongAnswerCount = 0;
    private int percentageSuccess = 0;
    private final List<Integer> quizQuestionIds = new ArrayList<>();

    public QuizController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @GetMapping({"/questions/{questionNumber}", "/questions"})
    public String getAll(@RequestParam(required = false) Long questionNumber, Model model) {

        model.addAttribute("newUserAnswer", new UserAnswer());
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionCount", NUMBER_OF_QUESTIONS);

        if (questionNumber == 1) {
            user = userService.getCurrentUser();

            List<UserStatTotal> userStat = userStatTotalDao.findByUserId(user.getId());
            if (userStat.size() == 0) {
                userQuizCount = 1;
            } else {
                userQuizCount += 1;
            }

            quizQuestionIds.clear();
            getRandomIdList();
            correctAnswerCount = wrongAnswerCount = percentageSuccess = 0;
        }

        int i = Math.toIntExact(questionNumber);
        long id = (long) quizQuestionIds.get(i - 1);
        Optional<Question> question = questionDao.findById(id);
        if (question.isPresent()) {
            questionID = question.get().getId();
            String questionText = question.get().getQuestionText();
            List<Answer> answers = question.get().getAnswers();
            setCorrectAnswerNumber(answers);

            model.addAttribute("questionText", questionText);
            model.addAttribute("answers", answers);
        }

        return "questions";
    }

    @PostMapping("/add-user-answer")
    public String addAnswer(@ModelAttribute UserAnswer userAnswer) {

        boolean answerIsCorrect = userAnswer.getAnswerNumber() == correctAnswerNumber;
        if (answerIsCorrect) {
            correctAnswerCount++;
        } else {
            wrongAnswerCount++;
        }

        userAnswerDao.save(new UserAnswer(user.getId(), userQuizCount, questionID, userAnswer.getAnswerNumber(), answerIsCorrect));

        String redirectUrl;
        if (questionNumber < NUMBER_OF_QUESTIONS) {
            questionNumber++;
            redirectUrl = "/questions?questionNumber=" + questionNumber;
        } else {
            userStatDao.save(new UserStat(user.getId(), userQuizCount, correctAnswerCount, wrongAnswerCount));
            percentageSuccess = correctAnswerCount * 100 / NUMBER_OF_QUESTIONS;

            updateUserStatTotal();

            questionNumber = 1;
            redirectUrl = "/quiz_summary";
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/quiz_summary")
    public String viewQuizSummary(Model model) {
        model.addAttribute("percentageSuccess", percentageSuccess);
        return "quiz_summary";
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

    private void setCorrectAnswerNumber(List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                correctAnswerNumber = answer.getAnswerNumber();
                break;
            }
        }
    }

    private void updateUserStatTotal() {
        List<UserStatTotal> userStat = userStatTotalDao.findByUserId(user.getId());
        if (userStat.size() == 0) {
            userStatTotalDao.save(new UserStatTotal(user.getId(), user.getEmail(), userQuizCount, correctAnswerCount, wrongAnswerCount, percentageSuccess));
        } else {
            UserStatTotal userStatTotalUpd = userStat.get(0);

            //int newQuizCount = userStatTotalUpd.getQuizCount() + 1;
            int newCorrectAnswerCount = userStatTotalUpd.getCorrectAnswerCount() + correctAnswerCount;
            int newWwrongAnswerCount = userStatTotalUpd.getWrongAnswerCount() + wrongAnswerCount;
            int newPercentageSuccess = newCorrectAnswerCount * 100 / (newCorrectAnswerCount + newWwrongAnswerCount);

            userStatTotalUpd.setQuizCount(userQuizCount);
            userStatTotalUpd.setCorrectAnswerCount(newCorrectAnswerCount);
            userStatTotalUpd.setWrongAnswerCount(newWwrongAnswerCount);
            userStatTotalUpd.setPercentageSuccess(newPercentageSuccess);

            userStatTotalDao.save(userStatTotalUpd);
        }
    }
}
