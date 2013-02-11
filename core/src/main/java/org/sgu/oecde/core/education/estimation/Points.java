package org.sgu.oecde.core.education.estimation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * Баллы. содержит студента, учебный план и соответсвующие им баллы/оценки, полученные на основе результатов,
 * а так же сумму баллы
 * @author ShihovMY
 */
public class Points implements Serializable{
    private AbstractStudent student;
    private Curriculum curriculum;
    private Map<IEstimate,Object> workPoints = new HashMap<IEstimate,Object>();
    private float sum;
    private static final long serialVersionUID = 65L;

    public Points() {
    }

    public Points(AbstractStudent student, Curriculum curriculum) {
        this.student = student;
        this.curriculum = curriculum;
    }

    /**
     * учебный план
     * @param <T> extends Curriculum
     * @return
     */
    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    /**
     * учебный план
     * @param curriculum
     */
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * студент
     * @param <T> extends AbstractStudent
     * @return
     */
    public <T extends AbstractStudent>T getStudent() {
        return (T) student;
    }

    /**
     * студент
     * @param student
     */
    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    /**
     * добавляет в набор оценок новые записи, сожержащиеся в workPoints
     * @param workPoints
     */
    public void addWorkPoints(Map<IEstimate, Object> workPoints) {
        if(workPoints!=null)
            this.workPoints.putAll(workPoints);
    }

    /**
     * 
     * @param <T> extends Object>Map<IEstimate, T
     * @return баллы/оценки и соответсвующие названия полей
     */
    public <T extends Object>Map<IEstimate, T> getWorkPoints() {
        return (Map<IEstimate, T>) workPoints;
    }

    /**
     * 
     * @param <T> extends Object>Map<IEstimate, T
     * @return баллы/оценки по соответсвующему названию поля
     */
    public <E>E getWorkPoints(IEstimate name) {
        if(workPoints.containsKey(name))
            return (E) workPoints.get(name);
        else
            return null;
    }

    /**
     *
     * @return сумма баллов
     */
    public float getSum() {
        return ((float)(Math.round(sum*100)))/100 ;
    }

    /**
     * сумма баллов
     * @param sum
     */
    public void setSum(float sum) {
        this.sum = sum;
    }

    public void addSum(float sum){
        this.sum+=sum;
    }

    /**
     * добавляет в набор оценок/баллов и новую запись
     * @param name - имя поля с оценками/баллами
     * @param value - баллы/оценки
     */
    public void addNewWorkPoint(IEstimate name,Object value){
        this.getWorkPoints().put(name, value);
    }

    /**
     * добавляет в набор оценок/баллов новую запись с баллами
     * @param name - имя поля с оценками/баллами
     * @param points - баллы
     */
    public void addIntegerWorkPoints(IEstimate name,Integer points){
        Object val = this.getWorkPoints().get(name);
        Integer oldP = 0;
        if(val!=null)
            Assert.isInstanceOf(Integer.class,val,val+", value of"+name+", is not integer ");
        oldP = this.<Integer>getWorkPoints().get(name);
        this.<Integer>getWorkPoints().put(name, (oldP!=null?oldP:0)+points);
    }

    /**
     * {@inheritDoc }
     */
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

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.student != null ? this.student.hashCode() : 0);
        hash = 83 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("учебный план: ").append(this.curriculum).append(";");
        sb.append("студент: ").append(this.student).append(";");
        if(!CollectionUtils.isEmpty(getWorkPoints())){
            Map<IEstimate,Object> m = getWorkPoints();
            Iterator specsI = m.entrySet().iterator();
            while(specsI.hasNext()){
                Map.Entry<IEstimate,Object> v = (Map.Entry)specsI.next();
                sb.append("\n").append(v.getKey()).append("   ").append(v.getValue());
            }
        }
        sb.append("\n----------  ").append(getSum());
        return sb.toString();
    }
}
