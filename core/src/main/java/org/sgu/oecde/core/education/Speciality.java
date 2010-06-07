package org.sgu.oecde.core.education;

import org.sgu.oecde.core.BasicItem;


/**
 * специальность
 * @author ShihovMY
 */
public class Speciality extends BasicItem{

    private String name;
    private String engShort;
    private String rusShort;
    private static final long serialVersionUID = 45L;

    public Speciality() {
    }

    public Speciality(int id) {
        setId(id);
    }

    public Speciality(int id, String name) {
        setId(id);
        this.name = name;
    }

    /**
     * @return имя специальности
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngShort() {
        return engShort;
    }

    public void setEngShort(String engShort) {
        this.engShort = engShort;
    }

    public String getRusShort() {
        return rusShort;
    }

    public void setRusShort(String rusShort) {
        this.rusShort = rusShort;
    }

}
