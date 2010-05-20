package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.springframework.dao.DataAccessException;
import org.sgu.oecde.core.education.resource.AbstractResource;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 19.05.2010
 * дао для ресурсов умк
 * @todo добавить методы для получения списков ресурсов с учетом доступа
 */
public interface IResourceDao<T extends AbstractResource> extends IUpdateDao<T> {

    public List<T> getResourceByCurriculums(List<? extends Curriculum> curriculums,AbstractResource resource, Class type)throws DataAccessException;
}
