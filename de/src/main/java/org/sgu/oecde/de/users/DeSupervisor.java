package org.sgu.oecde.de.users;

import org.sgu.oecde.de.education.City;

/**
 * наблюдатель дистанционного образования. привязан к представительству города
 * @author ShihovMY
 */
public class DeSupervisor extends org.sgu.oecde.core.users.Supervisor{
    /**
     * город
     */
    private City city;
    private static final long serialVersionUID = 69L;

    public DeSupervisor() {
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
      public String getClassType(){
         return "supervisor";
    }
}
