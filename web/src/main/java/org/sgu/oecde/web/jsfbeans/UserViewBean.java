package org.sgu.oecde.web.jsfbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Supervisor;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="userViewBean")
@ViewScoped
public class UserViewBean implements Serializable{
    @ManagedProperty(value="#{adminDao}")
    IBasicDao<Admin>adminDao;

    @ManagedProperty(value="#{teacherDao}")
    IBasicDao<Teacher>teacherDao;

    @ManagedProperty(value="#{studentDao}")
    IBasicDao<Student>studentDao;

    @ManagedProperty(value="#{supervisorDao}")
    IBasicDao<Supervisor>supervisorDao;

    private Long id;
    private String type;
    private AbstractUser user;

    private static final long serialVersionUID = 163L;

    public AbstractUser getUser(){
        if(user == null){
            UserType t = UserType.valueOf(type);
            switch(t){
                case ADMIN:
                    user = adminDao.getById(id);
                    break;
                case SUPERVISOR:
                    user = supervisorDao.getById(id);
                    break;
                case TEACHER:
                    user = teacherDao.getById(id);
                    break;
                case STUDENT:
                    user = studentDao.getById(id);
                    break;
                default:
                    throw new IllegalAccessError();
            }
        }
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAdminDao(IBasicDao<Admin> adminDao) {
        this.adminDao = adminDao;
    }

    public void setStudentDao(IBasicDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    public void setSupervisorDao(IBasicDao<Supervisor> supervisorDao) {
        this.supervisorDao = supervisorDao;
    }

    public void setTeacherDao(IBasicDao<Teacher> teacherDao) {
        this.teacherDao = teacherDao;
    }
}
