/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.Authentication;
import org.springframework.security.event.authorization.AuthorizedEvent;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class EventListner implements ApplicationListener,InitializingBean{
    
    private UsersInCache manager;

    protected EventListner() {
    }

    /**
	 * 
	 * @param apEvent
	 * @return 
	 */
	@Override
    public void onApplicationEvent(ApplicationEvent apEvent) {
        if (apEvent instanceof AuthorizedEvent)    {
            AuthorizedEvent event = (AuthorizedEvent) apEvent;
            Authentication a =  event.getAuthentication();
            manager.putUserInCache((AbstractUser)a.getPrincipal());
        }
    }

    public void setManager(UsersInCache manager) {
        this.manager = manager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(manager, "cache manager is null");
    }
}
