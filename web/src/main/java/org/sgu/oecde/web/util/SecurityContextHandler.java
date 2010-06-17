package org.sgu.oecde.web.util;

import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UserType;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

/**
 *
 * @author ShihovMY
 */
public class SecurityContextHandler {

    private SecurityContextHandler() {
        throw new AssertionError();
    }

    public static AbstractUser getUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication){
                Object principal = authentication.getPrincipal();
                if(principal instanceof AbstractUser){
                    return (AbstractUser) principal;
                }
            }
        }
        return null;
    }

    public static UserType getUserType(){
        return UserType.fromRole(getUser());
    }
}
