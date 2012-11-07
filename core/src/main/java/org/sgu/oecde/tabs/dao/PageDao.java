package org.sgu.oecde.tabs.dao;

import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.tabs.Page;
import org.springframework.stereotype.Repository;

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
