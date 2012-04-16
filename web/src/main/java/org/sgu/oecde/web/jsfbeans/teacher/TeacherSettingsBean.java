package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IUpdateDao;
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
public class TeacherSettingsBean implements IBeanWithAvatarAdding{

    @ManagedProperty(value="#{avatarBuilder}")
    private AvatarBuilder avatarBuilder;

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;

    @ManagedProperty(value="#{teacherDao}")
    private IUpdateDao<Teacher>teacherDao;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 172L;

    public Teacher getTeacher(){
        return teacherSessionBean.getTeacher();
    }

    public Teacher getUser(){
        return getTeacher();
    }
       public Map getSkins() {
        return getTeacher().getSkin().toMap();
    }

    public void save(){
        try {
            teacherDao.update(teacherSessionBean.getTeacher());
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

    public void setTeacherDao(IUpdateDao<Teacher> teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }

    public void setAvatarBuilder(AvatarBuilder avatarBuilder) {
        this.avatarBuilder = avatarBuilder;
    }
}
