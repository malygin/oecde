package org.sgu.oecde.core.users;

import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ShihovMY
 */
@Aspect
public class UserOnlineSetterAspect {
    @Autowired
    UsersInCache cache;

    private UserOnlineSetterAspect() {

    }
    
    @Around("( bean (userDao) || bean (studentDao)) ||  bean (teacherDao) ||" +
            "bean (adminDao) || bean (supervisorDao)).get*(**)")
    public Object setType(ProceedingJoinPoint pjp) throws Throwable{
        Object o = pjp.proceed();
        if(o instanceof List)
            for (AbstractUser s:(List<AbstractUser>)o){
                if(cache.isUserInCache(s))
                    s.setOnline(true);
            }
        else if(o instanceof AbstractUser)
            if(cache.isUserInCache((AbstractUser) o))
                ((AbstractUser) o).setOnline(true);
        return pjp.proceed();
    }
}