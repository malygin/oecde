package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;

/**
 *
 * @author ShihovMY
 */
public class TestAttempt extends AbstractSelfDependentWorkResult{
    private Integer quantity;
    private Integer duration;
    private TestAttemptType type;
    private Integer rightAnswers;
    private Integer points;
    private Set<AnsweredQuestion> answeredQuestions;
    private static final long serialVersionUID = 71L;

    public TestAttempt() {
    }

    public TestAttempt(String date) {
        setDate(date);
    }

    public TestAttempt(Long id) {
        setId(id);
    }

    public Set<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(Set<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Integer rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public TestAttemptType getType() {
        return type;
    }

    public void setType(TestAttemptType type) {
        this.type = type;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
