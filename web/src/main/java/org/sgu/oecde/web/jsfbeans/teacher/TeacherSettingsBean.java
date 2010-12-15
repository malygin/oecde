package org.sgu.oecde.web.jsfbeans.teacher;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.web.AvatarBuilder;
import org.sgu.oecde.web.IBeanWithAvatarAdding;
import org.sgu.oecde.web.jsfbeans.UserViewBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherSettingsBean")
@ViewScoped
public class TeacherSettingsBean extends UserViewBean implements IBeanWithAvatarAdding{

    @ManagedProperty(value="#{avatarBuilder}")
    private AvatarBuilder avatarBuilder;

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 172L;

    public Teacher getTeacher(){
        return (Teacher) getUser();
    }

    public void save(){
        try {
            super.save();
        } catch (Exception e) {
            e.fillInStackTrace();
            error = "При сохранении возникла ошибка";
        }
        saved = true;
    }
    
    public void uploadAvatar(){
        avatarBuilder.addAvatar(teacherSessionBean.getTeacher());
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @PostConstruct
    public void postConstract(){
        setUser(teacherSessionBean.getTeacher());
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }

    public void setAvatarBuilder(AvatarBuilder avatarBuilder) {
        this.avatarBuilder = avatarBuilder;
    }
}
