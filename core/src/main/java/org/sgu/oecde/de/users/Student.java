/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.de.users;

import org.sgu.oecde.core.users.AbstractStudent;

/**
 *
 * @author ShihovMY
 */
public class Student extends AbstractStudent{

    private static final long serialVersionUID = 51L;
    private String email;
    private Integer cellPhone;
    private Integer icq;

    public Student() {
    }

    public Student(Long id) {
        setId(id);
    }

    public Integer getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIcq() {
        return icq;
    }

    public void setIcq(Integer icq) {
        this.icq = icq;
    }
}
