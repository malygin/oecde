/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core;

import java.util.Collection;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
@Transactional
public interface IAdvancedDao<T extends BasicItem>extends IBasicDao<T> {
    public List<T> getByParametersCollection(Collection collection, String field) throws DataAccessException;
}
