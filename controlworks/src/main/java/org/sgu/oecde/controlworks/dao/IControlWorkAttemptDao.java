package org.sgu.oecde.controlworks.dao;

import java.util.List;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;

/**
 * дао попыток кр
 * @author ShihovMY
 */
public interface IControlWorkAttemptDao extends IUpdateDao<ControlWorkAttempt>{
    /**
     * возвращает все контрольные работы данных студентов по данным учебным планам с {@code beginIndex}
     * no {@code endIndex}
     * @param beginIndex c
     * @param endIndex по
     * @param students студенты
     * @param curriculums учебные планы
     * @return попытки по кр
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<ControlWorkAttempt> getAttemptsList(int beginIndex, int endIndex, List<? extends StudentGroup>groups,List<? extends Curriculum>curriculums, String filterType) throws DataAccessException;
    
    /**
     * 
     * @param curriculums
     * @return
     * @throws DataAccessException
     */
    public int getAttemptCountForTeacher(List<? extends Curriculum>curriculums,Teacher teacher, boolean readOnly) throws DataAccessException ;
}
