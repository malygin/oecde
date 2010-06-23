package org.sgu.oecde.core.education;

import org.sgu.oecde.core.BasicItem;


/**
 * специальность
 * @author ShihovMY
 */
public class Speciality extends BasicItem{

    /**
     * название
     */
    private String name;
    /**
     *  сокращённое имя латинскими буквами
     */
    private String engShort;
    /**
     *  сокращённое имя русскими буквами
     */
    private String rusShort;
    private static final long serialVersionUID = 45L;

    public Speciality() {
    }

    public Speciality(Long id) {
        setId(id);
    }

    public Speciality(Long id, String name) {
        setId(id);
        this.name = name;
    }

    /**
     * @return имя специальности
     */
    public String getName() {
        return name;
    }

    /**
     * имя специальности
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return сокращённое имя латинскими буквами
     */
    public String getEngShort() {
        return engShort;
    }

    /**
     * сокращённое имя латинскими буквами
     * @param engShort
     */
    public void setEngShort(String engShort) {
        this.engShort = engShort;
    }

    /**
     *  сокращённое имя русскими буквами
     * @return
     */
    public String getRusShort() {
        return rusShort;
    }

    /**
     *  сокращённое имя русскими буквами
     * @param rusShort
     */
    public void setRusShort(String rusShort) {
        this.rusShort = rusShort;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("название: ").append(name).append("; ");
        return sb.toString();
    }
}
