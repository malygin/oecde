package org.sgu.oecde.core.education.work;

import org.sgu.oecde.core.education.resource.AbstractResource;

/**
 * самостоятельная работа. от неё наследуются тесты, тренажёры, рабочие тетради и прочее
 * @author ShihovMY
 */
 abstract public class SelfDependentWork extends AbstractResource{
     /**
      * вес. максимальное количество баллов, которые можно набрать после прохождения данной работы
      */
    private Integer weight;
    /**
     * количество попыток в зачёт
     */
    private Integer estimateAttemptsNumber;
    /**
     * количество пробных попыток
     */
    private Integer trialNumber;
    /**
     * длительность
     */
    private Integer duration;

    public SelfDependentWork() {
    }

    /**
     *
     * @return длительность
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * длительность
     * @param duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * количество попыток в зачёт
     * @return
     */
    public Integer getEstimateAttemptsNumber() {
        return estimateAttemptsNumber;
    }

    /**
     * количество попыток в зачёт
     * @param estimateAttemptsNumber
     */
    public void setEstimateAttemptsNumber(Integer estimateAttemptsNumber) {
        this.estimateAttemptsNumber = estimateAttemptsNumber;
    }

    /**
     * пробных попыток
     * @return
     */
    public Integer getTrialNumber() {
        return trialNumber;
    }

    /**
     * пробных попыток
     * @param trialNumber
     */
    public void setTrialNumber(Integer trialNumber) {
        this.trialNumber = trialNumber;
    }

    /**
     *
     * @return максимум баллов
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * максимум баллов
     * @param weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
