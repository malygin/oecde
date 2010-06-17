package org.sgu.oecde.core.users;

import java.util.Arrays;
import org.sgu.oecde.core.BasicItem;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 *
 * @author ShihovMY
 */
public abstract class AbstractUser extends BasicItem implements UserDetails{
    private String password;
    private String username;
    private GrantedAuthority[] authorities;
    private boolean enabled = true;
    private Boolean fullAccess;
    private String largePhoto;
    private String mediumPhoto;
    private String smallPhoto;
    private transient boolean online=false;
    private static final long serialVersionUID = 61L;

    private AbstractUser(String userName){
        this.username = userName;
        this.enabled = true;
    }

    public static final AbstractUser getUserWithName(String userName){
        Assert.notNull(userName,"user name is null");
        return new AbstractUser(userName){};
    }

    public AbstractUser() {
       authorities = new GrantedAuthority[1];
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Boolean getFullAccess() {
        return fullAccess;
    }

    public void setFullAccess(Boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    public String getLargePhoto() {
        return largePhoto;
    }

    public void setLargePhoto(String largePhoto) {
        this.largePhoto = largePhoto;
    }

    public String getMediumPhoto() {
        return mediumPhoto;
    }

    public void setMediumPhoto(String mediumPhoto) {
        this.mediumPhoto = mediumPhoto;
    }

    public String getSmallPhoto() {
        return smallPhoto;
    }

    public void setSmallPhoto(String smallPhoto) {
        this.smallPhoto = smallPhoto;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void setAuthority(GrantedAuthority authority) {
        authorities[0]=authority;
    }

    public GrantedAuthority getAuthority(){
        return (authorities!=null&&authorities.length>0)?authorities[0]:null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
	 * 
	 * @return 
	 */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");

        if (this.getAuthority() != null) {
            sb.append("Granted Authorities: ");
            sb.append(this.getAuthority());
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractUser other = (AbstractUser) obj;
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if (!Arrays.deepEquals(this.authorities, other.authorities)) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 79 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 79 * hash + Arrays.deepHashCode(this.authorities);
        hash = 79 * hash + (this.enabled ? 1 : 0);
        return hash;
    }

}
