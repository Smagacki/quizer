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
import pl.edu.wszib.jwd.quizer.model.UserStat;
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

        List<UserStatTotal> stats = userStatTotalDao.findByUserId(user.getId());

        if (stats.size() != 0) {
            model.addAttribute("numberOfQuizes", stats.get(0).getQuizCount());
            model.addAttribute("percentageSuccess", stats.get(0).getPercentageSuccess());
            model.addAttribute("numberOfCorrect", stats.get(0).getCorrectAnswerCount());
            model.addAttribute("numberOfWrong", stats.get(0).getWrongAnswerCount());
        } else {
            model.addAttribute("numberOfQuizes", 0);
            model.addAttribute("percentageSuccess", 0);
            model.addAttribute("numberOfCorrect", 0);
            model.addAttribute("numberOfWrong", 0);
        }

        return "user_stats";
    }

    @GetMapping("/all_stats")
    public String viewAllStats(Model model) {
        user = userService.getCurrentUser();

        List<UserStatTotal> stats = userStatTotalDao.findAll();
        Set<UserResult> statSet = new TreeSet<>();

        for (UserStatTotal stat : stats) {
            UserResult userResult = new UserResult(stat.getUserId(), stat.getEmail(), stat.getQuizCount(),
                    stat.getCorrectAnswerCount(), stat.getWrongAnswerCount(), stat.getPercentageSuccess());
            statSet.add(userResult);
        }

        model.addAttribute("stats", statSet);
        return "all_stats";
    }
}
