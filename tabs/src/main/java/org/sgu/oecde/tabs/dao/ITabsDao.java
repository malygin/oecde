package org.sgu.oecde.tabs.dao;

import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.tabs.PageFile;
import org.sgu.oecde.tabs.Tab;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface ITabsDao extends IUpdateDao<Tab>{
    public void delete(Tab tab)throws DataAccessException;
    public void removeFile(PageFile pageFile)throws DataAccessException;
}
