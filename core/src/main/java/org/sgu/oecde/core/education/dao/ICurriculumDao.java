/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.education.dao;

import java.util.List;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Curriculum;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.AbstractTeacher;
import org.sgu.oecde.core.users.StudentGroup;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface ICurriculumDao<T extends Curriculum> extends IBasicDao<T>{
    
    @SuppressWarnings("unchecked")
    public List<T> getCurriculumsByModSemester(T item, Integer[] semesters) throws DataAccessException ;

    @SuppressWarnings("unchecked")
    public List<T> getBySemesterYearAndParameters(Integer[] semester, int year, AbstractTeacher teacher) throws DataAccessException ;

    @SuppressWarnings("unchecked")
    public <E extends AbstractTeacher> List<E> getTeachersByGroup(Integer[] semester, int year, StudentGroup group) throws DataAccessException ;

    @SuppressWarnings("unchecked")
    public <E extends StudentGroup> List<E> getGroupsForTeacher(Integer[] semester, int year, AbstractTeacher teacher) throws DataAccessException;
}
