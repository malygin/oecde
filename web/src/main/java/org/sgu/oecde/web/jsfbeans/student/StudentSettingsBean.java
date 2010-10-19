package org.sgu.oecde.web.jsfbeans.student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.AvatarBuilder;
import org.sgu.oecde.web.IBeanWithAvatarAdding;
import org.sgu.oecde.web.jsfbeans.UserViewBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="studentSettingsBean")
@ViewScoped
public class StudentSettingsBean extends UserViewBean implements IBeanWithAvatarAdding{

    @ManagedProperty(value="#{studentSessionBean}")
    private StudentSessionBean studentSessionBean;

    @ManagedProperty(value="#{avatarBuilder}")
    private AvatarBuilder avatarBuilder;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 156L;

    public void uploadAvatar(){
        avatarBuilder.addAvatar(studentSessionBean.getStudent());
    }

    public Student getStudent(){
        return (Student) getUser();
    }

    public void save(){
        try {
            save();
        } catch (Exception e) {
            e.fillInStackTrace();
            error = "При сохранении возникла ошибка";
        }
        saved = true;
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

    @PostConstruct
    public void postConstract(){
        setUser(studentSessionBean.getStudent());
    }
}
