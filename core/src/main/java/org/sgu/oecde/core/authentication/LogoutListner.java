package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.ui.logout.LogoutHandler;

/**
 *
 * @author ShihovMY
 */
public class LogoutListner implements LogoutHandler{

    @Autowired
    private UsersInCache manager;

    private LogoutListner() {
    }

    /**
     *
     * @param request
     * @param response
     * @param authentication
     * @return
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        manager.removeUserFromCache((AbstractUser)authentication.getPrincipal());
    }
}
