package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.education.work.SelfDependentWork;

/**
 *
 * @author ShihovMY
 */
public class TestEntity extends SelfDependentWork{
    private TestType type;
    private Integer quantity;
    private TestEstimationType estimation;
    private String openDate;
    private String closeDate;
    private Boolean writable;
    private Boolean shuffle;
    private Set<Question> questions;
    private static final long serialVersionUID = 70L;

    public TestEntity() {
    }

    public TestEntity(Long id) {
        setId(id);
    }

    public TestEstimationType getEstimation() {
        return estimation;
    }

    public void setEstimation(TestEstimationType estimation) {
        this.estimation = estimation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(Boolean shuffle) {
        this.shuffle = shuffle;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public Boolean isWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
