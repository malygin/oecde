package org.sgu.oecde.core.education;

/**
 * связка из учебного плана специальности,дисциплины,преподавателя,группы за семестр и год
 * @author shihovmy
 */
public class AdvancedCurriculum extends Curriculum{

    private Speciality speciality;
    private Discipline discipline;
    private static final long serialVersionUID = 46L;

    public AdvancedCurriculum() {
    }

    public AdvancedCurriculum(int id) {
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
