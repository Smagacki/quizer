package pl.edu.wszib.jwd.quizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.edu.wszib.jwd.quizer.dao.UserDao;
import pl.edu.wszib.jwd.quizer.model.User;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userDao.findByEmail(email);
    }
}
