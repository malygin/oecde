package org.sgu.oecde.core.authentication;

import javax.annotation.Resource;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.UsersInCache;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.Authentication;
import org.springframework.security.event.authorization.AuthorizedEvent;

/**
 *
 * @author ShihovMY
 */
public class EventListner implements ApplicationListener{

    @Resource
    private UsersInCache userCache;

    private EventListner() {
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
            userCache.putUserInCache((AbstractUser)a.getPrincipal());
        }
    }
}
