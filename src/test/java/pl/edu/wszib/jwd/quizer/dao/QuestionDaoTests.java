package pl.edu.wszib.jwd.quizer.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.edu.wszib.jwd.quizer.model.Answer;
import pl.edu.wszib.jwd.quizer.model.Question;
import pl.edu.wszib.jwd.quizer.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class QuestionDaoTests {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateQuestion() {
        Question question = new Question();

        question.setQuestionText("Question 1");
        question.setLanguage("Java");

        List<Answer> answers =  new ArrayList<>();
        answers.add(new Answer(question, 1, "answer 1", true));
        answers.add(new Answer(question, 2, "answer 2", false));
        answers.add(new Answer(question, 3, "answer 3", false));

        question.setAnswers(answers);

        Question savedQuestion = questionDao.save(question);
        Question existQuestion = entityManager.find(Question.class, savedQuestion.getId());
        assertThat(existQuestion.getId()).isEqualTo(question.getId());
    }
}
