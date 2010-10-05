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
public class TabsDao extends UpdateDao<Tab> implements ITabsDao{

    protected TabsDao() {
        super(Tab.class);
    }

    @Transactional
    @Override
    public void delete(Tab tab) throws DataAccessException {
        if(tab!=null && tab.getPages()!=null){
            for (Page p:tab.getPages()){
                if(p.getFiles()!=null){
                    for(PageFile f:p.getFiles()){
                        getSession().delete(f);
                    }
                }
                p.setFiles(null);
                getSession().flush();
                getSession().delete(p);
            }
            tab.setPages(null);
            getSession().flush();
            getSession().delete(tab);
        }
    }

    @Transactional
    @Override
    public void removeFile(PageFile pageFile)throws DataAccessException {
        getSession().delete(pageFile);
    }
}
