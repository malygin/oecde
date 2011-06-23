/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web.jsfbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.LangEnum;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.springframework.util.Assert;

/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date Jun 23, 2011
 */
@ManagedBean(name="userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable{

    protected LangEnum lang= LangEnum.ru;

    private static final long serialVersionUID = 154L;

    public UserSessionBean(){
     
    }

    public LangEnum getLang() {
        return lang;
    }

    public void setLang(LangEnum lang) {
        this.lang = lang;
    }

    
   
}
