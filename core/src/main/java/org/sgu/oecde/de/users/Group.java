/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.de.users;

import org.sgu.oecde.de.education.City;
import org.sgu.oecde.core.education.Speciality;
import org.sgu.oecde.core.users.StudentGroup;

/**
 *
 * @author ShihovMY
 */
public class Group extends StudentGroup{
    private Speciality speciality;
    private City city;

    private static final long serialVersionUID = 60L;

    public Group(Long id) {
        setId(id);
    }

    public Group() {
    }

    public <T extends City> T getCity() {
        return (T) city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public <T extends Speciality> T getSpeciality() {
        return (T) speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
