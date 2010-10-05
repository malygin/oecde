package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ShihovMY
 */
public class ChangeUserBean implements Serializable{

    private Authentication a;

    private static final long serialVersionUID = 168L;

    @Secured("ROLE_ADMIN")
    public void change(AbstractUser user){
        if(a == null)
            a = SecurityContextHolder.getContext().getAuthentication();
        AbstractUser u = SecurityContextHandler.getUser();
        if(u!=null&&user!=null){
            Authentication newA = new TestingAuthenticationToken(user, a.getCredentials(),u.getAuthorities() );
            SecurityContextHolder.getContext().setAuthentication(newA);
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
        }
    }

    @Secured("ROLE_ADMIN")
    public String goBack(){
        if(a!=null){
            SecurityContextHolder.getContext().setAuthentication(a);
        }
        return "../Admin/index.xhtml?faces-redirect=true";
    }

    public boolean getChanged(){
        return a != null;
    }
}