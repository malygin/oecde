package org.sgu.oecde.web.authentication;

import javax.annotation.Resource;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.Journal;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 *
 * @author ShihovMY
 */
public class LoginListener implements ApplicationListener{

    @Resource
    Journal journal;

    private LoginListener() {
        System.out.println();
    }

    public void onApplicationEvent(ApplicationEvent appEvent){
        if (appEvent instanceof AuthenticationSuccessEvent){
            AuthenticationSuccessEvent event = (AuthenticationSuccessEvent) appEvent;
            AbstractUser user = (AbstractUser) event.getAuthentication().getPrincipal();
            WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();
            journal.logSystemLogin(user, details.getRemoteAddress());
        }
    }
}
