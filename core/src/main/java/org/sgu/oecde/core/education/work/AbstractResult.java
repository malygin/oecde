package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.util.StringUtils;

/**
 * результат работы (контрольная, самостоятельная, тест, итоговая оценка и тд)
 * @author ShihovMY
 */
abstract public class AbstractResult extends BasicItem implements Comparable<AbstractResult>{
    /**
     * студент
     */
    private AbstractStudent student;
    /**
     * учебный план
     */
    private Curriculum curriculum;
    /**
     * дата
     */
    private String date;

    public AbstractResult() {
    }

    /**
     * учебный план
     * @param curriculum
     */
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * дата
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * дата
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @param <T>  extends AbstractStudent
     * @return студент
     */
    public <T extends AbstractStudent>T getStudent() {
        return (T) student;
    }

    /**
     *
     * @param <T> extends Curriculum
     * @return учебный план
     */
    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    /**
     * студент
     * @param student
     */
    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int compareTo(AbstractResult o) {
       int curriculumInt = 0;
       int studentInt = 0;
       if(o!=null){
           if(o.getCurriculum()!=null&&getCurriculum()!=null&&o.getCurriculum().getId()!=null)
               curriculumInt = (o.getCurriculum().getId().compareTo(getCurriculum().getId()));
           if(o.getStudent()!=null&&getStudent()!=null&&o.getStudent().getId()!=null)
               studentInt = o.getStudent().getId().compareTo(getStudent().getId());
       }
       return studentInt==0?(curriculumInt):studentInt;
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
        final AbstractResult other = (AbstractResult) obj;
        if (this.student != other.student && (this.student == null || !this.student.equals(other.student))) {
            return false;
        }
        if (this.curriculum != other.curriculum && (this.curriculum == null || !this.curriculum.equals(other.curriculum))) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
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
        hash = 47 * hash + (this.student != null ? this.student.hashCode() : 0);
        hash = 47 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        hash = 47 * hash + (this.date != null ? this.date.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("учебный план: ").append(curriculum).append(";\n");
        if(student!=null&&StringUtils.hasText(student.getFio()))
            sb.append("студент: ").append(student.getInitials()).append(";\n");
        sb.append("дата: ").append(date).append(";");
        return sb.toString();
    }
}
