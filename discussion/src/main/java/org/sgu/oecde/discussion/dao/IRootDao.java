package org.sgu.oecde.discussion.dao;

import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface IRootDao extends IUpdateDao<Root>{

    public int getNodesCount(int idObject, ForumTypes typeObject)  throws DataAccessException ;

    @Transactional
    public void save(Root root) throws DataAccessException ;
}
