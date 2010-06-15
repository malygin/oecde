package org.sgu.oecde.core.education.resource;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractTeacher;

/**
 *
 * @author ShihovMY
 */
public class Author extends BasicItem{
    private String name;
    private String secondName;
    private String surname;
    private AbstractTeacher teacher;
    private static final long serialVersionUID = 96L;

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public AbstractTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(AbstractTeacher teacher) {
        this.teacher = teacher;
    }
}
