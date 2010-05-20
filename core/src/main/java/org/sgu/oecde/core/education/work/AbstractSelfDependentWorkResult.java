package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
abstract public class AbstractSelfDependentWorkResult extends BasicItem implements Comparable<AbstractSelfDependentWorkResult>{
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

    @Override
    public int compareTo(AbstractSelfDependentWorkResult o) {
       int curriculumInt = (Integer.valueOf(o.getCurriculum().getId()).compareTo(getCurriculum().getId()));
       int studentInt = Integer.valueOf(o.getStudent().getId()).compareTo(getStudent().getId());
       int idInt = Integer.valueOf(o.getId()).compareTo(getId());
       int dateInt = o.getDate().compareTo(getDate());
       int workInt = Integer.valueOf(o.getWork().getId()).compareTo(getWork().getId());
       return studentInt==0?
           (curriculumInt==0?
           (workInt==0?
               (dateInt==0?idInt:dateInt):
               workInt):
               curriculumInt):
                   studentInt;
    }
}
