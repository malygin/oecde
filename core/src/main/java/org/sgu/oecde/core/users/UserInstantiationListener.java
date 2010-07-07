package org.sgu.oecde.core.users;

import org.hibernate.event.PostLoadEvent;
import org.hibernate.event.def.DefaultPostLoadEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * слушатель события пост загрузки сущности из базы
 * @author ShihovMY
 */
@Service
public class UserInstantiationListener extends DefaultPostLoadEventListener{
    @Autowired
    private UsersInCache cache;

    /**
     * если сущность события - юзер, то проверяет его наличие в кеше пользователей онлайн,
     * и если он там есть, то ставит параметр онлайн - тру
     * @param event
     */
    @Override
    public void onPostLoad(PostLoadEvent event) {
        if(event!=null){
            if(event.getEntity() instanceof AbstractUser){
                if(cache.isUserInCache((AbstractUser) event.getEntity()))
                    ((AbstractUser) event.getEntity()).setOnline(true);
            }
        }
    }
}
