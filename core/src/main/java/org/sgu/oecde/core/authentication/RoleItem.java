/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.BasicItem;
import org.springframework.security.GrantedAuthority;

/**
 *
 * @author ShihovMY
 */
public class RoleItem extends BasicItem implements GrantedAuthority {

    private static final long serialVersionUID = 41L;
    private String role;
    
    public RoleItem() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role!=null?role.toUpperCase():null;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public int compareTo(Object o) {
        if (o != null && o instanceof GrantedAuthority) {
                String rhsRole = ((GrantedAuthority) o).getAuthority();

                if (rhsRole == null) {
                        return -1;
                }

                return role.compareTo(rhsRole);
        }
        return -1;
    }



}
