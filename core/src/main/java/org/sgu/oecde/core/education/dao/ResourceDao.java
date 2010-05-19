
package org.sgu.oecde.core.education.dao;

import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 19.05.2010
 * 
 */
public class ResourceDao <T extends AbstractResource> extends BasicDao<T> implements IResourceDao<T> {

    @Override
    public void update(T item) throws DataAccessException {
       Assert.isInstanceOf(type,item );
       getSession().update(item);
    }

    @Override
    public void insert(T item) throws DataAccessException {
      Assert.isInstanceOf(type,item );
      getSession().saveOrUpdate(item);
    }

}
