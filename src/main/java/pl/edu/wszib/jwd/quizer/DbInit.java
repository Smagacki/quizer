package pl.edu.wszib.jwd.quizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.jwd.quizer.dao.AnswerDao;
import pl.edu.wszib.jwd.quizer.dao.QuestionDao;
import pl.edu.wszib.jwd.quizer.model.Answer;
import pl.edu.wszib.jwd.quizer.model.Question;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DbInit {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @PostConstruct
    private void saveInitialData() {
        String[][] questionArray = {
                {"Jaka jest prawidłowa składnia polecenia wypisującego na konsoli tekst \"Hello World\"?", "Java"},
                {"Jaki typ danych służy do przechowywania tekstu?", "Java"},
                {"Jaka jest prawidłowa inicjalizacja zmiennej na wartość 5?", "Java"},
                {"Jaka metoda służy do pobrania długości tekstu?", "Java"},
                {"Jaki operator służy do porównania dwóch wartości?", "Java"}
        };

        for (String[] s : questionArray) {
            questionDao.save(new Question(s[0], s[1]));
        }

        String[][] answerArray = {
                {"echo(\"Hello World\");",
                        "System.out.println(\"Hello World\");",
                        "Console.WriteLine(\"Hello World\");",
                        "print(\"Hello World\");"},
                {"string",
                        "String",
                        "myString",
                        "Txt"},
                {"num x = 5",
                        "int x = 5",
                        "integer x = 5",
                        "x = 5"},
                {"length()",
                        "len()",
                        "getLength()",
                        "getSize()"},
                {"=",
                        "<>",
                        "><",
                        "=="}
        };

        List<Question> allQuestions = (List<Question>) questionDao.findAll();
        for (int i = 0; i < questionArray.length; i++) {
            Question question = allQuestions.get(i);
            for (int j = 0; j < answerArray[i].length; j++) {
                answerDao.save(new Answer(question, answerArray[i][j]));
            }
        }
    }
}
