package org.sgu.oecde.web.jsfbeans;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Supervisor;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.de.users.Student;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 * 
 */
@ManagedBean(name="userViewBean")
@ViewScoped
public class UserViewBean implements Serializable{
    @ManagedProperty(value="#{adminDao}")
    private IUpdateDao<Admin>adminDao;

    @ManagedProperty(value="#{teacherDao}")
    private IUpdateDao<Teacher>teacherDao;

    @ManagedProperty(value="#{studentDao}")
    private IUpdateDao<Student>studentDao;

    @ManagedProperty(value="#{supervisorDao}")
    private IUpdateDao<Supervisor>supervisorDao;

    private Long id;
    private String type;
    protected AbstractUser user;

    private static final long serialVersionUID = 163L;

    public AbstractUser getUser(){
        if(user==null){
            if(type!=null)
                type = type.toUpperCase();
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
            if(!t.equals(UserType.toType(user)))
                return null;
        }
        return user;
    }
    
    public Map getSkins() {
        return user.getSkin().toMap();
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    @Secured("ROLE_ADMIN")
    public void save(){
        if(type!=null)
            type = type.toUpperCase();
        UserType t = UserType.valueOf(type);
        switch(t){
            case ADMIN:
                adminDao.update((Admin) user);
                break;
            case SUPERVISOR:
                supervisorDao.update((Supervisor) user);
                break;
            case TEACHER:
                teacherDao.update((Teacher) user);
                break;
            case STUDENT:
                studentDao.update((Student) user);
                break;
            default:
                throw new IllegalAccessError();
        }
    }

    @Secured("ROLE_ADMIN")
    public void deletePhoto(){
        user.setLargePhoto(null);
        user.setMediumPhoto(null);
        user.setSmallPhoto(null);
        save();
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

    public void setAdminDao(IUpdateDao<Admin> adminDao) {
        this.adminDao = adminDao;
    }

    public void setStudentDao(IUpdateDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    public void setSupervisorDao(IUpdateDao<Supervisor> supervisorDao) {
        this.supervisorDao = supervisorDao;
    }

    public void setTeacherDao(IUpdateDao<Teacher> teacherDao) {
        this.teacherDao = teacherDao;
    }
}
