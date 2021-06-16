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

import java.util.List;

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
        return "new_question";
    }

    @PostMapping("/add-question")
    public String addQuestion(@ModelAttribute Question question) {
        questionDao.save(question);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            String answerText = question.getAnswers().get(i).getAnswerText();
            answerDao.save(new Answer(question, i, answerText, false));
        }
        return "redirect:/new_question";
    }
}
