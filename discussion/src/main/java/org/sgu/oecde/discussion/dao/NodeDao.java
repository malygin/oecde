package org.sgu.oecde.discussion.dao;

import org.springframework.dao.DataAccessException;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.discussion.Node;
import org.springframework.stereotype.Repository;

/**
 * {@inheritDoc }
 */
@Repository
public class NodeDao extends UpdateDao<Node> implements INodeDao{

    protected NodeDao() {
        super(Node.class);
    }

    /**
     * {@inheritDoc }
     */
    public void delete(Node node) throws DataAccessException  {
        getSession().delete(node);
    }

    /**
     * {@inheritDoc }
     */
    public void save(Node node)  throws DataAccessException {
        getSession().save(node);
    }
}