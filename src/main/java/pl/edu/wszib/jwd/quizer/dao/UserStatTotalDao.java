package pl.edu.wszib.jwd.quizer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.jwd.quizer.model.UserStat;
import pl.edu.wszib.jwd.quizer.model.UserStatTotal;

import java.util.List;

@Repository
public interface UserStatTotalDao extends JpaRepository<UserStatTotal, Long> {

    @Query("select u from UserStatTotal u where u.userId = ?1")
    List<UserStatTotal> findByUserId(Long userId);
}

