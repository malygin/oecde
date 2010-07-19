package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.Estimate;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IEstimateDao extends IResultDao<Estimate>{

    public int getEstimatedGroupsCount(List<? extends Curriculum>curriculums,Teacher teacher)throws DataAccessException ;

}
