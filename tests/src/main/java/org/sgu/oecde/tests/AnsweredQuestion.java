package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class AnsweredQuestion extends BasicItem{
    private TestAttempt attempt;
    private Question question;
    private Set<GivenAnswer> givenAnswers;
    private Boolean right;
    private Integer resultPoints;
    private static final long serialVersionUID = 74L;

    public AnsweredQuestion() {
    }

    public AnsweredQuestion(Long id) {
        setId(id);
    }

    public Set<GivenAnswer> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(Set<GivenAnswer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getResultPoints() {
        return resultPoints;
    }

    public void setResultPoints(Integer resultPoints) {
        this.resultPoints = resultPoints;
    }

    public Boolean isRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }

    public TestAttempt getAttempt() {
        return attempt;
    }

    public void setAttempt(TestAttempt attempt) {
        this.attempt = attempt;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnsweredQuestion other = (AnsweredQuestion) obj;
        if (this.attempt != other.attempt && (this.attempt == null || !this.attempt.equals(other.attempt))) {
            return false;
        }
        if (this.question != other.question && (this.question == null || !this.question.equals(other.question))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.attempt != null ? this.attempt.hashCode() : 0);
        hash = 89 * hash + (this.question != null ? this.question.hashCode() : 0);
        return hash;
    }
}
