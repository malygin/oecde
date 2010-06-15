package org.sgu.oecde.core.education.estimation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class Points implements Serializable{
    private AbstractStudent student;
    private Curriculum curriculum;
    private Map<IEstimate,Object> workPoints = new HashMap<IEstimate,Object>();
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

    public void addWorkPoints(Map<IEstimate, Object> workPoints) {
        if(workPoints!=null)
            this.workPoints.putAll(workPoints);
    }

    public <T extends Object>Map<IEstimate, T> getWorkPoints() {
        return (Map<IEstimate, T>) workPoints;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void addSum(int sum){
        this.sum+=sum;
    }

    public void addNewWorkPoint(IEstimate name,Object value){
        this.getWorkPoints().put(name, value);
    }

    public void addIntegerWorkPoints(IEstimate name,Integer points){
        Object val = this.getWorkPoints().get(name);
        Integer oldP = 0;
        if(val!=null)
            Assert.isInstanceOf(Integer.class,val,val+", value of"+name+", is not integer ");
        oldP = this.<Integer>getWorkPoints().get(name);
        this.<Integer>getWorkPoints().put(name, (oldP!=null?oldP:0)+points);
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
