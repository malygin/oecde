package org.sgu.oecde.discussion.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.discussion.ForumTypes;
import org.sgu.oecde.discussion.Root;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
@Repository
public class RootDao extends UpdateDao<Root> implements IRootDao{

    protected RootDao() {
        super(Root.class);
    }

    private static final long serialVersionUID = 162L;

    /**
     * {@inheritDoc }
     */
    @SuppressWarnings("unchecked")
    public int getNodesCount(Long idObject, ForumTypes typeObject)  throws DataAccessException {
        if(idObject == null|| typeObject == null)
            return 0;
        Criteria cr = getSession().createCriteria(type).createAlias("children", "ch")
                .add(Property.forName("objectId").eq(idObject))
                .add(Property.forName("objectType").eq(typeObject))
                .setProjection(Projections.projectionList()
                .add(Projections.rowCount(), "ch"));
        List<Long> list =  cr.setCacheable(true).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }

    @SuppressWarnings("unchecked")
    public Root getRootByPage(Long idObject, ForumTypes typeObject,  int messageOnPage, int numPage)  throws DataAccessException {
        Root r =  new Root();
        Criteria cr = getSession().createCriteria(type)
                .add(Property.forName("objectId").eq(idObject))
                .add(Property.forName("objectType").eq(typeObject))
                .setFirstResult(messageOnPage * (numPage-1)).setMaxResults(messageOnPage);
        List l =  cr.setCacheable(true).list();
        return r;
    }


    @Transactional
    public void save(Root root) throws DataAccessException {
        getSession().save(root);
    }

  
}
