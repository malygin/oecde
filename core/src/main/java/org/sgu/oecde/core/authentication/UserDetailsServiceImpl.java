/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.users.AbstractUser;
import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class UserDetailsServiceImpl implements UserDetailsService,InitializingBean{
   
    IBasicDao<AbstractUser> userDao;

    /**
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        AbstractUser example = AbstractUser.getUserWithName(username);
        List<AbstractUser> l = userDao.getByExamlpeItem(example);
        return l.size()>0?l.get(0):null;
    }

    public IBasicDao<AbstractUser> getUserDao() {
        return userDao;
    }

    public void setUserDao(IBasicDao<AbstractUser> userDao) {
        this.userDao = userDao;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userDao, "user dao is null");
    }
}
