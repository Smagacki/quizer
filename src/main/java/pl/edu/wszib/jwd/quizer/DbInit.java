package pl.edu.wszib.jwd.quizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.wszib.jwd.quizer.dao.AnswerDao;
import pl.edu.wszib.jwd.quizer.dao.QuestionDao;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.dao.UserStatDao;
import pl.edu.wszib.jwd.quizer.model.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DbInit {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserStatDao userStatDao;

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
                {"20", "Jaka jest różnica między UNION a UNION ALL?", "SQL"},
                {"21", "Deklaracja w języku JavaScript: var x=true; powoduje, że zmienna x jest typu", "JavaScript"},
                {"22", "Zdarzenie JavaScript, będące reakcją na pojedynczo kliknięty dowolny element strony, nosi nazwę", "JavaScript"},
                {"23", "W języku skryptowym JavaScript zmienne mogą być deklarowane", "JavaScript"},
                {"24", "W języku JavaScript poprawnie zadeklarowana zmienna to", "JavaScript"},
                {"25", "Komentarz w języku JavaScript rozpoczyna się od znaku lub znaków", "JavaScript"},
                {"26", "Metoda document.getElementById(id) ma za zadanie", "JavaScript"},
                {"27", "Aby sprawdzić warunek czy liczba znajduje się w przedziale (100;200>, należy zapisać", "JavaScript"}
        };

        for (String[] s : questionArray) {
            questionDao.save(new Question(s[1], s[2]));
        }

        String[][] answerArray = {
                {"1", "1", "echo(\"Hello World\");", "N"},
                {"1", "2", "System.out.println(\"Hello World\");", "Y"},
                {"1", "3", "Console.WriteLine(\"Hello World\");", "N"},
                {"1", "4", "print(\"Hello World\");", "N"},
                {"2", "1", "string", "N"},
                {"2", "2", "String", "Y"},
                {"2", "3", "myString", "N"},
                {"2", "4", "Txt", "N"},
                {"3", "1", "num x = 5", "N"},
                {"3", "2", "int x = 5", "Y"},
                {"3", "3", "integer x = 5", "N"},
                {"3", "4", "x = 5", "N"},
                {"4", "1", "length()", "Y"},
                {"4", "2", "len()", "N"},
                {"4", "3", "getLength()", "N"},
                {"4", "4", "getSize()", "N"},
                {"5", "1", "=", "N"},
                {"5", "2", "<>", "N"},
                {"5", "3", "><", "N"},
                {"5", "4", "==", "Y"},
                {"6", "1", "super.nameMethod()", "Y"},
                {"6", "2", "self.nameMethod()", "N"},
                {"6", "3", "this.selfMethod()", "N"},
                {"7", "1", "wykonanie programu wraca do metody nadrzędnej", "N"},
                {"7", "2", "automatycznie przechodzi do kolejnej iteracji w pętli for", "Y"},
                {"7", "3", "przerywa wykonywanie pętli i kontynuuje wykonywanie dalszego kodu", "N"},
                {"8", "1", "sum", "Y"},
                {"8", "2", "sumAll", "N"},
                {"8", "3", "add", "N"},
                {"8", "4", "addAll", "N"},
                {"9", "1", "except", "N"},
                {"9", "2", "intersect", "N"},
                {"9", "3", "single", "N"},
                {"9", "4", "distinct", "Y"},
                {"10", "1", "?", "N"},
                {"10", "2", "*", "Y"},
                {"10", "3", "all", "N"},
                {"11", "1", "konstruktor zdefiniowany ze słowem kluczowym default", "N"},
                {"11", "2", "jest tworzony tylko wtedy kiedy zostanie jawnie zdefiniowany w kodzie", "N"},
                {"11", "3", "jest tworzony jeśli nie zdefiniowano jawnie konstruktora w klasie", "Y"},
                {"12", "1", "tak", "Y"},
                {"12", "2", "nie", "N"},
                {"12", "3", "tylko wtedy jeśli interfejsy roszerzają jeden wspólny interfejs", "N"},
                {"13", "1", "zwraca iloczyn wszystkich wartości liczbowych tabeli 1 i tabeli 2", "N"},
                {"13", "2", "połączenie wierszy tabeli 1 z wszystkimi wierszami tabeli 2", "Y"},
                {"13", "3", "połączenie wierszy tabeli 1 z wierszami tabeli 2 za pomocą kluczy głównych", "N"},
                {"14", "1", "length", "N"},
                {"14", "2", "size", "N"},
                {"14", "3", "count", "Y"},
                {"15", "1", "używając klauzuli WHERE", "N"},
                {"15", "2", "można trzykrotnie użyć DISTINCT", "N"},
                {"15", "3", "używając klauzuli HAVING", "Y"},
                {"16", "1", "poprzez podanie kilku interfejsów po przecinku przy słowie extends", "N"},
                {"16", "2", "tylko poprzez kolejne użycie extends w klasach dziedziczących", "N"},
                {"16", "3", "poprzez podanie kilku interfejsów po przecinku przy słowie implements", "Y"},
                {"17", "1", "kiedy nie ma już żadnych referencji do danego obiektu", "Y"},
                {"17", "2", "kiedy wywołamy metodę finalize() na obiekcie", "N"},
                {"18", "1", "w celu posortowania wierszy, aby przyspieszyć wykonywanie funkcji na danych w klazuli WHERE", "N"},
                {"18", "2", "w celu przyspieszenia wyszukiwania wierszy przy dużej ilości danych", "Y"},
                {"18", "3", "w celu zachowania unikalności wierszy - zduplikowane rekordy na będą wyświetlane", "N"},
                {"19", "1", "column != NULL", "N"},
                {"19", "2", "column <> NULL", "N"},
                {"19", "3", "column IS NOT NULL", "Y"},
                {"20", "1", "nie ma między nimi różnicy; słowo ALL jest opcjonalne w zależności od typu bazy danych", "N"},
                {"20", "2", "UNION pomija wiersze gdzie pojawiają się wartości NULL, a UNION ALL uwzględnia te wiersze", "N"},
                {"20", "3", "UNION ignoruje duplikaty w wyniku, a UNION ALL zwraca zduplikowane wiersze", "Y"},
                {"21", "1", "liczbowego", "N"},
                {"21", "2", "logicznego", "Y"},
                {"21", "3", "wyliczeniowego", "N"},
                {"21", "4", "tekstowego (łańcuch)", "N"},
                {"22", "1", "onDblClick", "N"},
                {"22", "2", "onLoad", "N"},
                {"22", "3", "onClick", "Y"},
                {"22", "4", "onKeyDown", "N"},
                {"23", "1", "tylko na początku skryptu", "N"},
                {"23", "2", "zawsze z poprzedzającym nazwę znakiem $", "N"},
                {"23", "3", "w momencie pierwszego użycia zmiennej", "Y"},
                {"23", "4", "tylko jeśli podamy typ zmiennej i jej nazwę", "N"},
                {"24", "1", "#imie", "N"},
                {"24", "2", "imię%", "N"},
                {"24", "3", "imię2", "N"},
                {"24", "4", "imie2", "Y"},
                {"25", "1", "#", "N"},
                {"25", "2", "<?", "N"},
                {"25", "3", "//", "Y"},
                {"25", "4", "<!--", "N"},
                {"26", "1", "wstawić tekst o treści ’id’ na stronie WWW", "N"},
                {"26", "2", "zwrócić odniesienie do pierwszego elementu HTML o podanym id", "Y"},
                {"26", "3", "pobrać dane z pola formularza i wstawić je do zmiennej id/", "N"},
                {"26", "4", "sprawdzić poprawność formularza o identyfikatorze id", "N"},
                {"27", "1", "if (liczba > 100 && liczba <= 200)", "Y"},
                {"27", "2", "if (liczba < 100 || liczba >= 200)", "N"},
                {"27", "3", "if (liczba < 100 && liczba <= 200)", "N"},
                {"27", "4", "if (liczba > 100 || liczba <= 200)", "N"}
        };

        List<Question> allQuestions = (List<Question>) questionDao.findAll();

        for (String[] answerRow : answerArray) {
            int i = Integer.parseInt(answerRow[0]);
            Question question = allQuestions.get(i - 1);
            int questionNumber = Integer.parseInt(answerRow[1]);
            boolean isCorrect = answerRow[3].equals("Y");
            answerDao.save(new Answer(question, questionNumber, answerRow[2], isCorrect));
        }

        //user do testow
//        User user = new User("admin@gmail.com", "admin2020", "admin", "admin");
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userDao.save(user);
    }
}
