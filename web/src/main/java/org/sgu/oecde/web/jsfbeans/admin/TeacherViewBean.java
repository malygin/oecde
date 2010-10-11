package org.sgu.oecde.web.jsfbeans.admin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.web.jsfbeans.UserViewBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherIndexBean;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherSessionBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherViewBean")
@ViewScoped
public class TeacherViewBean extends UserViewBean{

    @ManagedProperty(value="#{teacherSessionBean}")
    TeacherSessionBean teacherSessionBean;
    
    @ManagedProperty(value="#{teacherIndexBean}")
    TeacherIndexBean teacherIndexBean;

    private static final long serialVersionUID = 172L;

    public void setId(Long id) {
        super.setId(id);
        teacherSessionBean.setTeacher((Teacher) user);
        teacherIndexBean.setTeacher((Teacher) user);
    }

    public void setTeacherIndexBean(TeacherIndexBean teacherIndexBean) {
        this.teacherIndexBean = teacherIndexBean;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }
}
