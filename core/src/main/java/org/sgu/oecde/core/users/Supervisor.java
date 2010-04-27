/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.users;

/**
 *
 * @author ShihovMY
 */
public class Supervisor extends AbstractUser{
    private String description;
    private static final long serialVersionUID = 62L;

    public Supervisor() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
	 * 
	 * @return 
	 */
	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Desctiption: ").append(this.description).append("; ");
        return sb.toString();
    }
}
