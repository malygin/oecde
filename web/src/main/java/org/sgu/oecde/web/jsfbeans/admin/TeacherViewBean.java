package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherIndexBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherSessionBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherViewBean")
@ViewScoped
public class TeacherViewBean implements Serializable{

    @ManagedProperty(value="#{teacherDao}")
    IBasicDao<Teacher>teacherDao;

    @ManagedProperty(value="#{teacherSessionBean}")
    TeacherSessionBean teacherSessionBean;
    
    @ManagedProperty(value="#{teacherIndexBean}")
    TeacherIndexBean teacherIndexBean;

    Teacher teacher;
    Long id;

    private static final long serialVersionUID = 172L;

    public Long getId() {
        teacherSessionBean.setTeacher(teacher);
        teacherIndexBean.setTeacher(teacher);
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TeacherIndexBean getTeacherIndexBean() {
        return teacherIndexBean;
    }

    public void setTeacherIndexBean(TeacherIndexBean teacherIndexBean) {
        this.teacherIndexBean = teacherIndexBean;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }

    public void setTeacherDao(IBasicDao<Teacher> teacherDao) {
        this.teacherDao = teacherDao;
    }
}
