package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.work.AbstractResult;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IResultDao<T extends AbstractResult> extends IBasicDao<T>{
    @SuppressWarnings("unchecked")
    public List<T> getByStudentsAnsCurriculums(List<? extends Curriculum>curriculums,List<? extends AbstractStudent>students, T result) throws DataAccessException;
}
