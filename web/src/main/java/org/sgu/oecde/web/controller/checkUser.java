package org.sgu.oecde.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.SecurityContextHandler;

/**
 *
 * @author ShihovMY
 */
public class checkUser extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       try {
           AbstractUser user = SecurityContextHandler.getUser();
           if(user!=null){
               UserType type = UserType.fromRole((AbstractUser) user);
               if((mapping.findForward(type.name().toLowerCase())!=null))
                   return mapping.findForward(type.name().toLowerCase());
           }
           return mapping.findForward("error");
           
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
}
