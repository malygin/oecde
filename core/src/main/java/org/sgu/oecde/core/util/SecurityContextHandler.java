package org.sgu.oecde.core.util;

import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.Guest;
import org.sgu.oecde.core.users.UserType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * методы по работе с авторизованным пользователем, находящемся в SecurityContext
 * @author ShihovMY
 */
public class SecurityContextHandler {

    private SecurityContextHandler() {
        throw new AssertionError();
    }

    /**
     *
     * @return авторизованного пользователя
     */
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

    /**
     *
     * @return тип авторизованного пользователя
     */
    public static UserType getUserType(){
        return UserType.toType(getUser());
    }
}
