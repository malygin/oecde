package org.sgu.oecde.web.jsfbeans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
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
public class LoginBean implements Serializable{
    
    private boolean renderErrorEnter=false;

    private static final long serialVersionUID = 193L;

    public LoginBean() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        CustomAuthenticationSuccessHandler.doRedirect((HttpServletRequest)context.getRequest(), (HttpServletResponse)context.getResponse(),false);
    }

    public boolean isRenderErrorEnter() { 
        return this.renderErrorEnter;
    }

    public void setRenderErrorEnter(boolean renderErrorEnter) {
        this.renderErrorEnter = renderErrorEnter;
    }
}