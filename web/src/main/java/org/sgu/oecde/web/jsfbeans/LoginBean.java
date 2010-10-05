package org.sgu.oecde.web.jsfbeans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.web.authentication.CustomAuthenticationSuccessHandler;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 24.06.2010
 *
 */

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {
    private boolean renderErrorEnter=false;

    public LoginBean() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        CustomAuthenticationSuccessHandler.doRedirect((HttpServletRequest)context.getRequest(), (HttpServletResponse)context.getResponse(),false);
    }
    
    public void doLogin(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                 .getRequestDispatcher("/j_spring_security_check");
        try {
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        } catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    public boolean isRenderErrorEnter() { 
        return this.renderErrorEnter;
    }

    public void setRenderErrorEnter(boolean renderErrorEnter) {
        this.renderErrorEnter = renderErrorEnter;
    }

}
