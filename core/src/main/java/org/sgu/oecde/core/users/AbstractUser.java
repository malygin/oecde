package org.sgu.oecde.core.users;

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
    private boolean enabled=true;
    private boolean accountNonLocked;
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

    /**
	 * 
	 * @return 
	 */
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAuthority(GrantedAuthority authority) {
        authorities[0]=authority;
    }

    public GrantedAuthority getAuthority(){
        return (authorities!=null&&authorities.length>0)?authorities[0]:null;
    }

    /**
	 * 
	 * @return 
	 */
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

    /**
	 * 
	 * @param rhs
	 * @return 
	 */
	@Override
    public boolean equals(Object rhs) {

        if (!(rhs instanceof AbstractUser) || (rhs == null)) {
            return false;
        }

        AbstractUser user = (AbstractUser) rhs;

        if (user.getAuthorities().length != this.getAuthorities().length) {
            return false;
        }

        for (int i = 0; i < this.getAuthorities().length; i++) {
            if (!this.getAuthorities()[i].equals(user.getAuthorities()[i])) {
                return false;
            }
        }

        return (this.getPassword().equals(user.getPassword()) && this.getUsername().equals(user.getUsername()) && (this.isEnabled() == user.isEnabled()));
    }

    /**
	 * 
	 * @return 
	 */
	@Override
    public int hashCode() {
        int code = 9792;

        if (this.getAuthorities() != null) {
            for (int i = 0; i < this.getAuthorities().length; i++) {
                code = code * (this.getAuthorities()[i].hashCode() % 7);
            }
        }

        if (this.getPassword() != null) {
            code = code * (this.getPassword().hashCode() % 7);
        }

        if (this.getUsername() != null) {
            code = code * (this.getUsername().hashCode() % 7);
        }

        if (this.isEnabled()) {
            code = code * -7;
        }

        return code;
    }
}
