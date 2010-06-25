package org.sgu.oecde.de.users;

import org.sgu.oecde.de.education.City;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.users.StudentGroup;

/**
 * студенческая группа дистанционного образования
 * @author ShihovMY
 */
public class Group extends StudentGroup{
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
        if(city!=null&&!city.getName().isEmpty())
            sb.append("город: ").append(city.getName()).append("; ");
        if(speciality!=null&&!speciality.getName().isEmpty())
            sb.append("специальность: ").append(speciality.getName()).append("; ");
        return sb.toString();
    }
}
