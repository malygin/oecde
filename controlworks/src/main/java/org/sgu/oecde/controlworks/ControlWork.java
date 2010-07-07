package org.sgu.oecde.controlworks;

import java.util.Set;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;

/**
 * @author shihovmy
 * сущность контрольная работа
 */
public class ControlWork extends AbstractResult{
    /**
     *  статус работы
     * @see ControlWorkProgress
     */
    private ControlWorkProgress progress;
    /**
     * коллекция сущностей с попытками
     */
    private Set<? extends ControlWorkAttempt> cwAttempt;

    /**
     * баллы
     */
    private Integer points;

    private static final long serialVersionUID = 91L;

    public ControlWork() {
    }

    public ControlWork(AbstractStudent student, Curriculum curriculum) {
        setStudent(student);
        setCurriculum(curriculum);
    }

    /**
     *
     * @return попытки
     */
    public Set<? extends ControlWorkAttempt> getCwAttempt() {
        return cwAttempt;
    }

    /**
     * попытки
     * @param cwTrial
     */
    public void setCwAttempt(Set<? extends ControlWorkAttempt> cwTrial) {
        this.cwAttempt = cwTrial;
    }

    /**
     *
     * @return статус
     * @see ControlWorkProgress
     */
    public ControlWorkProgress getProgress() {
        return progress;
    }

    /**
     * статус
     * @param progress
     */
    public void setProgress(ControlWorkProgress progress) {
        this.progress = progress;
    }

    /**
     *
     * @return баллы
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * баллы
     * @param points
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("статус: ").append(progress).append("; ");
        sb.append("баллов: ").append(points).append("; ");
        return sb.toString();
    }
}
