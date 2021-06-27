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
public class QuizController {

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
    private UserStat userStat;
    private UserStatTotal userStatTotal;

    private Long questionID;
    private int questionNumber = 1;
    private int correctAnswerNumber = 0;
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

            userStatTotal = userStatTotalDao.findByUserId(user.getId());
            if (userStatTotal == null) {
                userStatTotal = new UserStatTotal(user.getId(), user.getEmail(), 0, 0, 0, 0);
            }

            updateUserStatTotal("new_quiz");
            updateUserStat("new_quiz");

            quizQuestionIds.clear();
            getRandomIdList();
        }

        if (questionNumber == NUMBER_OF_QUESTIONS) {
            model.addAttribute("button_text", "finish");
        } else {
            model.addAttribute("button_text", "next");
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
            model.addAttribute("language", question.get().getLanguage());
        }

        return "questions";
    }

    @PostMapping("/add-user-answer")
    public String addAnswer(@ModelAttribute UserAnswer userAnswer) {

        boolean answerIsCorrect = userAnswer.getAnswerNumber() == correctAnswerNumber;
        if (answerIsCorrect) {
            updateUserStatTotal("good_answer");
            updateUserStat("good_answer");
        }
        userAnswerDao.save(new UserAnswer(user.getId(), userStatTotal.getQuizCount(), questionID, userAnswer.getAnswerNumber(), answerIsCorrect));

        String redirectUrl;
        if (questionNumber < NUMBER_OF_QUESTIONS) {
            questionNumber++;
            redirectUrl = "/questions?questionNumber=" + questionNumber;
        } else {
            questionNumber = 1;
            redirectUrl = "/quiz_summary";
        }

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/quiz_summary")
    public String viewQuizSummary(Model model) {
        model.addAttribute("chartData", getChartData());
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

    private List<List<Object>> getChartData() {
        List<List<Object>> list = new ArrayList<>();

        List<Object> correctAnswers = new ArrayList<>();
        correctAnswers.add("Correct answers");
        correctAnswers.add(userStat.getCorrectAnswer());

        List<Object> wrongAnswers = new ArrayList<>();
        wrongAnswers.add("Wrong answers");
        wrongAnswers.add(userStat.getWrongAnswer());

        list.add(correctAnswers);
        list.add(wrongAnswers);

        return list;
    }

    private void updateUserStat(String action) {
        if (action.equals("new_quiz")) {
            userStat = new UserStat(user.getId(), userStatTotal.getQuizCount(), 0, NUMBER_OF_QUESTIONS);
            userStatDao.save(userStat);
        } else if (action.equals("good_answer")) {
            userStat.setCorrectAnswer(userStat.getCorrectAnswer() + 1);
            userStat.setWrongAnswer(userStat.getWrongAnswer() - 1);
        }
        userStatDao.save(userStat);
    }

    private void updateUserStatTotal(String action) {
        if (action.equals("new_quiz")) {
            userStatTotal.setQuizCount(userStatTotal.getQuizCount() + 1);
            userStatTotal.setWrongAnswerCount(userStatTotal.getWrongAnswerCount() + NUMBER_OF_QUESTIONS);
        } else if (action.equals("good_answer")) {
            userStatTotal.setCorrectAnswerCount(userStatTotal.getCorrectAnswerCount() + 1);
            userStatTotal.setWrongAnswerCount(userStatTotal.getWrongAnswerCount() - 1);
        }

        int newPercentageSuccess = userStatTotal.getCorrectAnswerCount() * 100 / (userStatTotal.getCorrectAnswerCount() + userStatTotal.getWrongAnswerCount());
        userStatTotal.setPercentageSuccess(newPercentageSuccess);

        userStatTotalDao.save(userStatTotal);
    }
}
