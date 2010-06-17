package org.sgu.oecde.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

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
            SecurityContext context = SecurityContextHolder.getContext();
            if (context instanceof SecurityContext){
                Authentication authentication = context.getAuthentication();
                if (authentication instanceof Authentication){
                    Object principal = authentication.getPrincipal();
                    if(principal instanceof AbstractUser){
                        UserType type = UserType.fromRole((AbstractUser) principal);
                        if((mapping.findForward(type.name().toLowerCase())!=null))
                            return mapping.findForward(type.name().toLowerCase());
                    }
                }
            }
            return mapping.findForward("error");
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("error");
        }
    }
}
