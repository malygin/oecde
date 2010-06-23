package org.sgu.oecde.core.education;

/**
 * наследуется от Curriculum и добавляет специальность, дисциплину, параметр - есть ли кр или нет
 * у данной специальности по данной дисциплины в данном семестре в данном году
 * @author shihovmy
 * @see org.sgu.oecde.core.education.Curriculum
 * @see org.sgu.oecde.core.education.Discipline
 * @see org.sgu.oecde.core.education.Speciality
 */
public class AdvancedCurriculum extends Curriculum{
    /**
     * специальность
     */
    private Speciality speciality;
    /**
     * дисциплина
     */
    private Discipline discipline;
    /**
     * есть ли контрольные рабоы
     */
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

    /**
     * дисциплина
     * @param discipline
     */
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    /**
     *
     * @return есть ли кр
     */
    public Boolean isGotControlWork() {
        return gotControlWork;
    }

    /**
     * есть ли кр
     * @param gotControlWork
     */
    public void setGotControlWork(Boolean gotControlWork) {
        this.gotControlWork = gotControlWork;
    }

    /**
     * @return специальность
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * специальность
     * @param speciality
     */
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        if(discipline!=null&&!discipline.getName().isEmpty())
            sb.append("дисциплина: ").append(discipline.getName()).append("; ");
        if(speciality!=null&&!speciality.getName().isEmpty())
            sb.append("специальность: ").append(speciality.getName()).append("; ");
        return sb.toString();
    }
}
