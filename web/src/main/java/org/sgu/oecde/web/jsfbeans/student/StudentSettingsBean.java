package org.sgu.oecde.web.jsfbeans.student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.AvatarBuilder;
import org.sgu.oecde.web.IBeanWithAvatarAdding;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSettingsBean")
@ViewScoped
public class StudentSettingsBean implements IBeanWithAvatarAdding{

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;

    @ManagedProperty(value="#{avatarBuilder}")
    private AvatarBuilder avatarBuilder;

    @ManagedProperty(value="#{studentDao}")
    private IUpdateDao<Student>studentDao;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 156L;

    @Override
    public void uploadAvatar(){
        avatarBuilder.addAvatar(studentSessionBean.getStudent());
    }

    public Student getStudent(){
        return studentSessionBean.getStudent();
    }

    public void save(){
        try {
            studentDao.update(studentSessionBean.getStudent());
        } catch (Exception e) {
            e.fillInStackTrace();
            error = "При сохранении возникла ошибка";
        }
        saved = true;
    }

    public AbstractUser getUser() {
        return getStudent();
    }

    public void setStudentSessionBean(StudentSessionBean studentSessionBean) {
        this.studentSessionBean = studentSessionBean;
    }

    public void setAvatarBuilder(AvatarBuilder avatarBuilder) {
        this.avatarBuilder = avatarBuilder;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setStudentDao(IUpdateDao<Student> studentDao) {
        this.studentDao = studentDao;
    }
}
