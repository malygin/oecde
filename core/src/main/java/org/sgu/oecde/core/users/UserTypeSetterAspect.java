package org.sgu.oecde.core.users;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheFactoryBean;

/**
 *
 * @author ShihovMY
 */
@Aspect
public class UserTypeSetterAspect {
    @Autowired
    EhCacheFactoryBean userCacheBackend;

    private UserTypeSetterAspect() {
    }

    @Around(" execution (* org.springframework.security.providers.dao.cache.EhCacheBasedUserCache.*(org.springframework.security.userdetails.UserDetails))" +
            " || execution (* org.sgu.oecde.core.users.UsersInCache.*(java.lang.Class)) " +
            " || execution (* org.springframework.security.providers.dao.cache.EhCacheBasedUserCache.*(java.lang.String))")
    public Object setType(ProceedingJoinPoint pjp) throws Throwable{
        return pjp.proceed();
    }
}
