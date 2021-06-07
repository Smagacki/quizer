package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.jwd.quizer.dao.AnswerDao;
import pl.edu.wszib.jwd.quizer.dao.QuestionDao;
import pl.edu.wszib.jwd.quizer.model.Answer;
import pl.edu.wszib.jwd.quizer.model.Question;

@Controller
public class NewQuestionController {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    AnswerDao answerDao;

    @GetMapping("/newQuestion")
    public String get(Model model) {
        model.addAttribute("newQuestion", new Question());
        model.addAttribute("newAnswer", new Answer());
        return "newQuestion";
    }

    @PostMapping("/add-question")
    public String addQuestion(@ModelAttribute Question question) {
        questionDao.save(question);
        for (Answer answer : question.getAnswers()) {
            answerDao.save(new Answer(question, answer.getAnswerText()));
        }
        return "redirect:/newQuestion";
    }
}
