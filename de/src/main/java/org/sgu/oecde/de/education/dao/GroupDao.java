package org.sgu.oecde.de.education.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.ScrollableResults;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author ShihovMY
 */
public class GroupDao extends UpdateDao<Group> implements IGroupDao{

    public GroupDao() {
        super(Group.class);
    }

    /**
     * {@inheritDoc }
     */
    public Group getTeachersAndCurriculumsByOldGroup(int year, Student student) throws DataAccessException {
        if(student == null&& student.getId()==0)
            return null;
        List<Group>l = (List<Group>) getSession()
                .createQuery("select gr from Group gr join gr.persons p,Student st where st=:st and st in (p) and gr.year=:y")
                .setParameter("y", year).setParameter("st", student).setCacheable(true)
                .list();
        return l.isEmpty()?null:l.get(0);
    }

    @Override
    public List<Object[]> getAllGroupsAndCities() throws DataAccessException{
        List<Object[]>m = new ArrayList<Object[]>();
        ScrollableResults r = getSession().createQuery("select distinct g,c from Student st join st.city c join st.group g join fetch g.speciality s order by c.name,s.name").scroll();
        while(r.next()){
            m.add(r.get());
        }
        return m;
    }

    public List<Group> getGroupsByCity(City city) throws DataAccessException{
        return getSession().createQuery("select distinct g from Student st join st.city c join st.group g join fetch g.speciality where c=:c order by g.name")
                .setParameter("c", city).list();
    }
}