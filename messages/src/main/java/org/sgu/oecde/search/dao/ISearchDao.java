/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.search.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface ISearchDao {
    public List search(Class type,String[] words) throws DataAccessException;
}
