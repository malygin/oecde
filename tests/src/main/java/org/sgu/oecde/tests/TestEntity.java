package org.sgu.oecde.tests;

import java.util.Set;
import org.sgu.oecde.core.education.work.SelfDependentWork;

/**
 *
 * @author ShihovMY
 */
public class TestEntity extends SelfDependentWork{
    private TestType type;
    private int quantity;
    private TestEstimationType estimation;
    private boolean writable;
    private boolean shuffle;
    private Set<Question> questions;
    private static final long serialVersionUID = 70L;

    public TestEntity() {
    }

    public TestEntity(int id) {
        setId(id);
    }

    public TestEstimationType getEstimation() {
        return estimation;
    }

    public void setEstimation(TestEstimationType estimation) {
        this.estimation = estimation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }
}
