/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public class UpdateDao<T extends BasicItem> extends BasicDao<T> implements IUpdateDao<T>{

    @Override
    public void update(T item) throws DataAccessException {
        Assert.isInstanceOf(type,item );
        getSession().update(item);
    }
}
