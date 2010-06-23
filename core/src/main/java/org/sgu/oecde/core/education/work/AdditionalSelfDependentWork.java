package org.sgu.oecde.core.education.work;

import java.io.Serializable;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;

/**
 * расширенная версия самостоятельной работы. не имеет отображения в бд. Содержит ссылку
 * на саму работу, а так же ряд параметров, как то количество использованных попыток, ссылка на
 * учебный план и тд.
 * @author ShihovMY
 */
public class AdditionalSelfDependentWork implements Serializable{

    /**
     * количество использованных студентом попыток в зачёт
     */
    private Integer estimateAttemptsUsedNumber;
    /**
     * сумма баллов, набранных студентом по данной работе
     */
    private Integer pointsForWork;
    /**
     * результаты студента по данной работе
     */
    private List<? extends AbstractSelfDependentWorkResult> results;
    /**
     * ссылка на учебный план
     */
    private Curriculum curriculum;
    /**
     * самостоятельная работа
     */
    private SelfDependentWork work;
    private static final long serialVersionUID = 54L;

    public AdditionalSelfDependentWork() {
    }

    public AdditionalSelfDependentWork(SelfDependentWork work) {
        this.work = work;
    }

    /**
     *
     * @return количество использованных студентом попыток в зачёт
     */
    public Integer getEstimateAttemptsUsedNumber() {
        return estimateAttemptsUsedNumber;
    }

    /**
     * количество использованных студентом попыток в зачёт
     * @param estimateAttemptsUsedNumber
     */
    public void setEstimateAttemptsUsedNumber(Integer estimateAttemptsUsedNumber) {
        this.estimateAttemptsUsedNumber = estimateAttemptsUsedNumber;
    }

    /**
     * результаты студента по данной работе
     * @param <T> extends AbstractSelfDependentWorkResult
     * @return
     */
    public <T extends AbstractSelfDependentWorkResult>List<T> getResults() {
        return (List<T>) results;
    }

    /**
     * результаты студента по данной работе
     * @param results
     */
    public void setResults(List<? extends AbstractSelfDependentWorkResult> results) {
        this.results = (List<AbstractSelfDependentWorkResult>) results;
    }

    /**
     *
     * @return сумма баллов, набранных студентом по данной работе
     */
    public Integer getPointsForWork() {
        return pointsForWork;
    }

    /**
     * сумма баллов, набранных студентом по данной работе
     * @param pointsForWork
     */
    public void setPointsForWork(Integer pointsForWork) {
        this.pointsForWork = pointsForWork;
    }

    /**
     * ссылка на работу
     * @param <T> extends SelfDependentWork
     * @return
     */
    public <T extends SelfDependentWork>T getWork() {
        return (T) work;
    }

    /**
     * ссылка на работу
     * @param work
     */
    public void setWork(SelfDependentWork work) {
        this.work = work;
    }

    /**
     * ссылка на учебный план
     * @param <T> extends Curriculum
     * @return
     */
    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    /**
     * ссылка на учебный план
     * @param curriculum
     */
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AdditionalSelfDependentWork other = (AdditionalSelfDependentWork) obj;
        if (this.work != other.work && (this.work == null || !this.work.equals(other.work))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.work != null ? this.work.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("учебный план: ").append(curriculum).append(";\n");
        if(work!=null&&!work.getTitle().isEmpty())
            sb.append("работа: ").append(work.getTitle()).append(" (").append(work.getClass().getName()).append(");\n");
        return sb.toString();
    }
}
