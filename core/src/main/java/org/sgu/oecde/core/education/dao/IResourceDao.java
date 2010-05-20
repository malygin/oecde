
package org.sgu.oecde.core.education.dao;

import org.sgu.oecde.core.IBasicDao;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.sgu.oecde.core.education.resource.AbstractResource;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 19.05.2010
 * дао для ресурсов умк
 * @todo добавить методы для получения списков ресурсов с учетом доступа
 */
public interface IResourceDao<T extends AbstractResource> extends IBasicDao<T> {
    @Transactional
    public void update(T item) throws DataAccessException;

    @Transactional
    public void insert(T item) throws DataAccessException;

    @Transactional
    public void delete(T item) throws DataAccessException;
}
