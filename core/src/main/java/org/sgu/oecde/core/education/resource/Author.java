package org.sgu.oecde.core.education.resource;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.Teacher;

/**
 * автор ресурсов, либо умк
 * @author ShihovMY
 */
public class Author extends BasicItem{

    /**
     * имя
     */
    private String name;
    /**
     * отчество
     */
    private String secondName;
    /**
     * фамилия
     */
    private String surname;
    /**
     * ссылка на преподавателя
     */
    private Teacher teacher;
    private static final long serialVersionUID = 96L;

    public Author() {
    }

    /**
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * имя
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * отчество
     * @return
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * отчество
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * фамилия
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * фамилия
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * ссылка на преподавателя
     * @return
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * ссылка на преподавателя
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
