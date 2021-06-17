package pl.edu.wszib.jwd.quizer.model;

import javax.persistence.*;

@Entity
@Table(name="users_answers")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private int quizNumber;
    private Long questionId;
    private int answerNumber;

    @Column(nullable = false)
    private boolean isCorrect;

    public UserAnswer() {
    }

    public UserAnswer(Long userId, int quizNumber, Long questionId, int answerNumber, boolean isCorrect) {
        this.userId = userId;
        this.quizNumber = quizNumber;
        this.questionId = questionId;
        this.answerNumber = answerNumber;
        this.isCorrect = isCorrect;

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

    public int getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

