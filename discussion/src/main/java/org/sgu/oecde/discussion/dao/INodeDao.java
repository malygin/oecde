package org.sgu.oecde.discussion.dao;

import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.discussion.Node;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface INodeDao extends IUpdateDao<Node>{

    @Transactional
    public void delete(Node node) throws DataAccessException  ;

    @Transactional
    public void save(Node node)  throws DataAccessException;
}
