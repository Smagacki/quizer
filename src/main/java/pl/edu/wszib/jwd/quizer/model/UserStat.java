package pl.edu.wszib.jwd.quizer.model;

import javax.persistence.*;

@Entity
@Table(name = "users_stats")
public class UserStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int numberOfQuizes;

    public UserStat() {
    }

    public UserStat(Long userId, int numberOfQuizes) {
        this.userId = userId;
        this.numberOfQuizes = numberOfQuizes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getNumberOfQuizes() {
        return numberOfQuizes;
    }

    public void setNumberOfQuizes(int numberOfQuizes) {
        this.numberOfQuizes = numberOfQuizes;
    }
}
