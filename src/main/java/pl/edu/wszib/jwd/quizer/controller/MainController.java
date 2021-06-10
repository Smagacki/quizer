package pl.edu.wszib.jwd.quizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.jwd.quizer.dao.UserAnswerDao;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.model.User;
import pl.edu.wszib.jwd.quizer.model.UserAnswer;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    UserDao userDao;

    private String currUserLogin;

    @GetMapping("/")
    public String get(Model model) {


        if (currUserLogin != null) {
            User user = userDao.findFirstByLogin(currUserLogin);
//            if(user != null)
//                model.addAttribute("currentUser", user.getLogin());
//            } else {
//                model.addAttribute("currentUser", userInfo.get());
//            }

        }




        return "main";
    }
}
