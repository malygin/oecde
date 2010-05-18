/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.discussion.dao;

import org.springframework.dao.DataAccessException;

import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.discussion.Node;

/**
 * @author Basakovvy
 */
public class NodeDao extends BasicDao<Node> implements INodeDao{

    protected NodeDao() {
        super(Node.class);
    }

    public void delete(Node node) throws DataAccessException  {
        getSession().delete(node);
    }

    public void save(Node node)  throws DataAccessException {
        getSession().save(node);
    }

    public void update(Node node)  throws DataAccessException {
        getSession().update(node);
    }
}