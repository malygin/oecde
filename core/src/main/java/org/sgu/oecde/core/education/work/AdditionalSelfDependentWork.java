package org.sgu.oecde.core.education.work;

import java.io.Serializable;
import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.springframework.util.StringUtils;

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
    private int estimateAttemptsUsedNumber;

    /**
     * количество использованных студентом пробных попыток
     */
    private int trialAttemptsUsedNumber;

    /**
     * количество использованных студентом попыток переэкзаменовки
     */
    private int reExameAttemptsUsedNumber;
    /**
     * сумма баллов, набранных студентом по данной работе
     */
    private int pointsForWork;
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
    public int getEstimateAttemptsUsedNumber() {
        return estimateAttemptsUsedNumber;
    }

    /**
     * количество использованных студентом попыток в зачёт
     * @param estimateAttemptsUsedNumber
     */
    public void setEstimateAttemptsUsedNumber(int estimateAttemptsUsedNumber) {
        this.estimateAttemptsUsedNumber = estimateAttemptsUsedNumber;
    }

    /**
     *
     * @return использованных попыток переэкзаменовки
     */
    public int getReExameAttemptsUsedNumber() {
        return reExameAttemptsUsedNumber;
    }

    /**
     * использованных попыток переэкзаменовки
     * @param reExameAttemptsUsedNumber
     */
    public void setReExameAttemptsUsedNumber(int reExameAttemptsUsedNumber) {
        this.reExameAttemptsUsedNumber = reExameAttemptsUsedNumber;
    }

    /**
     *
     * @return использованных пробных попыток
     */
    public int getTrialAttemptsUsedNumber() {
        return trialAttemptsUsedNumber;
    }

    /**
     * использованных пробных попыток
     * @param trialAttemptsUsedNumber
     */
    public void setTrialAttemptsUsedNumber(int trialAttemptsUsedNumber) {
        this.trialAttemptsUsedNumber = trialAttemptsUsedNumber;
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
    public int getPointsForWork() {
        return pointsForWork;
    }

    /**
     * сумма баллов, набранных студентом по данной работе
     * @param pointsForWork
     */
    public void setPointsForWork(int pointsForWork) {
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
        StringBuffer sb = new StringBuffer();
        sb.append("учебный план: ").append(curriculum).append("\n");
        if(work!=null&&StringUtils.hasText(work.getTitle()))
            sb.append("работа: ").append(work.getTitle()).append(" (").append(work.getClass().getSimpleName()).append(");\n");
        return sb.toString();
    }
}
