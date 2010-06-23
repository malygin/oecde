package org.sgu.oecde.core.users;

import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * аоп аспект. ищет пользователей онлайн в кеше перед получением их из бд
 * @author ShihovMY
 */
@Aspect
public class UserOnlineSetterAspect {
    @Autowired
    private UsersInCache cache;

    private UserOnlineSetterAspect() {
    }

    /**
     * перед вызовом любого из методов дао пользователей на получение пользователей,
     * проверяет наличие полученных пользователей в кеше пользователей онлайн. Если
     * они там есть, то устанавливает соответствующее значение у этих пользователей/
     * после чего
     * @param pjp
     * @return полученные пользователи
     * @throws Throwable
     */
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