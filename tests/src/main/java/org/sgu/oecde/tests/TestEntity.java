package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.education.work.SelfDependentWork;

/**
 * тест
 * @author ShihovMY
 */
public class TestEntity extends SelfDependentWork{
    /**
     * тип теста
     * @see TestType
     */
    private TestType type;
    /**
     * количество выводимых вопросов
     */
    private Integer quantity;
    /**
     * тип оценивания
     * @see TestEstimationType
     */
    private TestEstimationType estimation;
    /**
     * дата начала прохождения теста
     */
    private String openDate;
    /**
     * дата окончания прохождения теста
     */
    private String closeDate;
    /**
     * записывать ли полностью прохождение базу
     */
    private Boolean writable;
    /**
     * перемешивать ли
     */
    private Boolean shuffle;
    /**
     * вопросы
     */
    private Set<Question> questions;
    private static final long serialVersionUID = 70L;

    public TestEntity() {
    }

    public TestEntity(Long id) {
        setId(id);
    }

    /**
     *
     * @return тип оценивания
     */
    public TestEstimationType getEstimation() {
        return estimation;
    }

    /**
     * тип оценивания
     * @param estimation
     */
    public void setEstimation(TestEstimationType estimation) {
        this.estimation = estimation;
    }

    /**
     *
     * @return количество выводимых ответов
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * количество выводимых ответов
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return вопросы
     */
    public Set<Question> getQuestions() {
        return questions;
    }

    /**
     * вопросы
     * @param questions
     */
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    /**
     *
     * @return перемешивать ли
     */
    public Boolean isShuffle() {
        return shuffle;
    }

    /**
     * перемешивать ли
     * @param shuffle
     */
    public void setShuffle(Boolean shuffle) {
        this.shuffle = shuffle;
    }

    /**
     *
     * @return тип теста
     */
    public TestType getType() {
        return type;
    }

    /**
     * тип теста
     * @param type
     */
    public void setType(TestType type) {
        this.type = type;
    }

    /**
     *
     * @return записывать ли полностью прохождение
     */
    public Boolean isWritable() {
        return writable;
    }

    /**
     * записывать ли полностью прохождение
     * @param writable
     */
    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    /**
     *
     * @return дата закрытия
     */
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * дата закрытия
     * @param closeDate
     */
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    /**
     *
     * @return дата открытия
     */
    public String getOpenDate() {
        return openDate;
    }

    /**
     * дата открытия
     * @param openDate
     */
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
