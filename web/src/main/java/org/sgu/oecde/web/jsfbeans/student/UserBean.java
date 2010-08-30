package org.sgu.oecde.web.jsfbeans.student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;


/**
 *
 * @author KorgovVD
 */
@ManagedBean(name="userBean")
@ViewScoped
public class UserBean {

    private AbstractUser user;

    private Long id;

    @ManagedProperty(value="#{userDao}")
    private IBasicDao<AbstractUser>userDao;

    public UserBean() {
    }

    public AbstractUser getUser(){
        if(user==null){
            user = userDao.getById(id);
        }
        return user;
    }

    public void setUserDao(IBasicDao<AbstractUser> userDao) {
        this.userDao = userDao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
