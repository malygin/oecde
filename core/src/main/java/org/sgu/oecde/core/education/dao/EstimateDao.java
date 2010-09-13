package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@Repository
public class EstimateDao extends ResultDao<Estimate> implements IEstimateDao{

    public EstimateDao() {
        super(Estimate.class);
    }

    private static final long serialVersionUID = 131L;

    public int getEstimatedGroupsCount(List<? extends Curriculum>curriculums,Teacher teacher)throws DataAccessException {
        List<Long>list = getSession()
                .createQuery("select count(distinct gr) from Estimate e join e.curriculum cr join cr.teacherToGroups tg join tg.group gr join gr.persons p where cr in (:crs) and e.student in (p) and tg.teacher=:tch")
                .setParameterList("crs", curriculums).setParameter("tch", teacher).list();
        return !CollectionUtils.isEmpty(list)?Long.valueOf(list.get(0)).intValue():0;
    }

    @Transactional
    public void save(Estimate result) throws DataAccessException {
        getSession().saveOrUpdate(result);
    }
}
