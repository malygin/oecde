package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.education.work.AbstractSelfDependentWorkResult;

/**
 * попытка прохождения теста
 * @author ShihovMY
 */
public class TestAttempt extends AbstractSelfDependentWorkResult{
    /**
     * количетсво вопросов, на которые был дан ответ
     */
    private Integer quantity;
    /**
     * время прохождения теста
     */
    private Integer duration;
    /**
     * тип прохождения
     * @see TestAttemptType
     */
    private TestAttemptType type;
    /**
     * количество вопросов, на которые был дан правильный ответ
     */
    private Integer rightAnswers;
    /**
     * количетво набранных баллов
     */
    private Integer points;
    /**
     * вопросы, на которые был дан ответ
     */
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

    /**
     *
     * @return отвеченные вопросы
     */
    public Set<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    /**
     * отвеченные вопросы
     * @param answeredQuestions
     */
    public void setAnsweredQuestions(Set<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    /**
     *
     * @return время прохождения
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * время прохождения
     * @param duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return количество ответов
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * количество ответов
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return правильных ответов
     */
    public Integer getRightAnswers() {
        return rightAnswers;
    }

    /**
     * правильных ответов
     * @param rightAnswers
     */
    public void setRightAnswers(Integer rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    /**
     *
     * @return тип
     */
    public TestAttemptType getType() {
        return type;
    }

    /**
     * тип
     * @param type
     */
    public void setType(TestAttemptType type) {
        this.type = type;
    }

    /**
     *
     * @return набрано баллов
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * набрано баллов
     * @param points
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("набрано баллов: ").append(points).append(";\n");
        return sb.toString();
    }
}
