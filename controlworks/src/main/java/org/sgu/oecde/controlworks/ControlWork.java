package org.sgu.oecde.controlworks;

import java.util.Set;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 * @author shihovmy
 *  сущность контрольная работа
 */
public class ControlWork extends AbstractResult{
    /**
     * progress - статус работы
     */
    private ControlWorkProgress progress;
    /**
     * cwAttempt - коллекция сущностей с попытками
     */
    private Set<? extends ControlWorkAttempt> cwAttempt;

    private Integer points;

    private static final long serialVersionUID = 91L;

    public ControlWork() {
    }

    public ControlWork(AbstractStudent student, Curriculum curriculum) {
        setStudent(student);
        setCurriculum(curriculum);
    }

    public Set<? extends ControlWorkAttempt> getCwAttempt() {
        return cwAttempt;
    }

    public void setCwAttempt(Set<? extends ControlWorkAttempt> cwTrial) {
        this.cwAttempt = cwTrial;
    }

    public ControlWorkProgress getProgress() {
        return progress;
    }

    public void setProgress(ControlWorkProgress progress) {
        this.progress = progress;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}
