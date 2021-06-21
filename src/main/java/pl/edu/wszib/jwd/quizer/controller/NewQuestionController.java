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

import java.util.*;

@Controller
public class NewQuestionController {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    AnswerDao answerDao;

    @GetMapping("/new_question")
    public String get(Model model) {
        model.addAttribute("newQuestion", new Question());
        model.addAttribute("newAnswer", new Answer());
        return "new_question";
    }

    @PostMapping("/add-question")
    public String addQuestion(@ModelAttribute Question question) {
        question.getAnswers().get(0).setCorrect(true);
        List<Answer> userAnswers = new ArrayList<>();
        for (Answer a : question.getAnswers()) {
            if (a.getAnswerText() != null && !a.getAnswerText().isEmpty())
                userAnswers.add(a);
            else
                break;
        }
        question.setAnswers(getMixedAnswersList(userAnswers));

        questionDao.save(question);

        for (int i = 0; i < question.getAnswers().size(); i++) {
            String answerText = question.getAnswers().get(i).getAnswerText();
            boolean isCorrect = question.getAnswers().get(i).isCorrect();
            if (answerText != null && !answerText.isEmpty())
                answerDao.save(new Answer(question, i + 1, answerText, isCorrect));
            else
                break;
        }
        return "redirect:/new_question";
    }

    private List<Answer> getMixedAnswersList(List<Answer> answers) {
        Random rd = new Random();

        List<Integer> randomInt = new ArrayList<>();
        while (randomInt.size() < answers.size()) {
            int x = rd.nextInt(answers.size());
            if (!randomInt.contains(x))
                randomInt.add(x);
        }

        List<Answer> mixedList = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            mixedList.add(answers.get(randomInt.get(i)));
        }

        return mixedList;
    }
}
