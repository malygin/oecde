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

    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public <T extends AbstractStudent>T getStudent() {
        return (T) student;
    }

    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    public List<EstimatedWorkPoints> getWorkPoints() {
        return workPoints;
    }

    public void setWorkPoints(List<EstimatedWorkPoints> workPoints) {
        this.workPoints.addAll(workPoints);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Points other = (Points) obj;
        if (this.student != other.student && (this.student == null || !this.student.equals(other.student))) {
            return false;
        }
        if (this.curriculum != other.curriculum && (this.curriculum == null || !this.curriculum.equals(other.curriculum))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.student != null ? this.student.hashCode() : 0);
        hash = 83 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }
}
