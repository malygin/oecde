/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.Authentication;
import org.springframework.security.ui.logout.LogoutHandler;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class LogoutListner implements LogoutHandler,InitializingBean{

    private UsersInCache manager;

    protected LogoutListner() {
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

    public void setManager(UsersInCache manager) {
        this.manager = manager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(manager, "cache manager is null");
    }


}
