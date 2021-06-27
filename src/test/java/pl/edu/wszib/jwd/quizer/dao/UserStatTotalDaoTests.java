package pl.edu.wszib.jwd.quizer.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.edu.wszib.jwd.quizer.model.UserStatTotal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class UserStatTotalDaoTests {

    @Autowired
    private UserStatTotalDao userStatTotalDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserStatTotal() {
        UserStatTotal userStatTotal = new UserStatTotal();
        userStatTotal.setUserId(1L);
        userStatTotal.setEmail("jan.kowalski@gmail.com");
        userStatTotal.setQuizCount(1);
        userStatTotal.setCorrectAnswerCount(0);
        userStatTotal.setWrongAnswerCount(5);
        userStatTotal.setPercentageSuccess(0);

        UserStatTotal savedUserStatTotal = userStatTotalDao.save(userStatTotal);
        UserStatTotal existUserStatTotal = entityManager.find(UserStatTotal.class, savedUserStatTotal.getId());
        assertThat(existUserStatTotal.getEmail()).isEqualTo(userStatTotal.getEmail());
    }

    @Test
    public void testFindUserStatTotalByEmail() {
        UserStatTotal userStatTotal = userStatTotalDao.findByUserId(1L);
        assertThat(userStatTotal).isNotNull();
    }
}
