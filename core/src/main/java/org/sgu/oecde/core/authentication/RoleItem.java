package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.BasicItem;
import org.springframework.security.GrantedAuthority;

/**
 * роль-тип пользователя
 * @author ShihovMY
 * @see org.springframework.security.GrantedAuthority
 */
public class RoleItem extends BasicItem implements GrantedAuthority {

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

    /**
     * {@inheritDoc}
     * @return роль
     */
    @Override
    public String getAuthority() {
        return this.role;
    }

    /**
     * {@inheritDoc}
     */
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("role: ").append(role).append("; ");
        return sb.toString();
    }
}
