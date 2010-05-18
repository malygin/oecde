package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;

/**
 *
 * @author ShihovMY
 */
public class TestAttempt extends AbstractSelfDependentWorkResult{
    private int quantity;
    private int duration;
    private TestAttemptType type;
    private int rightAnswers;
    private int points;
    private Set<AnsweredQuestion> answeredQuestions;
    private static final long serialVersionUID = 71L;

    public TestAttempt() {
    }

    public TestAttempt(String date) {
        setDate(date);
    }

    public TestAttempt(int id) {
        setId(id);
    }

    public Set<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(Set<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public TestAttemptType getType() {
        return type;
    }

    public void setType(TestAttemptType type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
