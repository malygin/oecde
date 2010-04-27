/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.users;

import java.util.List;
import org.springframework.security.providers.dao.UserCache;

/**
 *
 * @author ShihovMY
 */
public interface IUsersInCache extends UserCache{
    public List getAllUsers();
}
