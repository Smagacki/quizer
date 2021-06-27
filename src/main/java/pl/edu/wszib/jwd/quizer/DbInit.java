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
                {"1", "What is the correct syntax for the command to print \"Hello World\" text to the console?", "Java"},
                {"2", "What type of data is used to store text?", "Java"},
                {"3", "What is the correct initialization of the variable to the value 5?", "Java"},
                {"4", "What method is used to get the length of the text?", "Java"},
                {"5", "What operator is used to compare two values?", "Java"},
                {"6", "What command calls a method from a parent class while in a child class?", "Java"},
                {"7", "How the 'continue' keyword works?", "Java"},
                {"8", "What function can sum up all the values \u200B\u200Bin a given column??", "SQL"},
                {"9", "What keyword is used to exclude duplicates from the SQL query results?", "SQL"},
                {"10", "What symbol means to select data from all columns of the table?", "SQL"},
                {"11", "What is the default constructor?", "Java"},
                {"12", "Can a class implement multiple interfaces?", "Java"},
                {"13", "What is the Cartesian product?", "SQL"},
                {"14", "What function can count the number of rows as a result of the SQL query?", "SQL"},
                {"15", "How to select only the grouped rows that meet the count (*)> 3 criterion?", "SQL"},
                {"16", "How a class can extend several interfaces?", "Java"},
                {"17", "When the garbage collector knows it can delete an instance of an object?", "Java"},
                {"18", "Why database indexes are used?", "SQL"},
                {"19", "How to check if a value in a column of a table is not null?", "SQL"},
                {"20", "What is the difference between UNION and UNION ALL?", "SQL"},
                {"21", "JavaScript declaration: var x = true; causes the variable x to be of type", "JavaScript"},
                {"22", "A JavaScript event in response to a single click on any page element is called", "JavaScript"},
                {"23", "In the JavaScript language, variables can be declared", "JavaScript"},
                {"24", "In JavaScript, a correctly declared variable is", "JavaScript"},
                {"25", "A JavaScript comment begins with a character or characters", "JavaScript"},
                {"26", "Document.getElementById(id) does the job", "JavaScript"},
                {"27", "To check whether a number is in the range (100; 200>, write", "JavaScript"}
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
                {"7", "1", "program execution goes back to the parent method", "N"},
                {"7", "2", "automatically advances to the next iteration in the for loop", "Y"},
                {"7", "3", "breaks loop execution and continues executing further code", "N"},
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
                {"11", "1", "a constructor defined with the 'default' keyword", "N"},
                {"11", "2", "it is only created when it is explicitly defined in your code", "N"},
                {"11", "3", "it is created if no constructor is explicitly defined in the class", "Y"},
                {"12", "1", "yes", "Y"},
                {"12", "2", "no", "N"},
                {"12", "3", "only if the interfaces extend one common interface", "N"},
                {"13", "1", "returns the product of all the numeric values of table 1 and table 2", "N"},
                {"13", "2", "combination of table 1 rows with all rows of table 2", "Y"},
                {"13", "3", "combination of table 1 rows with the rows of table 2 by primary keys", "N"},
                {"14", "1", "length", "N"},
                {"14", "2", "size", "N"},
                {"14", "3", "count", "Y"},
                {"15", "1", "using the WHERE clause", "N"},
                {"15", "2", "you can use DISTINCT three times", "N"},
                {"15", "3", "using the HAVING clause", "Y"},
                {"16", "1", "by specifying several interfaces after a comma for the word extends", "N"},
                {"16", "2", "only by reusing extends in inheriting classes", "N"},
                {"16", "3", "by specifying several interfaces after a comma for the word implements", "Y"},
                {"17", "1", "when there are no longer any references to the object", "Y"},
                {"17", "2", "when we call the finalize () method on the object", "N"},
                {"18", "1", "to sort rows to speed up the execution of functions on data in the WHERE clause", "N"},
                {"18", "2", "to speed up searching for rows with large amounts of data", "Y"},
                {"18", "3", "to keep the rows unique - duplicate records will not be displayed", "N"},
                {"19", "1", "column != NULL", "N"},
                {"19", "2", "column <> NULL", "N"},
                {"19", "3", "column IS NOT NULL", "Y"},
                {"20", "1", "there is no difference between them; the word ALL is optional depending on the database type", "N"},
                {"20", "2", "UNION skips rows where NULL values appear, and UNION ALL display those rows", "N"},
                {"20", "3", "UNION ignores duplicates in the result, and UNION ALL returns duplicate rows", "Y"},
                {"21", "1", "numerical", "N"},
                {"21", "2", "logical", "Y"},
                {"21", "3", "enumerated", "N"},
                {"21", "4", "text (string)", "N"},
                {"22", "1", "onDblClick", "N"},
                {"22", "2", "onLoad", "N"},
                {"22", "3", "onClick", "Y"},
                {"22", "4", "onKeyDown", "N"},
                {"23", "1", "only at the beginning of the script", "N"},
                {"23", "2", "always followed by a $ sign", "N"},
                {"23", "3", "when the variable was first used", "Y"},
                {"23", "4", "only if we give the type of the variable and its name", "N"},
                {"24", "1", "#name", "N"},
                {"24", "2", "name%", "N"},
                {"24", "3", "name2", "Y"},
                {"25", "1", "#", "N"},
                {"25", "2", "<?", "N"},
                {"25", "3", "//", "Y"},
                {"25", "4", "<!--", "N"},
                {"26", "1", "insert a text with the content \"id\" on the website", "N"},
                {"26", "2", "return a reference to the first HTML element with the given id", "Y"},
                {"26", "3", "get the data from the form field and insert it into the id variable", "N"},
                {"27", "1", "if (number > 100 && number <= 200)", "Y"},
                {"27", "2", "if (number < 100 || number >= 200)", "N"},
                {"27", "3", "if (number < 100 && number <= 200)", "N"},
                {"27", "4", "if (number > 100 || number <= 200)", "N"}
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
