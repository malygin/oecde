/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface IUpdateDao<T extends BasicItem> extends IBasicDao<T>  {
    @Transactional
    public void update(T item) throws DataAccessException;
}
