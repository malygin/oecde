package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public class ResultDao<T extends AbstractResult> extends BasicDao<T> implements IResultDao<T>{

    protected ResultDao(Class<T> type) {
        super(type);
    }

    protected ResultDao(){
        super((Class<T>)AbstractResult.class);
    }

    @Override
    public List<T> getByStudentsAndCurriculums(List<? extends Curriculum> curriculums, List<? extends AbstractStudent> students, T result) throws DataAccessException {
        Criteria cr =  getSession().createCriteria(type);
        if(result!=null){
            result.setCurriculum(null);
            result.setStudent(null);
            getCriteriaByParametrizedItem(result, cr);
        }
        cr.add(Property.forName("curriculum").in(curriculums)).add(Property.forName("student").in(students));
        return cr.list();
    }

}
