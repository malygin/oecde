package org.sgu.oecde.core.education.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
public class ResultDao<T extends AbstractResult> extends BasicDao<T> implements IResultDao<T>{

    protected ResultDao(Class<T> type) {
        super(type);
    }

    protected ResultDao(){
        super((Class<T>)AbstractResult.class);
    }

    private static final long serialVersionUID = 133L;
    /**
     * {@inheritDoc }
     */
    @Override
    public List<T> getByStudentsAndCurriculums(Collection<? extends Curriculum> curriculums, Collection<? extends AbstractStudent> students, T result) throws DataAccessException {
        if(CollectionUtils.isEmpty(curriculums)||CollectionUtils.isEmpty(students))
            return null;
        Criteria cr =  getSession().createCriteria(type);
        if(result!=null){
            result.setCurriculum(null);
            result.setStudent(null);
            getCriteriaByParametrizedItem(result, cr);
        }
        cr.add(Property.forName("curriculum").in(curriculums)).add(Property.forName("student").in(students));
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return cr.setCacheable(false).list();
    }

    @Transactional
    public void deleteAllExceptEstimates() throws DataAccessException {
        getSession().createQuery("delete from "+type.getSimpleName()+" r where r.class!="+Estimate.class);
    }
}