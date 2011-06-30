package org.sgu.oecde.controlworks;

import java.util.List;
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
    private List<? extends ControlWorkAttempt> cwAttempt;

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
    public List<? extends ControlWorkAttempt> getCwAttempt() {
        return cwAttempt;
    }

    /**
     * попытки
     * @param cwTrial
     */
    public void setCwAttempt(List<? extends ControlWorkAttempt> cwTrial) {
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
     * @todo не здесь должна быть првоверка на балы, не здесь..
     */
    public void setPoints(Integer points) {
        if (points==null) this.points = points;
        else if (points>100) this.points=100;
        else if(points<=0) this.points=0;        
                else this.points = points;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("статус: ").append(progress).append("; ");
        sb.append("баллов: ").append(points).append("; ");
        return sb.toString();
    }
}
