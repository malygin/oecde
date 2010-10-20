package org.sgu.oecde.web.authentication;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.core.util.SwitchedUserCheker;
import org.sgu.oecde.journal.Journal;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author ShihovMY
 */
public class CustomAuthenticationSuccessHandler implements  AuthenticationSuccessHandler {


    @Resource
    Journal journal;

    protected CustomAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        doRedirect(request,response,true);
        if(!SwitchedUserCheker.check(authentication.getAuthorities()))
            journal.logSystemLogin(SecurityContextHandler.getUser(), request.getRemoteAddr());
    }

    public static void doRedirect(HttpServletRequest request, HttpServletResponse response, boolean returnToIndex)throws IOException, ServletException {
         UserType type = SecurityContextHandler.getUserType();
         if(type!=null){
             response.sendRedirect(request.getContextPath()+"/"+type.toNameFolder());
         }else
             if(returnToIndex)
                response.sendRedirect(request.getContextPath()+"/"+"index.xhtml");
    }
}