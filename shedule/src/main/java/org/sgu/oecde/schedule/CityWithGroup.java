package org.sgu.oecde.schedule;

import java.io.Serializable;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.users.Group;

/**
 *
 * @author ShihovMY
 */
public class CityWithGroup implements Serializable,Comparable<CityWithGroup>{
    
    private static final long serialVersionUID=101L;

    private City city;

    private Group group;

    public CityWithGroup() {
    }

    public CityWithGroup(City city, Group group) {
        this.city = city;
        this.group = group;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CityWithGroup other = (CityWithGroup) obj;
        if (this.city != other.city && (this.city == null || !this.city.equals(other.city))) {
            return false;
        }
        if (this.group != other.group && (this.group == null || !this.group.equals(other.group))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 47 * hash + (this.group != null ? this.group.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(CityWithGroup o) {
        int cityI = 0;
        int groupI = 0;
        if(o!=null&&o.getCity()!=null&&o.getGroup()!=null&&o.getCity().getName()!=null&&o.getGroup().getName()!=null){
            cityI = o.getCity().getName().compareTo(getCity().getName());
            groupI = o.getGroup().compareTo(getGroup());
        }
        return cityI==0?groupI:cityI;
    }
}
