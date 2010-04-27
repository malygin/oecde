package org.sgu.oecde.core.education.estimation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
public class Points implements Serializable{
    private AbstractStudent student;
    private Curriculum curriculum;
    private List<EstimatedWorkPoints> workPoints = new LinkedList<EstimatedWorkPoints>();
    private int sum;
    private static final long serialVersionUID = 65L;

    public Points() {
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public AbstractStudent getStudent() {
        return student;
    }

    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    public List<EstimatedWorkPoints> getWorkPoints() {
        return workPoints;
    }

    public void setWorkPoints(List<EstimatedWorkPoints> workPoints) {
        this.workPoints = workPoints;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
