package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.jwd.quizer.dao.UserStatDao;
import pl.edu.wszib.jwd.quizer.model.User;
import pl.edu.wszib.jwd.quizer.model.UserStat;

import java.util.List;

@Controller
public class StatsController {

    @Autowired
    UserStatDao userStatDao;

    private int numberOfQuizes;
    private int numberOfCorrect;
    private int numberOfWrong;
    private int percentageSuccess;

    @GetMapping("/user_stats")
    public String viewUsersStats(Model model) {
        numberOfQuizes = 0;
        numberOfCorrect = 0;
        numberOfWrong = 0;
        percentageSuccess = 0;

        List<UserStat> stats = userStatDao.findByUserId(1L);
        for (UserStat stat : stats) {
            numberOfQuizes++;
            numberOfCorrect += stat.getCorrectAnswer();
            numberOfWrong += stat.getWrongAnswer();
        }
        percentageSuccess = (numberOfCorrect * 100 / (numberOfCorrect + numberOfWrong));

        model.addAttribute("numberOfQuizes", numberOfQuizes);
        model.addAttribute("percentageSuccess", percentageSuccess);
        model.addAttribute("numberOfCorrect", numberOfCorrect);
        model.addAttribute("numberOfWrong", numberOfWrong);

        return "user_stats";
    }

    @GetMapping("/all_stats")
    public String viewAllStats(Model model) {
        List<UserStat> userStats = userStatDao.findAll();
        model.addAttribute("userStats", userStats);
        return "all_stats";
    }

}
