package org.sgu.oecde.core.authentication;

import org.springframework.security.core.GrantedAuthority;

/**
 * роль-тип пользователя
 * @author ShihovMY
 * @see org.springframework.security.GrantedAuthority
 */
public class RoleItem implements GrantedAuthority {

    private static final long serialVersionUID = 41L;
    /**
     * название роли
     */
    private String role;

    public RoleItem() {
    }

    /**
     *
     * @return роль
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role!=null?role.toUpperCase():null;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

    @Override
    public String toString() {
        return role;
    }
}
