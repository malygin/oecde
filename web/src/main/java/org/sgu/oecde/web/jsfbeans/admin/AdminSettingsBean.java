package org.sgu.oecde.web.jsfbeans.admin;

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
@ManagedBean(name="adminSettingsBean")
@ViewScoped
public class AdminSettingsBean extends UserViewBean implements IBeanWithAvatarAdding{

    @ManagedProperty(value="#{avatarBuilder}")
    private AvatarBuilder avatarBuilder;

    @ManagedProperty(value="#{adminSessionBean}")
    private AdminSessionBean adminSessionBean;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 181L;

    public Teacher getTeacher(){
        return (Teacher) getUser();
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
    
    public void uploadAvatar(){
        avatarBuilder.addAvatar(adminSessionBean.getAdmin());
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
        setUser(adminSessionBean.getAdmin());
    }

    public void setAdminSessionBean(AdminSessionBean adminSessionBean) {
        this.adminSessionBean = adminSessionBean;
    }

    public void setAvatarBuilder(AvatarBuilder avatarBuilder) {
        this.avatarBuilder = avatarBuilder;
    }
}
