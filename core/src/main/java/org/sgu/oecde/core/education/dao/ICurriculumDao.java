package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.StudentGroup;
import org.springframework.dao.DataAccessException;

/**
 * интерфейс для CurriculumDao. наследуется от IBasicDao
 * @author ShihovMY
 * @param <T> extends Curriculum
 * @see org.sgu.oecde.core.IBasicDao
 * @see org.sgu.oecde.core.education.dao.CurriculumDao
 */
public interface ICurriculumDao<T extends Curriculum> extends IBasicDao<T>{

    /**
     * @param item образец
     * @param semesters массив чётных/нечётных семестров
     * @return лист учебных планов
     * @throws DataAccessException
     * @see org.sgu.oecde.core.BasicDao#getCriteriaByParametrizedItem(org.sgu.oecde.core.BasicItem, org.hibernate.Criteria) получение по образцу
     */
    @SuppressWarnings("unchecked")
    public List<T> getCurriculumsByModSemester(T item, Integer[] semesters) throws DataAccessException ;

    /**
     * @param semester массив чётных/нечётных семестров
     * @param year год
     * @param teacher преподаватель
     * @return лист учебных планов
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public List<T> getBySemesterYearAndParameters(Integer[] semester, int year, Teacher teacher) throws DataAccessException ;

    /**
     * получает список преподавателей, которые ведут у данной группы в данном году в данных семестрах
     * @param <E> extends Teacher
     * @param semester - семестр
     * @param year - год
     * @param group - группа
     * @return список преподавателей
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public <E extends Teacher> List<E> getTeachersByGroup(Integer[] semester, int year, StudentGroup group) throws DataAccessException ;

    /**
     * получает список групп, у которых данный преподаватель ведёт в данном году в данных семестрах
     * @param <E> extends StudentGroup
     * @param semester семестр
     * @param year год
     * @param teacher преподаватель
     * @return список групп
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    public <E extends StudentGroup> List<E> getGroupsForTeacher(Integer[] semester, int year, Teacher teacher) throws DataAccessException;
}
