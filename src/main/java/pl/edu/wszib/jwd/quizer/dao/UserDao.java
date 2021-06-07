package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.jwd.quizer.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findFirstByLogin(String userName);
}
