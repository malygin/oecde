package org.sgu.oecde.web.jsfbeans;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.SecurityContextHandler;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 24.06.2010
 *
 */

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
    private String login;
    private String password;
    private boolean rememberMe;
    private boolean renderErrorEnter=false;
 
    public LoginBean() {
    }
    
    
    @PostConstruct
    public void redirectUser(){
       AbstractUser user = SecurityContextHandler.getUser();
        System.out.println("post "+user);
    }

    // This is the action method called when the user clicks the "login" button
    public String doLogin() throws IOException, ServletException
    {
       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
       RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                 .getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
       return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isRenderErrorEnter() { 
        return this.renderErrorEnter;
    }

    public void setRenderErrorEnter(boolean renderErrorEnter) {
        this.renderErrorEnter = renderErrorEnter;
    }


}
