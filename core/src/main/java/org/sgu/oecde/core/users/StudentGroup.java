package org.sgu.oecde.core.users;

import java.util.Set;
import org.sgu.oecde.core.education.TeacherToGroup;
import org.springframework.util.CollectionUtils;

/**
 * студенческая группа
 * @author ShihovMY
 */
public class StudentGroup<T extends AbstractStudent> extends AbstractGroup<T>{
    /**
     * курс
     */
    private Integer year;
    /**
     * группа-преподаватель-учебный план
     */
    private Set<TeacherToGroup> teacherToGroups;

    private Integer calendarYear;

    public  StudentGroup() {
    }

    public StudentGroup(Long id) {
        setId(id);
    }

    /**
     *
     * @return количество человек
     */
    public Integer getNumber() {
        return CollectionUtils.isEmpty(getPersons())?0:getPersons().size();
    }

    /**
     *
     * @return курс
     */
    public Integer getYear() {
        return year;
    }

    /**
     * курс
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     *
     * @return группа-преподаватель-учебный план
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

    public Integer getCalendarYear() {
        return calendarYear;
    }

    public void setCalendarYear(Integer calendarYear) {
        this.calendarYear = calendarYear;
    }
}
