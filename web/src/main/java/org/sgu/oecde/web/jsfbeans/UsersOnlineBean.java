package org.sgu.oecde.web.jsfbeans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.users.UsersInCache;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="usersOnlineBean")
@ViewScoped
public class UsersOnlineBean implements Serializable{

    private List<? extends AbstractUser>users;
    private String type;

    @ManagedProperty(value="#{usersInCache}")
    private UsersInCache usersInCache;

    private static final long serialVersionUID = 180L;

    public String getType() {
        return type;
    }

    public List<? extends AbstractUser> getUsers() {
        return users;
    }

    public void setType(String type) {
        if(type!=null)
            type = type.toUpperCase();
        UserType t = UserType.valueOf(type);
        switch(t){
            case ADMIN:
                users = usersInCache.getAdmins();
                break;
            case SUPERVISOR:
                users = usersInCache.getSupervisors();
                break;
            case TEACHER:
                users = usersInCache.getTeachers();
                break;
            case STUDENT:
                users = usersInCache.getStudents();
                break;
            default:
                return;
        }
        this.type = type;
    }

    public void setUsersInCache(UsersInCache usersInCache) {
        this.usersInCache = usersInCache;
    }
}
