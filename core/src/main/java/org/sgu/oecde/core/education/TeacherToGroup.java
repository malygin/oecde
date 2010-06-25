package org.sgu.oecde.core.education;

import java.io.Serializable;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.StudentGroup;

/**
 * соотношение преподавателя-группы и учебного плана.
 * Показывает, какой преподаватель ведёт у этой группы какой курс за конкретный семестр
 * в конкретном году
 * @author ShihovMY
 */
public final class TeacherToGroup implements Serializable{
    private StudentGroup group;
    private Teacher teacher;
    private Curriculum curriculum;
    private static final long serialVersionUID = 64L;

    public TeacherToGroup() {
    }

    public TeacherToGroup(Teacher teacher) {
        this.teacher = teacher;
    }

    public TeacherToGroup(StudentGroup group) {
        this.group = group;
    }

    /**
     * учебный план
     * @param <T> extends Curriculum
     * @return
     */
    public <T extends Curriculum> T getCurriculum() {
        return (T) curriculum;
    }

    /**
     * учебный план
     * @param curriculum
     */
    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * студенческая группа
     * @param <T> extends StudentGroup
     * @return
     */
    public <T extends StudentGroup> T getGroup() {
        return (T) group;
    }

    /**
     * студенческая группа
     * @param group
     */
    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    /**
     * преподаватель
     * @param <T> extends Teacher
     * @return
     */
    public <T extends Teacher> T getTeacher() {
        return (T) teacher;
    }

    /**
     * преподаватель
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TeacherToGroup other = (TeacherToGroup) obj;
        if (this.group != other.group && (this.group == null || !this.group.equals(other.group))) {
            return false;
        }
        if (this.teacher != other.teacher && (this.teacher == null || !this.teacher.equals(other.teacher))) {
            return false;
        }
        if (this.curriculum != other.curriculum && (this.curriculum == null || !this.curriculum.equals(other.curriculum))) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hash = 33;
        hash = 11 * hash + (this.group != null ? this.group.hashCode() : 0);
        hash = 11 * hash + (this.teacher != null ? this.teacher.hashCode() : 0);
        hash = 11 * hash + (this.curriculum != null ? this.curriculum.hashCode() : 0);
        return hash;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Group: ").append(this.group).append(";\n");
        sb.append("Teacher: ").append(this.teacher).append(";\n");
        sb.append("Curriculum: ").append(this.curriculum).append(";");
        return sb.toString();
    }
}
