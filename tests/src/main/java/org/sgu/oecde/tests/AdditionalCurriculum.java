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
    /**
     * баллы по тестам
     */
    private int testPoints;
    /**
     * баллы по итоговым тестам
     */
    private int concludingTestPoints;
    /**
     * баллы по переэкзаменовке
     */
    private int reTestPoints;
    /**
     * баллы по итоговым тестам переэкзаменовка
     */
    private int concludingReTestPoints;
    
    private int activityPoints;
    private int samAudWorkPoints;
    private int samAudOutWorkPoints;
    private int personalCharPoints;
    /**
     * сумма баллов всех
     */
    private float sum;
    private static final long serialVersionUID = 76L;

    public AdditionalCurriculum() {
    }

    public AdditionalCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
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
    public <T extends Curriculum>T getCurriculum() {
        return (T) curriculum;
    }

    /**
     * учебный план
     * @param curriculum
     */
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    /**
     *
     * @return баллы по итоговым тестам переэкзаменовка
     */
    public int getConcludingReTestPoints() {
        return concludingReTestPoints;
    }

    /**
     * баллы по итоговым тестам переэкзаменовка
     * @param concludingReTestPoints
     */
    public void setConcludingReTestPoints(Integer concludingReTestPoints) {
        this.concludingReTestPoints = concludingReTestPoints!=null?concludingReTestPoints:0;
    }

    /**
     *
     * @return баллы по итоговым тестам
     */
    public int getConcludingTestPoints() {
        return concludingTestPoints;
    }

    /**
     * баллы по итоговым тестам
     * @param concludingTestPoints
     */
    public void setConcludingTestPoints(Integer concludingTestPoints) {
        this.concludingTestPoints = concludingTestPoints!=null?concludingTestPoints:0;
    }

    /**
     *
     * @return баллы по тестам переэкзаменовка
     */
    public int getReTestPoints() {
        return reTestPoints;
    }

    /**
     * баллы по  тестам переэкзаменовка
     * @param reTestPoints
     */
    public void setReTestPoints(Integer reTestPoints) {
        this.reTestPoints = reTestPoints!=null?reTestPoints:0;
    }

    /**
     *
     * @return баллы по тестам
     */
    public int getTestPoints() {
        return testPoints;
    }

    /**
     * баллы по тестам
     * @param testPoints
     */
    public void setTestPoints(Integer testPoints) {
        this.testPoints = testPoints!=null?testPoints:0;
    }

    public int getActivityPoints() {
        return activityPoints;
    }

    public void setActivityPoints(int activityPoints) {
        this.activityPoints = activityPoints;
    }

    public int getPersonalCharPoints() {
        return personalCharPoints;
    }

    public void setPersonalCharPoints(int personalCharPoints) {
        this.personalCharPoints = personalCharPoints;
    }

    public int getSamAudOutWorkPoints() {
        return samAudOutWorkPoints;
    }

    public void setSamAudOutWorkPoints(int samAudOutWorkPoints) {
        this.samAudOutWorkPoints = samAudOutWorkPoints;
    }

    public int getSamAudWorkPoints() {
        return samAudWorkPoints;
    }

    public void setSamAudWorkPoints(int samAudWorkPoints) {
        this.samAudWorkPoints = samAudWorkPoints;
    }
    
    

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
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
