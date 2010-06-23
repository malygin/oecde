package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 * вопрос, на который был дан ответ. содержит ссылки на попытку по тесту, сам вопрос
 * и коллекцию данных ответов
 * @author ShihovMY
 */
public class AnsweredQuestion extends BasicItem{
    /**
     * прохождение теста
     */
    private TestAttempt attempt;
    /**
     * вопрос
     */
    private Question question;
    /**
     * данные ответы
     */
    private Set<GivenAnswer> givenAnswers;
    /**
     * правильный ли был дан ответ
     */
    private Boolean right;
    /**
     * набрано баллов
     */
    private Integer resultPoints;
    private static final long serialVersionUID = 74L;

    public AnsweredQuestion() {
    }

    public AnsweredQuestion(Long id) {
        setId(id);
    }

    /**
     *
     * @return данные ответы
     */
    public Set<GivenAnswer> getGivenAnswers() {
        return givenAnswers;
    }

    /**
     * данные ответы
     * @param givenAnswers
     */
    public void setGivenAnswers(Set<GivenAnswer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    /**
     *
     * @return вопрос
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * вопрос
     * @param question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     *
     * @return набранные баллы
     */
    public Integer getResultPoints() {
        return resultPoints;
    }

    /**
     * набранные баллы
     * @param resultPoints
     */
    public void setResultPoints(Integer resultPoints) {
        this.resultPoints = resultPoints;
    }

    /**
     *
     * @return правильный ли был дан овтет
     */
    public Boolean isRight() {
        return right;
    }

    /**
     * правильный ли был дан овтет
     * @param right
     */
    public void setRight(Boolean right) {
        this.right = right;
    }

    /**
     *
     * @return прохождение теста
     */
    public TestAttempt getAttempt() {
        return attempt;
    }

    /**
     * прохождение теста
     * @param attempt
     */
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("вопрос: ").append(question).append("; ");
        sb.append("результат: ").append(right?"правильно":"не правильно").append("; ");
        return sb.toString();
    }
}
