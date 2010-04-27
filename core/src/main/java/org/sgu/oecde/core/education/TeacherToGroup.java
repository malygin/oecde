/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education;

import java.io.Serializable;
import org.sgu.oecde.core.users.AbstractTeacher;
import org.sgu.oecde.core.users.StudentGroup;

/**
 *
 * @author ShihovMY
 */
public final class TeacherToGroup implements Serializable{
    private StudentGroup group;
    private AbstractTeacher teacher;
    private Curriculum curriculum;
    private static final long serialVersionUID = 64L;

    public TeacherToGroup() {
    }

    public TeacherToGroup(AbstractTeacher teacher) {
        this.teacher = teacher;
    }

    public TeacherToGroup(StudentGroup group) {
        this.group = group;
    }

    public <T extends Curriculum> T getCurriculum() {
        return (T) curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public <T extends StudentGroup> T getGroup() {
        return (T) group;
    }

    public void setGroup(StudentGroup group) {
        this.group = group;
    }

    public <T extends AbstractTeacher> T getTeacher() {
        return (T) teacher;
    }

    public void setTeacher(AbstractTeacher teacher) {
        this.teacher = teacher;
    }

    /**
	 * 
	 * @param obj
	 * @return 
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
	 * 
	 * @return 
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
	 * 
	 * @return 
	 */
	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Group: ").append(this.group).append("; ");
        sb.append("Teacher: ").append(this.teacher).append("; ");
        sb.append("Curriculum: ").append(this.curriculum).append("; ");
        return sb.toString();
    }
}
