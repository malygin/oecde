package org.sgu.oecde.discussion.dao;

import java.util.Set;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.discussion.Node;
import org.sgu.oecde.discussion.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface INodeDao extends IBasicDao<Node>{

    @Transactional
    public void delete(Node node) throws DataAccessException  ;

    @Transactional
    public void save(Node node)  throws DataAccessException;

    @Transactional
    public void update(Node node)  throws DataAccessException;
}
