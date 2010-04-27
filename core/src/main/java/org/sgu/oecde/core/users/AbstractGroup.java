/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.users;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 *
 * @author ShihovMY
 */
public abstract class AbstractGroup<T extends AbstractPerson> extends BasicItem{
    private Set<T> persons;
    private String name;

    public AbstractGroup() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<T> getPersons() {
        return persons;
    }

    public void setPersons(Set<T> persons) {
        this.persons = persons;
    }
}
