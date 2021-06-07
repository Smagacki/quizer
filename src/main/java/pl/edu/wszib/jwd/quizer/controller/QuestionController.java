package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.jwd.quizer.dao.AnswerDao;
import pl.edu.wszib.jwd.quizer.dao.QuestionDao;
import pl.edu.wszib.jwd.quizer.dao.UserAnswerDao;
import pl.edu.wszib.jwd.quizer.model.Answer;
import pl.edu.wszib.jwd.quizer.model.Question;
import pl.edu.wszib.jwd.quizer.model.UserAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class
QuestionController {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    AnswerDao answerDao;

    @Autowired
    UserAnswerDao userAnswerDao;

    private final int NUMBER_OF_QUESTIONS = 20;
    private Long questionID;
    private int questionNumber = 1;
    String redirectUrl;

    public QuestionController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @GetMapping({"/questions/{questionNumber}", "/questions/{questionNumber}/{answerId}", "/questions"})
    public String getAll(@RequestParam(required = false) Long questionNumber,
                         @RequestParam(required = false) Long answerId,
                         Model model) {

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
        userAnswerDao.save(new UserAnswer(1L, questionID, 1L));

        if (questionNumber < NUMBER_OF_QUESTIONS) {
            questionNumber++;
            redirectUrl = "/questions?questionNumber=" + questionNumber;
        } else {
            redirectUrl = "/";
        }

        return "redirect:" + redirectUrl;
    }
}
