package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.jwd.quizer.model.User;

import java.util.List;

@Controller
public class StatsController {

    @GetMapping("/user_stats")
    public String viewUsersStats(Model model) {
        return "user_stats";
    }

    @GetMapping("/all_stats")
    public String viewAllStats(Model model) {
        return "all_stats";
    }

}
