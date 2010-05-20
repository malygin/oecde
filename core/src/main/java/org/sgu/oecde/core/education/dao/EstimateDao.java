package org.sgu.oecde.core.education.dao;

import java.util.Collection;
import java.util.List;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.work.Estimate;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public class EstimateDao<T extends Estimate> extends BasicDao<T> implements IEstimateDao<T>{

    @SuppressWarnings("unchecked")
    protected EstimateDao() {
        super((Class<T>) Estimate.class);
    }

    protected EstimateDao(Class<T> type){
        super(type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getGroupGrades(Collection students,Collection curriculums) throws DataAccessException{
       /* Criteria cr =  getSession().createCriteria(type);
        cr = byParametersList(cr, students, "student");
        cr = byParametersList(cr, curriculums, "curriculum");*/
        return null;//cr.list();
    }
}
