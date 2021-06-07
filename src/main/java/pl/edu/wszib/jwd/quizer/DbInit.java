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
                {"Jaki operator służy do porównania dwóch wartości?", "Java"},
                {"Jakie polecenie wywołuje metodę z klasy nadrzędnej, będąc w klasie podrzędnej?", "Java"},
                {"Jak działa słowo kluczowe continue?", "Java"},
                {"Jaką funkcją można zsumować wszystkie wartości w danej kolumnie?", "SQL"},
                {"Jakie słowo kluczowe służy wykluczeniu duplikatów z wyników zapytania SQL?", "SQL"},
                {"Jaki symbol oznacza wybranie danych z wszystkich kolumn tabeli?", "SQL"},
                {"Co to jest konstruktor domyślny?", "Java"},
                {"Czy klasa może implementować kilka interfejsów?", "Java"},
                {"Co to jest iloczyn kartezjański?", "SQL"},
                {"Jaką funkcją można zliczyć ilość wierszy w rezultacie zapytania SQL?", "SQL"},
                {"Jak wybrać tylko te zgrupowane wiersze które spełniają kryterium count(*) > 3 ?", "SQL"},
                {"Jak klasa może rozszerzać kilka interfejsów?", "Java"},
                {"Kiedy garbage collector wie że może usunąć instancję obiektu?", "Java"},
                {"Po co stosuje się indeksy na bazie danych?", "SQL"},
                {"Jak sprawdzić czy dana wartość jest różna od null?", "SQL"},
                {"Jaka jest różnica między UNION a UNION ALL?", "SQL"}
        };

        for (String[] s : questionArray) {
            questionDao.save(new Question(s[0], s[1]));
        }

        String[][] answerArray = {
                {"1", "echo(\"Hello World\");", "N"},
                {"1", "System.out.println(\"Hello World\");", "Y"},
                {"1", "Console.WriteLine(\"Hello World\");", "N"},
                {"1", "print(\"Hello World\");", "N"},
                {"2", "string", "N"},
                {"2", "String", "Y"},
                {"2", "myString", "N"},
                {"2", "Txt", "N"},
                {"3", "num x = 5", "N"},
                {"3", "int x = 5", "Y"},
                {"3", "integer x = 5", "N"},
                {"3", "x = 5", "N"}

//            "length()", "len()", "getLength()", "getSize()"
//        },
//        {
//            "=", "<>", "><", "=="
//        },
//        {
//            "super.nameMethod()", "self.nameMethod()", "this.selfMethod()"
//        },
//        {
//            "wykonanie programu wraca do metody nadrzędnej",
//                    "automatycznie przechodzi do kolejnej iteracji w pętli for",
//                    "przerywa wykonywanie pętli i kontynuuje wykonywanie dalszego kodu"
//        },
//        {
//            "sum", "sumAll", "add", "addAll"
//        },
//        {
//            "except", "intersect", "single", "distinct"
//        },
//        {
//            "*", "?", "all"
//        },
//        {
//            "konstruktor zdefiniowany ze słowem kluczowym default",
//                    "jest tworzony tylko wtedy kiedy zostanie jawnie zdefiniowany w kodzie",
//                    "jest tworzony jeśli nie zdefiniowano jawnie konstruktora w klasie"
//        },
//        {
//            "tak", "nie", "tylko wtedy jeśli interfejsy roszerzają jeden wspólny interfejs"
//        },
//        {
//            "zwraca iloczyn wszystkich wartości liczbowych tabeli 1 i tabeli 2",
//                    "połączenie wierszy tabeli 1 z wszystkimi wierszami tabeli 2",
//                    "połączenie wierszy tabeli 1 z wierszami tabeli 2 za pomocą kluczy głównych"
//        },
//        {
//            "length", "size", "count"
//        },
//        {
//            "Używając klauzuli HAVING", "Używając klauzuli WHERE", "Można trzykrotnie użyć DISTINCT"
//        },
//        {
//            "poprzez podanie kilku interfejsów po przecinku przy słowie extends",
//                    "tylko poprzez kolejne użycie extends w klasach dziedziczących",
//                    "poprzez podanie kilku interfejsów po przecinku przy słowie implements"
//        },
//        {
//            "kiedy nie ma już żadnych referencji do danego obiektu", "kiedy wywołamy metodę finalize() na obiekcie"
//        },
//        {
//            "w celu posortowania wierszy, aby przyspieszyć wykonywanie funkcji na danych w klazuli WHERE",
//                    "w celu zachowania unikalności wierszy - zduplikowane rekordy na będą wyświetlane",
//                    "w celu przyspieszenia wyszukiwania wierszy przy dużej ilości danych"
//        },
//        {
//            "column != NULL", "column <> NULL", "column IS NULL"
//        },
//        {
//            "nie ma między nimi różnicy; słowo ALL jest opcjonalne w zależności od typu bazy danych.",
//                    "UNION pomija wiersze gdzie pojawiają się wartości NULL, a UNION ALL uwzględnia te wiersze.",
//                    "UNION ignoruje duplikaty w wyniku, a UNION ALL zwraca zduplikowane wiersze."
//        }

//    }
        } ;

        List<Question> allQuestions = (List<Question>) questionDao.findAll();

        for (String[] answerRow : answerArray) {
            int i = Integer.parseInt(answerRow[0]);
            Question question = allQuestions.get(i - 1);
            boolean isCorrect = answerRow[2].equals("Y");
            answerDao.save(new Answer(question, answerRow[1], isCorrect));
        }
    }
}
