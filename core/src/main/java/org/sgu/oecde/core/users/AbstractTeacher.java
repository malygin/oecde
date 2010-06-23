package org.sgu.oecde.core.users;

import java.util.Set;
import org.sgu.oecde.core.education.TeacherToGroup;

/**
 * абстрактный преподаватель
 * @author ShihovMY
 */
public abstract class AbstractTeacher extends AbstractPerson{

    /**
     * должность
     */
    private String post;
    /**
     * преподаватель - студенческая группа - учебный план
     */
    private Set<TeacherToGroup> teacherToGroups;
    /**
     * департамент/отдел/кафедра
     */
    private Department department;

    public AbstractTeacher() {
    }

    /**
     * должность
     * @param post
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     *
     * @return должность
     */
    public String getPost() {
        return post;
    }

    /**
     *
     * @return преподаватель - студенческая группа - учебный план
     */
    public Set<TeacherToGroup> getTeacherToGroups() {
        return teacherToGroups;
    }

    /**
     * преподаватель - студенческая группа - учебный план
     * @param teacherToGroups
     */
    public void setTeacherToGroups(Set<TeacherToGroup> teacherToGroups) {
        this.teacherToGroups = teacherToGroups;
    }

    /**
     *
     * @return отдел
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * отдел
     * @param department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}
