package org.sgu.oecde.tests;

import org.sgu.oecde.core.BasicItem;

/**
 * выбранный вариант ответа
 * @author ShihovMY
 */
public class GivenAnswer extends BasicItem{
    /**
     * вопрос, на который был дан ответ
     */
    private AnsweredQuestion answeredQuestion;
    /**
     * вариант ответа
     */
    private Answer rightAnswer;
    /**
     * данный овтет
     */
    private String givenAnswer;
    private static final long serialVersionUID = 73L;

    public GivenAnswer() {
    }

    public GivenAnswer(Long id) {
        setId(id);
    }

    /**
     *
     * @return данный овтет
     */
    public String getGivenAnswer() {
        return givenAnswer;
    }

    /**
     * данный овтет
     * @param givenAnswer
     */
    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    /**
     *
     * @return вариант ответа
     */
    public Answer getRightAnswer() {
        return rightAnswer;
    }

    /**
     * вариант ответа
     * @param rightAnswer
     */
    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    /**
     *
     * @return вопрос, на который был дан ответ
     */
    public AnsweredQuestion getAnsweredQuestion() {
        return answeredQuestion;
    }

    /**
     * вопрос, на который был дан ответ
     * @param answeredQuestion
     */
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("вариант ответа: ").append(rightAnswer).append("; ");
        sb.append("ответ: ").append(givenAnswer).append("; ");
        return sb.toString();
    }
}
