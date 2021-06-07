package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.model.User;

@Controller
public class NewUserController {
    @Autowired
    UserDao userDao;

    @GetMapping("/newUser")
    public String get(Model model) {
        model.addAttribute("newUser", new User());
        return "newUser";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/";
    }
}
