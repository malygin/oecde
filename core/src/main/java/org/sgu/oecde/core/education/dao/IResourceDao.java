package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.springframework.dao.DataAccessException;
import org.sgu.oecde.core.education.resource.AbstractResource;

/**
 * @author ShihovMY
 * created 19.05.2010
 * дао для ресурсов умк
 * @todo добавить методы для получения списков ресурсов с учетом доступа
 * @param <T> extends AbstractResource
 */
public interface IResourceDao<T extends AbstractResource> extends IUpdateDao<T> {

    /**
     * получает лист ресурсов конкретного типа для данного учебного плана.
     * запрос формируется следующим образом: к учебному плану джойнятся все ресурсы,
     * параллельно получаются все ресурсы данного типа, равные заданным параметрам ресурса-образца.
     * Возвращаются все ресурсы данного типа, что есть в данном учебном плане.
     * @param curriculums - учебный план
     * @param resource - ресурс-образец
     * @param type - тип ресурса
     * @return лист ресурсов
     * @throws DataAccessException
     */
    public List<T> getResourceByCurriculums(List<? extends Curriculum> curriculums, Long resourceId, Class type)throws DataAccessException;
}
