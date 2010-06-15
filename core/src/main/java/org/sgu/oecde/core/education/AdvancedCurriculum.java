package org.sgu.oecde.core.education;

/**
 * связка из учебного плана специальности,дисциплины,преподавателя,группы за семестр и год
 * @author shihovmy
 */
public class AdvancedCurriculum extends Curriculum{

    private Speciality speciality;
    private Discipline discipline;
    private Boolean gotControlWork;
    private static final long serialVersionUID = 46L;

    public AdvancedCurriculum() {
    }

    public AdvancedCurriculum(Long id) {
        super(id);
    }

    /**
     * @return дисциплина
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Boolean isGotControlWork() {
        return gotControlWork;
    }

    public void setGotControlWork(Boolean gotControlWork) {
        this.gotControlWork = gotControlWork;
    }

    /**
     * @return специальность
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
