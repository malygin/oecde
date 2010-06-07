/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.users;

import java.util.Set;
import org.sgu.oecde.core.education.TeacherToGroup;

/**
 *
 * @author ShihovMY
 */
public abstract class StudentGroup extends AbstractGroup<AbstractStudent>{
    private Integer year;
    private Integer number;
    private Set<TeacherToGroup> teacherToGroups;

    public  StudentGroup() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<TeacherToGroup> getTeacherToGroups() {
        return teacherToGroups;
    }

    public void setTeacherToGroups(Set<TeacherToGroup> teacherToGroups) {
        this.teacherToGroups = teacherToGroups;
    }
}
