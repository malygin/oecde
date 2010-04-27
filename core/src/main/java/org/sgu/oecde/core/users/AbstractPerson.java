/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.users;

/**
 *
 * @author ShihovMY
 */
public abstract class AbstractPerson extends AbstractUser{

    private String name;
    private String secondName;
    private String surname;

    public AbstractPerson() {
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

    /**
	 * 
	 * @return 
	 */
	@Override
    public String toString(){
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Surname: ").append(this.surname).append("; ");
        sb.append("Name: ").append(this.name).append("; ");
        sb.append("Second name: ").append(this.secondName).append("; ");
        return sb.toString();
    }
}
