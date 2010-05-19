package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.AbstractStudent;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ShihovMY
 */
public interface IControlWorkDao extends IBasicDao<ControlWork>{

    @SuppressWarnings("unchecked")
    public List<ControlWork> getByStudentsAndCurriculums(List<? extends AbstractStudent>students,List<? extends Curriculum>curriculums) throws DataAccessException;
    
    @SuppressWarnings("unchecked")
    public List<ControlWork> getByStudentAndCurriculums(List<? extends Curriculum>curriculums, AbstractStudent student) throws DataAccessException;

    @SuppressWarnings("unchecked")
    public List<ControlWork> getByStudentsAndCurriculum(List<? extends AbstractStudent>students, Curriculum curriculum) throws DataAccessException;

    @Transactional
    public void save(ControlWork work) throws DataAccessException;
}
