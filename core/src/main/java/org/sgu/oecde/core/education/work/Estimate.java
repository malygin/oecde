package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
public class Estimate extends BasicItem {
    private int gradeCode;
    private String date;
    private AbstractStudent student;
    private Curriculum curriculum;
    private static final long serialVersionUID = 67L;

    public Estimate() {
    }

    public int getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(int gradeCode) {
        this.gradeCode = gradeCode;
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
}
