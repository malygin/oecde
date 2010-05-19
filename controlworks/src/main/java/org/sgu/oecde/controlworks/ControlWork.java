package org.sgu.oecde.controlworks;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 * @author shihovmy
 *  сущность контрольная работа
 */
public class ControlWork extends  BasicItem{
    /**
     * kod - айди студента
     */
    private AbstractStudent student;
    /**
     * semestr - семеср отправки работы
     */
    private Curriculum curriculum;
    /**
     * progress - статус работы
     */
    private ControlWorkProgress progress;
    /**
     * cwAttempt - коллекция сущностей с попытками
     */
    private Set<? extends ControlWorkAttempt> cwAttempt;

    private int points;

    private static final long serialVersionUID = 91L;

    public ControlWork() {
    }

    public ControlWork(AbstractStudent student, Curriculum curriculum) {
        this.student = student;
        this.curriculum = curriculum;
    }

    public Set<? extends ControlWorkAttempt> getCwAttempt() {
        return cwAttempt;
    }

    public void setCwAttempt(Set<? extends ControlWorkAttempt> cwTrial) {
        this.cwAttempt = cwTrial;
    }

    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public ControlWorkProgress getProgress() {
        return progress;
    }

    public void setProgress(ControlWorkProgress progress) {
        this.progress = progress;
    }

    public <T extends AbstractStudent>T getStudent() {
        return (T) student;
    }

    public void setStudent(AbstractStudent student) {
        this.student = student;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ControlWork other = (ControlWork) obj;
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
        hash = 97 * hash + (this.student != null ? this.student.hashCode() : 0);
        hash = 97 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }

}
