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
    private int cellPhone;
    private int icq;

    public Student() {
    }

    public Student(int id) {
        setId(id);
    }

    public int getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(int cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIcq() {
        return icq;
    }

    public void setIcq(int icq) {
        this.icq = icq;
    }
}
