package org.sgu.oecde.tabs.dao;

import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.tabs.Page;
import org.sgu.oecde.tabs.PageFile;
import org.sgu.oecde.tabs.Tab;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
@Repository
public class PageDao extends UpdateDao<Page> implements IPageDao{

    protected PageDao() {
        super(Page.class);
    }

   
}
