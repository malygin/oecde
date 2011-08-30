package org.sgu.oecde.de.education.dao;

import java.util.List;
import java.util.Map;
import org.sgu.oecde.core.IUpdateDao;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public interface IGroupDao extends IUpdateDao<Group>{
    
    public Group getTeachersAndCurriculumsByOldGroup(int year, Student student) throws DataAccessException;
    public List<Object[]> getAllGroupsAndCities(int year) throws DataAccessException;
    public List<Group> getGroupsByCity(City city) throws DataAccessException;
}