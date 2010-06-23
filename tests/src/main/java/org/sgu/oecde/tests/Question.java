package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 * вопрос теста
 * @author ShihovMY
 */
public class Question  extends BasicItem{

    /**
     * текст вопроса
     */
    private String title;
    /**
     * количество баллов, которое можно набрать, ответив правильно на этот вопрос
     */
    private Integer weight;
    /**
     * тип вопроса
     * @see QuestionType
     */
    private QuestionType type;
    /**
     * варианты ответов
     */
    private Set<Answer> answers;
    private static final long serialVersionUID = 72L;

    public Question() {
    }

    public Question(Long id) {
        setId(id);
    }

    /**
     *
     * @return варианты ответов
     */
    public Set<Answer> getAnswers() {
        return answers;
    }

    /**
     * варианты ответов
     * @param answers
     */
    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    /**
     *
     * @return текст вопроса
     */
    public String getTitle() {
        return title;
    }

    /**
     * текст вопроса
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return тип
     */
    public QuestionType getType() {
        return type;
    }

    /**
     * тип
     * @param type
     */
    public void setType(QuestionType type) {
        this.type = type;
    }

    /**
     *
     * @return баллы
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * баллы
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("вопрос: ").append(title).append("; ");
        return sb.toString();
    }
}
