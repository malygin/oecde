package org.sgu.oecde.tests;

import java.io.Serializable;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.estimation.Points;

/**
 * расширенная версия учебных планов. Помимо ссылки на сами планы имеет баллы, набранные студентом по
 * данной дисциплине в даннмо семестре в данном году, а так же количество пройденных тестов и общее
 * количество тестов
 * @author ShihovMY
 * @see org.sgu.oecde.core.education.Curriculum
 */
public class AdditionalCurriculum implements Serializable,Comparable<AdditionalCurriculum>{
    /**
     * баллы
     */
    private Points points;
    /**
     * учебный план
     */
    private Curriculum curriculum;
    /**
     * общее количество тестов
     */
    private int testsCount;
    /**
     * количество пройденных тестов
     */
    private int passedTests;
    private static final long serialVersionUID = 76L;

    public AdditionalCurriculum() {
    }

    /**
     *
     * @return количество пройденных тестов
     */
    public int getPassedTests() {
        return passedTests;
    }

    /**
     * количество пройденных тестов
     * @param passedTests
     */
    public void setPassedTests(int passedTests) {
        this.passedTests = passedTests;
    }

    /**
     *
     * @return баллы
     */
    public Points getPoints() {
        return points;
    }

    /**
     * баллы
     * @param points
     */
    public void setPoints(Points points) {
        this.points = points;
    }

    /**
     *
     * @return количество тестов
     */
    public int getTestsCount() {
        return testsCount;
    }

    /**
     * количество тестов
     * @param testsCount
     */
    public void setTestsCount(int testsCount) {
        this.testsCount = testsCount;
    }

    /**
     *
     * @return учебный план
     */
    public Curriculum getCurriculum() {
        return curriculum;
    }

    /**
     * учебный план
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
        final AdditionalCurriculum other = (AdditionalCurriculum) obj;
        if (this.curriculum != other.curriculum && (this.curriculum == null || !this.curriculum.equals(other.curriculum))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(AdditionalCurriculum o) {
       int curriculumInt = 0;
       if(o!=null)
           if(o.getCurriculum()!=null&&getCurriculum()!=null&&o.getCurriculum().getId()!=null)
               curriculumInt = (o.getCurriculum().getId().compareTo(getCurriculum().getId()));
       return curriculumInt;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("учебный план: ").append(curriculum).append("; ");
        return sb.toString();
    }
}
