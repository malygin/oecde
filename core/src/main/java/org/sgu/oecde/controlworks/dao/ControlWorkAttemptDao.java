package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * {@inheritDoc }
 */
@Repository
public class ControlWorkAttemptDao extends UpdateDao<ControlWorkAttempt> implements IControlWorkAttemptDao{

    protected ControlWorkAttemptDao() {
        super(ControlWorkAttempt.class);
    }

    private static final long serialVersionUID = 159L;

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ControlWorkAttempt> getAttemptsList(int beginIndex, int endIndex, List<? extends StudentGroup>groups,List<? extends Curriculum>curriculums, String filtertype) throws DataAccessException {
        Assert.state(beginIndex >= 0 || endIndex > 0 || beginIndex < endIndex ||!CollectionUtils.isEmpty(groups)||!CollectionUtils.isEmpty(curriculums),"wrong indexes");
            Criteria query =getSession().createCriteria(type).setFetchMode("w", FetchMode.JOIN)
                .createAlias("work", "w")
               // .setFirstResult(beginIndex).setMaxResults(endIndex)
                .setMaxResults(endIndex)
                .add(Property.forName("w.curriculum").in(curriculums))
                .createAlias("w.student", "st")
                .add(Property.forName("filePath").ne("empty"))
                .add(Property.forName("st.group").in(groups));
            
                 if (filtertype.equals("date")) query.addOrder(Order.desc("attemptDate"));
                 else if (filtertype.equals("read")) query.addOrder(Order.desc("read")); 
                 else{              
                    query.addOrder(Order.asc("read"))
                    .addOrder(Order.desc("attemptDate"));}
                
               
        
             return    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public int getAttemptCountForTeacher(List<? extends Curriculum>curriculums,Teacher teacher, boolean readOnly) throws DataAccessException {
        if(CollectionUtils.isEmpty(curriculums)||teacher == null)
            return 0;
        List<Long>list = getSession()
                .createQuery("select count(*) from ControlWorkAttempt cw join cw.work w join w.curriculum cr join cr.teacherToGroups tg join tg.group gr join gr.persons p where cr in (:crs) and w.student in (p) and tg.teacher=:tch and cw.filePath!='empty'"+(readOnly?" and cw.read='T' ":""))
                .setParameterList("crs", curriculums).setParameter("tch", teacher).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }
}
