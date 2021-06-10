package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.jwd.quizer.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findFirstByLogin(String login);

}
