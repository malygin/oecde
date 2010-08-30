package org.sgu.oecde.core.authentication;

import java.io.Serializable;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * Слушатель, отлавливающий момент выхода пользователя из системы и удаляет его из кеша пользователей онлайн
 * @author ShihovMY
 */
public class LogoutListner implements LogoutHandler,Serializable{

    @Autowired
    private UsersInCache manager;

    private LogoutListner() {
    }
    
    private static final long serialVersionUID = 134L;

    /**
     * удаляет текущего пользователя из кеша пользователей онлайн
     * {@inheritDoc}
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if(authentication==null)
            return;
        AbstractUser user = (AbstractUser)authentication.getPrincipal();
        if(user!=null)
            manager.removeUserFromCache(user);
    }
}
