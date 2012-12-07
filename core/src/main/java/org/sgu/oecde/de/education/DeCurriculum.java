package org.sgu.oecde.de.education;

import org.sgu.oecde.core.education.AdvancedCurriculum;
import org.sgu.oecde.core.users.Department;

/**
 * учебный план дистанционного обучения.
 * @author ShihovMY
 */
public class DeCurriculum extends AdvancedCurriculum{
    /**
     * контрольные работы по данной дисциплине у данной специальности в данном семестре
     * в данном году только в рукописном
     */
    private Boolean controlWorksPaperOnly;
    /**
     * количество лабораторных/самостоятельных работ
     */
    private Integer labWorksNumber;
    /**
     * количество курсовых
     */
    private Integer termPapersNumber;
    /**
     * выбрана ли дисциплина
     */
    private Boolean selected = true;
    
    private Integer weightTest = 20;
    private Integer weightAud = 20;
    private Integer weightOutAud = 20;
    private Integer weightPers = 20;
    private Integer weightAtt = 20;

    private  Boolean scientificActivities= false;
    
    
  
    
    // форма образования - очники - заочники
    private FormEducation formEducation;
    

    
    private static final long serialVersionUID = 63L;

    public DeCurriculum() {
    }

    public DeCurriculum(Long id) {
        super(id);
    }

    /**
     *
     * @return кр только в рукописном
     * @see #controlWorksPaperOnly
     */
    public Boolean getControlWorksPaperOnly() {
        return controlWorksPaperOnly;
    }

    /**
     * кр только в рукописном
     * @param controlWorksPaperOnly
     * @see #controlWorksPaperOnly
     */
    public void setControlWorksPaperOnly(Boolean controlWorksPaperOnly) {
        this.controlWorksPaperOnly = controlWorksPaperOnly;
    }

    /**
     *
     * @return количество лабораторных/самостоятельных работ
     */
    public Integer getLabWorksNumber() {
        return labWorksNumber;
    }

    /**
     * оличество лабораторных/самостоятельных работ
     * @param labWorksNumber
     */
    public void setLabWorksNumber(Integer labWorksNumber) {
        this.labWorksNumber = labWorksNumber;
    }

    /**
     *
     * @return выбрана
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * выбрана
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     *
     * @return количество курсовых
     */
    public Integer getTermPapersNumber() {
        return termPapersNumber;
    }

    /**
     * количество курсовых
     * @param termPapersNumber
     */
    public void setTermPapersNumber(Integer termPapersNumber) {
        this.termPapersNumber = termPapersNumber;
    }

    public FormEducation getFormEducation() {
        return formEducation;
    }

    public void setFormEducation(FormEducation formEducation) {
        this.formEducation = formEducation;
    }

    public Integer getWeightAtt() {
        return weightAtt;
    }

    public void setWeightAtt(Integer weightAtt) {
        this.weightAtt = weightAtt;
    }

    public Integer getWeightAud() {
        return weightAud;
    }

    public void setWeightAud(Integer weightAud) {
        this.weightAud = weightAud;
    }

    public Integer getWeightOutAud() {
        return weightOutAud;
    }

    public void setWeightOutAud(Integer weightOutAud) {
        this.weightOutAud = weightOutAud;
    }

    public Integer getWeightPers() {
        return weightPers;
    }

    public void setWeightPers(Integer weightPers) {
        this.weightPers = weightPers;
    }

    public Integer getWeightTest() {
        return weightTest;
    }

    public void setWeightTest(Integer weightTest) {
        this.weightTest = weightTest;
    }


    public Boolean getScientificActivities() {
        return scientificActivities;
    }

    public void setScientificActivities(Boolean scientificActivities) {
        this.scientificActivities = scientificActivities;
    }
}
