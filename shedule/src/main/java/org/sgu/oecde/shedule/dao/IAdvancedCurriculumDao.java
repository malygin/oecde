package org.sgu.oecde.shedule.dao;

import java.util.List;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Teacher;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface  IAdvancedCurriculumDao extends ICurriculumDao<DeCurriculum>{

    /**
     *
     * @param semester семестры
     * @param year год
     * @param teacher преподаватель
     * @return дисциплины преподавателя за указанные год и семестр
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<Discipline> getBySemesterYearTeacher(Integer[] semester, int year, Teacher teacher) throws DataAccessException;
    
    /**
     * 
     * @param semester семестры
     * @param year год
     * @param teacher преподаватель
     * @param discipline дисциплина
     * @return группы преподавателя по данной дисциплине в данных семестра в данном году
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<Group> getGroupBySemesterYearTeacherDiscipline(Integer[] semester, int year, Teacher teacher,Discipline discipline) throws DataAccessException;

}
