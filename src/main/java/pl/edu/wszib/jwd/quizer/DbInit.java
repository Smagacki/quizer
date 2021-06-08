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
                {"1", "Jaka jest prawidłowa składnia polecenia wypisującego na konsoli tekst \"Hello World\"?", "Java"},
                {"2", "Jaki typ danych służy do przechowywania tekstu?", "Java"},
                {"3", "Jaka jest prawidłowa inicjalizacja zmiennej na wartość 5?", "Java"},
                {"4", "Jaka metoda służy do pobrania długości tekstu?", "Java"},
                {"5", "Jaki operator służy do porównania dwóch wartości?", "Java"},
                {"6", "Jakie polecenie wywołuje metodę z klasy nadrzędnej, będąc w klasie podrzędnej?", "Java"},
                {"7", "Jak działa słowo kluczowe continue?", "Java"},
                {"8", "Jaką funkcją można zsumować wszystkie wartości w danej kolumnie?", "SQL"},
                {"9", "Jakie słowo kluczowe służy wykluczeniu duplikatów z wyników zapytania SQL?", "SQL"},
                {"10", "Jaki symbol oznacza wybranie danych z wszystkich kolumn tabeli?", "SQL"},
                {"11", "Co to jest konstruktor domyślny?", "Java"},
                {"12", "Czy klasa może implementować kilka interfejsów?", "Java"},
                {"13", "Co to jest iloczyn kartezjański?", "SQL"},
                {"14", "Jaką funkcją można zliczyć ilość wierszy w rezultacie zapytania SQL?", "SQL"},
                {"15", "Jak wybrać tylko te zgrupowane wiersze które spełniają kryterium count(*) > 3 ?", "SQL"},
                {"16", "Jak klasa może rozszerzać kilka interfejsów?", "Java"},
                {"17", "Kiedy garbage collector wie że może usunąć instancję obiektu?", "Java"},
                {"18", "Po co stosuje się indeksy na bazie danych?", "SQL"},
                {"19", "Jak sprawdzić czy wartość w kolumnie tabeli jest różna od null?", "SQL"},
                {"20", "Jaka jest różnica między UNION a UNION ALL?", "SQL"}
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
                {"3", "x = 5", "N"},
                {"4", "length()", "Y"},
                {"4", "len()", "N"},
                {"4", "getLength()", "N"},
                {"4", "getSize()", "N"},
                {"5", "=", "N"},
                {"5", "<>", "N"},
                {"5", "><", "N"},
                {"5", "==", "T"},
                {"6", "super.nameMethod()", "Y"},
                {"6", "self.nameMethod()", "N"},
                {"6", "this.selfMethod()", "N"},
                {"7", "wykonanie programu wraca do metody nadrzędnej", "N"},
                {"7", "automatycznie przechodzi do kolejnej iteracji w pętli for", "Y"},
                {"7", "przerywa wykonywanie pętli i kontynuuje wykonywanie dalszego kodu", "N"},
                {"8", "sum", "Y"},
                {"8", "sumAll", "N"},
                {"8", "add", "N"},
                {"8", "addAll", "N"},
                {"9", "except", "N"},
                {"9", "intersect", "N"},
                {"9", "single", "N"},
                {"9", "distinct", "Y"},
                {"10", "?", "N"},
                {"10", "*", "Y"},
                {"10", "all", "N"},
                {"11", "konstruktor zdefiniowany ze słowem kluczowym default", "N"},
                {"11", "jest tworzony tylko wtedy kiedy zostanie jawnie zdefiniowany w kodzie", "N"},
                {"11", "jest tworzony jeśli nie zdefiniowano jawnie konstruktora w klasie", "Y"},
                {"12", "tak", "Y"},
                {"12", "nie", "N"},
                {"12", "tylko wtedy jeśli interfejsy roszerzają jeden wspólny interfejs", "N"},
                {"13", "zwraca iloczyn wszystkich wartości liczbowych tabeli 1 i tabeli 2", "Y"},
                {"13", "połączenie wierszy tabeli 1 z wszystkimi wierszami tabeli 2", "N"},
                {"13", "połączenie wierszy tabeli 1 z wierszami tabeli 2 za pomocą kluczy głównych", "N"},
                {"14", "length", "N"},
                {"14", "size", "N"},
                {"14", "count", "Y"},
                {"15", "używając klauzuli WHERE", "N"},
                {"15", "można trzykrotnie użyć DISTINCT", "N"},
                {"15", "używając klauzuli HAVING", "Y"},
                {"16", "poprzez podanie kilku interfejsów po przecinku przy słowie extends", "N"},
                {"16", "tylko poprzez kolejne użycie extends w klasach dziedziczących", "N"},
                {"16", "poprzez podanie kilku interfejsów po przecinku przy słowie implements", "Y"},
                {"17", "kiedy nie ma już żadnych referencji do danego obiektu", "Y"},
                {"17", "kiedy wywołamy metodę finalize() na obiekcie", "N"},
                {"18", "w celu posortowania wierszy, aby przyspieszyć wykonywanie funkcji na danych w klazuli WHERE", "N"},
                {"18", "w celu przyspieszenia wyszukiwania wierszy przy dużej ilości danych", "Y"},
                {"18", "w celu zachowania unikalności wierszy - zduplikowane rekordy na będą wyświetlane", "N"},
                {"19", "column != NULL", "N"},
                {"19", "column <> NULL", "N"},
                {"19", "column IS NOT NULL", "Y"},
                {"20", "nie ma między nimi różnicy; słowo ALL jest opcjonalne w zależności od typu bazy danych", "N"},
                {"20", "UNION pomija wiersze gdzie pojawiają się wartości NULL, a UNION ALL uwzględnia te wiersze", "N"},
                {"20", "UNION ignoruje duplikaty w wyniku, a UNION ALL zwraca zduplikowane wiersze", "N"}
        };

        List<Question> allQuestions = (List<Question>) questionDao.findAll();

        for (String[] answerRow : answerArray) {
            int i = Integer.parseInt(answerRow[0]);
            Question question = allQuestions.get(i - 1);
            boolean isCorrect = answerRow[2].equals("Y");
            answerDao.save(new Answer(question, answerRow[1], isCorrect));
        }
    }
}
