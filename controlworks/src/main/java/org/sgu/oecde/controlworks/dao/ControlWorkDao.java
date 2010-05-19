package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.core.BasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public class ControlWorkDao extends BasicDao<ControlWork> implements IControlWorkDao{

    protected ControlWorkDao(){
        super(ControlWork.class);
    }

    public List<ControlWork> getByStudentsAndCurriculums(List<? extends AbstractStudent>students,List<? extends Curriculum>curriculums) throws DataAccessException{
        Criteria cr = getSession().createCriteria(type);
        cr.add(Property.forName("student").in(students))
                .add(Property.forName("progress").ne(ControlWorkProgress.passed))
                .add(Property.forName("curriculum").in(curriculums))
                .addOrder(Order.asc("curriculum"))
                .addOrder(Order.asc("student"));
        return cr.list();
    }

    public List<ControlWork> getByStudentAndCurriculums(List<? extends Curriculum>curriculums, AbstractStudent student) throws DataAccessException{
        Criteria cr = getSession().createCriteria(type);
        cr.add(Property.forName("curriculum").in(curriculums))
                .addOrder(Order.asc("curriculum"));
        return cr.list();
    }
    public List<ControlWork> getByStudentsAndCurriculum(List<? extends AbstractStudent>students, Curriculum curriculum) throws DataAccessException{
        Criteria cr = getSession().createCriteria(type);
        cr.add(Property.forName("student").in(students))
                .addOrder(Order.asc("student"));
        return cr.list();
    }

    public void save(ControlWork work) throws DataAccessException{
        getSession().saveOrUpdate(work);
    }
}
