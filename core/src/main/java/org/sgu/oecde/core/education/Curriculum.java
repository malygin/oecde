package org.sgu.oecde.core.education;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 * связка из учебного плана преподавателя,группы за семестр и год, соответсвующего умк, 
 * а так же тип оценивания группы по курсу данном семестре в данном году
 *
 * @author ShihovMY
 */
public class Curriculum extends BasicItem{

    /**
     *  семестр
     */
    private Integer semester;
    /**
     * календарный год
     */
    private Integer calendarYear;
    /**
     * отношение студенческой группы к преподавателю и учебному плану
     */
    private Set<TeacherToGroup> teacherToGroups;
    /**
     * умк
     */
    private Umk umk;
    /**
     * способ оценивания
     */
    private ExaminationType examinationType;
    private static final long serialVersionUID = 59L;

    public Curriculum() {
    }

    public Curriculum(Long id) {
        setId(id);
    }
    /**
     * @return семестр
     */
    public Integer getSemester() {
        return semester;
    }

    /**
     * семестр
     * @param semester
     */
    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    /**
     * @return год
     */
    public Integer getCalendarYear() {
        return calendarYear;
    }

    /**
     * год
     * @param year
     */
    public void setCalendarYear(Integer year) {
        this.calendarYear = year;
    }

    /**
     * группа-преподаватель-учебный план
     * @return
     */
    public Set<TeacherToGroup> getTeacherToGroups() {
        return teacherToGroups;
    }

    /**
     * группа-преподаватель-учебный план
     * @param teacherToGroups
     */
    public void setTeacherToGroups(Set<TeacherToGroup> teacherToGroups) {
        this.teacherToGroups = teacherToGroups;
    }

    /**
     *
     * @param <T> extends Umk
     * @return умк
     */
    public<T extends Umk> T getUmk() {
        return (T) umk;
    }

    /**
     * умк
     * @param umk
     */
    public void setUmk(Umk umk) {
        this.umk = umk;
    }

    /**
     *
     * @return тип оценивания дисциплины
     */
    public ExaminationType getExaminationType() {
        return examinationType;
    }

    /**
     *  тип оценивания дисциплины
     * @param examinationType
     */
    public void setExaminationType(ExaminationType examinationType) {
        this.examinationType = examinationType;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("семестр: ").append(this.semester).append("; ");
        sb.append("год: ").append(this.calendarYear).append("; ");
        return sb.toString();
    }
}
