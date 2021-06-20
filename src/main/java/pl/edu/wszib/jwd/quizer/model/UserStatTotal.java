package pl.edu.wszib.jwd.quizer.model;

import javax.persistence.*;

@Entity
@Table(name = "users_stats_total")
public class UserStatTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String email;
    private int quizCount;
    int correctAnswerCount;
    int wrongAnswerCount;
    int percentageSuccess;

    public UserStatTotal() {
    }

    public UserStatTotal(Long userId, String email, int quizCount, int correctAnswerCount, int wrongAnswerCount, int percentageSuccess) {
        this.userId = userId;
        this.email = email;
        this.quizCount = quizCount;
        this.correctAnswerCount = correctAnswerCount;
        this.wrongAnswerCount = wrongAnswerCount;
        this.percentageSuccess = percentageSuccess;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuizCount() {
        return quizCount;
    }

    public void setQuizCount(int quizCount) {
        this.quizCount = quizCount;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

    public int getWrongAnswerCount() {
        return wrongAnswerCount;
    }

    public void setWrongAnswerCount(int wrongAnswerCount) {
        this.wrongAnswerCount = wrongAnswerCount;
    }

    public int getPercentageSuccess() {
        return percentageSuccess;
    }

    public void setPercentageSuccess(int percentageSuccess) {
        this.percentageSuccess = percentageSuccess;
    }
}
