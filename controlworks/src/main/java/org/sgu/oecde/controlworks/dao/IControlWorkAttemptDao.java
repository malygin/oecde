package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IControlWorkAttemptDao extends IBasicDao<ControlWorkAttempt>{

    @SuppressWarnings("unchecked")
    public List<ControlWorkAttempt> getAttemptsList(int beginIndex, int endIndex, List<AbstractStudent>students,List<Curriculum>curriculums) throws DataAccessException;
}
