package org.sgu.oecde.core.users;

import java.util.Iterator;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;

/**
 * группа пользователей. от неё наследуются департаменты с преподавателями, студенческие группы.
 * входить могут только пользователи-люди
 * @author ShihovMY
 */
public abstract class AbstractGroup<T extends AbstractPerson> extends BasicItem implements Iterable<T>{
    /**
     * пользователи
     */
    private Set<T> persons;
    /**
     * название группы
     */
    private String name;
    
    /**
     * group-ancestor, for faculty
     */
    private AbstractGroup<T> ancestor;
    

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
    public Iterator<T> iterator(){
        if(persons!=null)
            return persons.iterator();
        else
            return null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
            sb.append("название: ").append(getName()).append("; ");
        return sb.toString();
    }

    public AbstractGroup<T> getAncestor() {
        return ancestor;
    }

    public void setAncestor(AbstractGroup<T> ancestor) {
        this.ancestor = ancestor;
    }
    
    
}
