package org.sgu.oecde.de.users;

import org.sgu.oecde.de.education.City;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.users.StudentGroup;
import org.springframework.util.StringUtils;

/**
 * студенческая группа дистанционного образования
 * @author ShihovMY
 */
public class Group extends StudentGroup implements Comparable<Group>{
    /**
     * специальность
     */
    private Speciality speciality;
    /**
     * город
     */
    private City city;
    /**
     * факультет
     */
    private String faculty;

    private static final long serialVersionUID = 60L;

    public Group(Long id) {
        setId(id);
    }

    public Group() {
    }

    public Group(City city) {
        this.city = city;
    }

    /**
     *
     * @param <T> extends City
     * @return город
     */
    public <T extends City> T getCity() {
        return (T) city;
    }

    /**
     * город
     * @param city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     *
     * @param <T> extends Speciality
     * @return специальность
     */
    public <T extends Speciality> T getSpeciality() {
        return (T) speciality;
    }

    /**
     * специальность
     * @param speciality
     */
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        if(city!=null&&StringUtils.hasText(city.getName()))
            sb.append("город: ").append(city.getName()).append("; ");
        if(speciality!=null&&StringUtils.hasText(speciality.getName()))
            sb.append("специальность: ").append(speciality.getName()).append("; ");
        return sb.toString();
    }

    @Override
    public int compareTo(Group o) {
        int city = 0;
        int speciality = 0;
        int group = 0;
        if(this!= null && o != null && this.getCity()!=null && o.getCity() != null
                && this.getSpeciality()!=null && o.getSpeciality() != null && this.getName() != null
                && this.getSpeciality().getName()!= null && this.getCity().getName()!=null){
            group = this.getName().compareTo(o.getName());
            speciality = this.getSpeciality().getName().compareTo(o.getSpeciality().getName());
            city = this.getCity().getName().compareTo(o.getCity().getName());
        }
        return city==0?(speciality==0?group:speciality):city;
    }
}
