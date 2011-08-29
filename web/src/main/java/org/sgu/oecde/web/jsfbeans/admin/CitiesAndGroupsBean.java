package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.education.dao.IGroupDao;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="citiesAndGroups")
@SessionScoped
public class CitiesAndGroupsBean implements Serializable{

    @ManagedProperty(value="#{groupDao}")
    private IGroupDao groupDao;
   
    @ManagedProperty(value="#{semesterGetter}")
    protected SemesterGetter semesterGetter;


    private List<NewEntry<City,List<NewEntry<Speciality,List<Group>>>>>cities;

    private static final long serialVersionUID = 169L;
    private int year;

    @Secured("ROLE_ADMIN")
    public List<NewEntry<City,List<NewEntry<Speciality,List<Group>>>>> getCities(){
        if(cities == null){
            year=semesterGetter.getCurrentYear();
            List<Object[]>l = groupDao.getAllGroupsAndCities(year);
            City c = null;
            Speciality s = null;
            NewEntry<City,List<NewEntry<Speciality,List<Group>>>>cityEntry = null;
            NewEntry<Speciality,List<Group>>specialityEntry = null;
            List<NewEntry<Speciality,List<Group>>> specList = null;
            List<Group>groupList = null;
            cities = new ArrayList<NewEntry<City, List<NewEntry<Speciality, List<Group>>>>>();
            for(Object[] e:l){
                Group g = (Group) e[0];
                City city = (City) e[1];
                if(city!=c){
                    specList = new ArrayList<NewEntry<Speciality, List<Group>>>();
                    cityEntry = new NewEntry(city, specList);
                    cities.add(cityEntry);
                }
                if(g.getSpeciality()!=s){
                    groupList = new ArrayList<Group>();
                    specialityEntry = new NewEntry<Speciality, List<Group>>(g.getSpeciality(), groupList);
                    specList.add(specialityEntry);
                }
                groupList.add(g);
                c = city;
                s = g.getSpeciality();
            }
        }
        return cities;
    }

    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void setSemesterGetter(SemesterGetter semesterGetter) {
        this.semesterGetter = semesterGetter;
    }

  

    public int getYear() {
        if (year==0) year=semesterGetter.getCurrentYear();
        return year;
    }
    
}
