package org.sgu.oecde.core.users;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.providers.dao.cache.EhCacheBasedUserCache;
/**
 *
 * @author ShihovMY
 */
public class UsersInCache extends EhCacheBasedUserCache {

    private UsersInCache() {
    }

    public List getAllUsers(Class type){
        List list = new ArrayList();
        for(Object k:getCache().getKeysWithExpiryCheck()){
            list.add(getCache().get(k).getValue());
        }
        return list;
    }


}
