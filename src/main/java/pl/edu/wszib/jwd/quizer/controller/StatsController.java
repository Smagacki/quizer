package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.dao.UserStatDao;
import pl.edu.wszib.jwd.quizer.dao.UserStatTotalDao;
import pl.edu.wszib.jwd.quizer.model.User;
import pl.edu.wszib.jwd.quizer.model.UserResult;
import pl.edu.wszib.jwd.quizer.model.UserStatTotal;
import pl.edu.wszib.jwd.quizer.service.UserService;

import java.util.*;

@Controller
public class StatsController {

    @Autowired
    UserStatDao userStatDao;

    @Autowired
    UserStatTotalDao userStatTotalDao;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    private User user;

    @GetMapping("/user_stats")
    public String viewUsersStats(Model model) {
        user = userService.getCurrentUser();
        int correctAnswerCount = 0;
        int wrongAnswerCount = 0;

        UserStatTotal stats = userStatTotalDao.findByUserId(user.getId());

        if (stats != null) {
            correctAnswerCount = stats.getCorrectAnswerCount();
            wrongAnswerCount = stats.getWrongAnswerCount();
            model.addAttribute("numberOfQuizes", stats.getQuizCount());
            model.addAttribute("percentageSuccess", stats.getPercentageSuccess());
            model.addAttribute("numberOfCorrect", correctAnswerCount);
            model.addAttribute("numberOfWrong", wrongAnswerCount);
        } else {
            model.addAttribute("numberOfQuizes", 0);
            model.addAttribute("percentageSuccess", 0);
            model.addAttribute("numberOfCorrect", 0);
            model.addAttribute("numberOfWrong", 0);
        }

        model.addAttribute("chartData", getChartData(correctAnswerCount, wrongAnswerCount));
        return "user_stats";
    }

    @GetMapping("/all_stats")
    public String viewAllStats(Model model) {
        List<UserStatTotal> stats = userStatTotalDao.findAll();

        Set<UserResult> statSet = new TreeSet<>();
        for (UserStatTotal stat : stats) {
            UserResult userResult = new UserResult(stat.getUserId(), stat.getEmail(), stat.getQuizCount(),
                    stat.getCorrectAnswerCount(), stat.getWrongAnswerCount(), stat.getPercentageSuccess());
            statSet.add(userResult);
        }

        int i = 0;
        for (UserResult userResult : statSet) {
            userResult.setPosition(++i);
        }

        model.addAttribute("stats", statSet);
        return "all_stats";
    }

    private List<List<Object>> getChartData(int correctAnswerCount, int wrongAnswerCount) {
        List<List<Object>> list = new ArrayList<>();

        List<Object> correctAnswers = new ArrayList<>();
        correctAnswers.add("Correct answers");
        correctAnswers.add(correctAnswerCount);

        List<Object> wrongAnswers = new ArrayList<>();
        wrongAnswers.add("Wrong answers");
        wrongAnswers.add(wrongAnswerCount);

        list.add(correctAnswers);
        list.add(wrongAnswers);

        return list;
    }
}
