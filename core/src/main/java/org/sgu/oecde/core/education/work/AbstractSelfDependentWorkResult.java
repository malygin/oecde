package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
abstract public class AbstractSelfDependentWorkResult extends BasicItem{
    private String date;
    private AbstractStudent student;
    private Curriculum curriculum;
    private SelfDependentWork work;

    public AbstractSelfDependentWorkResult() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public <T extends AbstractStudent>T getStudent() {
        return (T) student;
    }

    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public <T extends SelfDependentWork>T getWork() {
        return (T) work;
    }

    public void setWork(SelfDependentWork work) {
        this.work = work;
    }
}
