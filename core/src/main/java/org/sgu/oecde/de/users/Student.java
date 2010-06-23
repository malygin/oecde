package org.sgu.oecde.de.users;

import org.sgu.oecde.core.users.AbstractStudent;

/**
 * студент дистанционного образования
 * @author ShihovMY
 */
public class Student extends AbstractStudent{

    /**
     * e-mail
     */
    private String email;
    /**
     * мобильный
     */
    private Integer cellPhone;
    /**
     * icq
     */
    private Integer icq;
    private static final long serialVersionUID = 51L;

    public Student() {
    }

    public Student(Long id) {
        setId(id);
    }

    /**
     *
     * @return сотовый
     */
    public Integer getCellPhone() {
        return cellPhone;
    }

    /**
     * сотовый
     * @param cellPhone
     */
    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return icq
     */
    public Integer getIcq() {
        return icq;
    }

    /**
     * icq
     * @param icq
     */
    public void setIcq(Integer icq) {
        this.icq = icq;
    }
}
