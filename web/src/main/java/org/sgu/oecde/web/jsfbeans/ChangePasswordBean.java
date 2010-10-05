package org.sgu.oecde.web.jsfbeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.hibernate.validator.constraints.NotEmpty;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="changePasswordBean")
@ViewScoped
public class ChangePasswordBean implements Serializable{

    @ManagedProperty(value="#{userDao}")
    private IUpdateDao<AbstractUser>userDao;

    @NotEmpty(message="не введён новый пароль")
    private String password;

    @NotEmpty(message="не введён старый пароль")
    private String oldPassword;

    @NotEmpty(message="Вы не повторили новый пароль")
    private String rePassword;

    private boolean saved;

    private String error;

    private static final long serialVersionUID = 155L;

    public void savePassword(AjaxBehaviorEvent event){
        AbstractUser user = (AbstractUser) event.getComponent().getAttributes().get("user");
        Assert.notNull(user);
        if(user.getPassword().equals(oldPassword)){
            if(password.equals(rePassword)){
                user.setPassword(password);
                try {
                    userDao.update(user);
                } catch (Exception e) {
                    e.fillInStackTrace();
                    error = "При сохранении возникла ошибка";
                }
                saved = true;
                error = null;
            }else{
                error = "Неверно повторен пароль";
            }
        }else{
            error = "Неверно введён старый пароль";
        }
    }

    public void setUserDao(IUpdateDao<AbstractUser> userDao) {
        this.userDao = userDao;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
