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
public abstract class AbstractTeacher extends AbstractPerson implements ITeacher{
 
    private String post;
    private Set<TeacherToGroup> teacherToGroups;
    private Department department;
    public AbstractTeacher() {
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String getPost() {
        return post;
    }

    public Set<TeacherToGroup> getTeacherToGroups() {
        return teacherToGroups;
    }

    public void setTeacherToGroups(Set<TeacherToGroup> teacherToGroups) {
        this.teacherToGroups = teacherToGroups;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
