package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.model.User;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserDao userDao;

    @GetMapping("")
    public String viewHomePage() {
        return "main";
    }

    @GetMapping("/register")
    public String showSignUpFrom(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDao.save(user);
        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> listUsers = userDao.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/quiz_panel")
    public String viewQuizPanel(Model model) {
//        List<User> listUsers = userDao.findAll();
//        model.addAttribute("listUsers", listUsers);
        return "quiz_panel";
    }
}
