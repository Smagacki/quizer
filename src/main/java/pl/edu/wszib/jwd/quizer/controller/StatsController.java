package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.jwd.quizer.dao.UserStatDao;
import pl.edu.wszib.jwd.quizer.model.UserStat;

import java.util.List;

@Controller
public class StatsController {

    @Autowired
    UserStatDao userStatDao;

    private int numberOfQuizes = 0;
    private int numberOfCorrect = 0;
    private int numberOfWrong = 0;

    @GetMapping("/user_stats")
    public String viewUsersStats(Model model) {
        List<UserStat> stats = userStatDao.findByUserId(1L);

        for (UserStat stat : stats) {
            numberOfQuizes++;
            numberOfCorrect += stat.getCorrectAnswer();
            numberOfWrong += stat.getWrongAnswer();
        }

        model.addAttribute("numberOfQuizes", numberOfQuizes);
        model.addAttribute("numberOfCorrect", numberOfCorrect);
        model.addAttribute("numberOfWrong", numberOfWrong);

        return "user_stats";
    }

    @GetMapping("/all_stats")
    public String viewAllStats(Model model) {
        return "all_stats";
    }

}
