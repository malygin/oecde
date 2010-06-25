package org.sgu.oecde.shedule.dao;

import java.util.List;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.users.Teacher;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IAdvancedCurriculumDao<T extends Curriculum> extends ICurriculumDao<T>{

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
    public List<StudentGroup> getGroupBySemesterYearTeacherDiscipline(Integer[] semester, int year, Teacher teacher,Discipline discipline) throws DataAccessException;

}
