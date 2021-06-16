package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.jwd.quizer.model.User;
import pl.edu.wszib.jwd.quizer.model.UserStat;

@Repository
public interface UserStatDao extends JpaRepository<UserStat, Long> {

//    @Query("select u from UserStat u where u.email = ?1")
//    User findByEmail(String email);

    @Query("select max(u.quizNumber) from UserStat u where u.id = ?1")
    int findTopById(Long id);

}

