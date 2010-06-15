package org.sgu.oecde.tests;

import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public class GivenAnswer extends BasicItem{
    private AnsweredQuestion answeredQuestion;
    private Answer rightAnswer;
    private String givenAnswer;
    private static final long serialVersionUID = 73L;

    public GivenAnswer() {
    }

    public GivenAnswer(Long id) {
        setId(id);
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public Answer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public AnsweredQuestion getAnsweredQuestion() {
        return answeredQuestion;
    }

    public void setAnsweredQuestion(AnsweredQuestion answeredQuestion) {
        this.answeredQuestion = answeredQuestion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GivenAnswer other = (GivenAnswer) obj;
        if (this.answeredQuestion != other.answeredQuestion && (this.answeredQuestion == null || !this.answeredQuestion.equals(other.answeredQuestion))) {
            return false;
        }
        if (this.rightAnswer != other.rightAnswer && (this.rightAnswer == null || !this.rightAnswer.equals(other.rightAnswer))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.answeredQuestion != null ? this.answeredQuestion.hashCode() : 0);
        hash = 23 * hash + (this.rightAnswer != null ? this.rightAnswer.hashCode() : 0);
        return hash;
    }
}
