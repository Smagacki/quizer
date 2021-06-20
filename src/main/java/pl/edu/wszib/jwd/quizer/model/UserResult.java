package pl.edu.wszib.jwd.quizer.model;

import org.jetbrains.annotations.NotNull;

public class UserResult implements Comparable<UserResult> {
    long userId;
    String email;
    int quizCount;
    int correctAnswerCount;
    int wrongAnswerCount;
    int percentageSuccess;

    public UserResult() {
    }

    public UserResult(long userId, String email, int quizCount, int correctAnswerCount, int wrongAnswerCount, int percentageSuccess) {
        this.userId = userId;
        this.email = email;
        this.quizCount = quizCount;
        this.correctAnswerCount = correctAnswerCount;
        this.wrongAnswerCount = wrongAnswerCount;
        this.percentageSuccess = percentageSuccess;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    @Override
    public int compareTo(@NotNull UserResult u) {
        return Integer.compare(percentageSuccess, u.percentageSuccess) * -1;
    }
}
