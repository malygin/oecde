package org.sgu.oecde.discussion.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public class RootDao extends BasicDao<Root> implements IRootDao{

    protected RootDao() {
        super(Root.class);
    }

    @SuppressWarnings("unchecked")
    public int getNodesCount(int idObject, ForumTypes typeObject)  throws DataAccessException {
        Criteria cr = getSession().createCriteria(type).createAlias("children", "ch")
                .add(Property.forName("objectId").eq(idObject))
                .add(Property.forName("objectType").eq(typeObject))
                .setProjection(Projections.projectionList()
                .add(Projections.rowCount(), "ch"));
        List<Integer> list =  cr.list();
        return !CollectionUtils.isEmpty(list)?list.get(0):0;
    }

    public void save(Root root) throws DataAccessException {
        getSession().save(root);
    }

    public void update(Root root) throws DataAccessException {
        getSession().update(root);
    }
}
