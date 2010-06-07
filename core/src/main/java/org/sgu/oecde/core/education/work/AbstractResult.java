package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
abstract public class AbstractResult extends BasicItem implements Comparable<AbstractResult>{
    private AbstractStudent student;
    private Curriculum curriculum;

    public AbstractResult() {
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }


    public <T extends AbstractStudent>T getStudent() {
        return (T) student;
    }

    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    @Override
    public int compareTo(AbstractResult o) {
       int curriculumInt = 0;
       int studentInt = 0;
       if(o!=null){
           if(o.getCurriculum()!=null&&getCurriculum()!=null)
               curriculumInt = (Integer.valueOf(o.getCurriculum().getId()).compareTo(getCurriculum().getId()));
           if(o.getStudent()!=null&&getStudent()!=null)
               studentInt = Integer.valueOf(o.getStudent().getId()).compareTo(getStudent().getId());
       }
       return studentInt==0?(curriculumInt):studentInt;
    }

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
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.student != null ? this.student.hashCode() : 0);
        hash = 97 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }
}
