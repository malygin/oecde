package org.sgu.oecde.discussion.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Node;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc }
 */
@Repository
public class NodeDao extends UpdateDao<Node> implements INodeDao{
    private final  String GET_BY_PAGE="Select n from Node n JOIN n.root r where r.objectId=:id and r.objectType=:type order by n.time desc";

    protected NodeDao() {
        super(Node.class);
    }

    private static final long serialVersionUID = 163L;

    @Transactional
    public void delete(Node node) throws DataAccessException  {
        getSession().delete(node);
    }

    @Transactional
    public void save(Node node)  throws DataAccessException {
        getSession().merge(node);
    }

    @Override
    public List<Node> getByPage(Long id, ForumTypes type, int nodeOnPage, int numPage) throws DataAccessException {
       return getSession().createQuery(GET_BY_PAGE).
                setLong("id", id).
                setString("type", type.toString()).
                setFirstResult(nodeOnPage * (numPage-1)).setMaxResults(nodeOnPage).list();
    }
}
