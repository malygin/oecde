package org.sgu.oecde.core.users;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 * группа пользователей. от неё наследуются департаменты с преподавателями, студенческие группы.
 * входить могут только пользователи-люди
 * @author ShihovMY
 */
public abstract class AbstractGroup<T extends AbstractPerson> extends BasicItem{
    /**
     * пользователи
     */
    private Set<T> persons;
    /**
     * название группы
     */
    private String name;

    public AbstractGroup() {
    }

    /**
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    /**
     * название
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return пользователи
     */
    public Set<T> getPersons() {
        return persons;
    }

    /**
     * пользователи
     * @param persons
     */
    public void setPersons(Set<T> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
            sb.append("название: ").append(getName()).append("; ");
        return sb.toString();
    }
}
