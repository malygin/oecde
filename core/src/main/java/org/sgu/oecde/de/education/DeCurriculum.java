package org.sgu.oecde.de.education;

import org.sgu.oecde.core.education.AdvancedCurriculum;

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
    private Boolean selected;
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
    public Boolean isControlWorksPaperOnly() {
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
    public Boolean isSelected() {
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
    
}
