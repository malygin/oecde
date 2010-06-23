package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.hibernate.Query;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.util.HqlConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

/**
 * {@inheritDoc }
 */
public class ResourceDao <T extends AbstractResource> extends UpdateDao<T> implements IResourceDao<T> {

    protected ResourceDao() {
        super((Class<T>)AbstractResource.class);
    }

    /**
     * {@inheritDoc }
     */
    public List<T> getResourceByCurriculums(List<? extends Curriculum> curriculums,AbstractResource resource,Class type)throws DataAccessException{
        String byExample = null;
        Assert.notNull(type);
        if(resource!=null)
            byExample = "r=:e";
        Query q = HqlConstructor.makeQuery(getSession(), "distinct r ", "from Curriculum cr join cr.umk u join u.resources rs, "+type.getName()+" r",null, "cr in (:c) and r in (rs)", byExample, "r")
                .setParameterList("c", curriculums);
        if(resource!=null)
            q.setParameter("e", resource);
        return q.list();
    }
}
