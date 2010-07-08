package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.users.AbstractUser;
import java.util.List;
import javax.annotation.Resource;
import org.sgu.oecde.core.IBasicDao;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * {@inheritDoc }
 * @author ShihovMY
 */
public class UserDetailsServiceImpl implements UserDetailsService{

    @Resource
    IBasicDao<AbstractUser> userDao;

    private UserDetailsServiceImpl() {
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        AbstractUser example = AbstractUser.getUserWithName(username);
        List<AbstractUser> l = userDao.getBySimpleExample(example);
        return (UserDetails) (l.size() > 0 ? l.get(0) : null);
    }
}
