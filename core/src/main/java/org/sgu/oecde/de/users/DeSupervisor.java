/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.de.users;

import org.sgu.oecde.de.education.City;

/**
 *
 * @author ShihovMY
 */
public class DeSupervisor extends org.sgu.oecde.core.users.Supervisor{
    private City city;
    private static final long serialVersionUID = 69L;

    public DeSupervisor() {
    }

    public <T extends City> T getCity() {
        return (T) city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
