package org.sgu.oecde.core.users;

import java.util.Arrays;
import org.sgu.oecde.core.BasicItem;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * абстрактный пользователь. реализует интерфейс UserDetails
 * @author ShihovMY
 * @see org.springframework.security.userdetails.UserDetails
 */
public abstract class AbstractUser extends BasicItem implements UserDetails{
    /**
     * пароль
     */
    private String password;
    /**
     * имя пользователя
     */
    private String username;
    /**
     * массив ролей из 1го элемента
     */
    private GrantedAuthority[] authorities;
    /**
     * доступен ли
     */
    private boolean enabled = true;
    /**
     * имеет ли полный доступ
     */
    private Boolean fullAccess;
    /**
     * крупная фотография
     */
    private String largePhoto;
    /**
     * средняя фотография
     */
    private String mediumPhoto;
    /**
     * маленькая фотография
     */
    private String smallPhoto;
    /**
     * пользователь онлай
     */
    private transient boolean online=false;
    private static final long serialVersionUID = 61L;

    private AbstractUser(String userName){
        this.username = userName;
        this.enabled = true;
    }

    /**
     * @param userName
     * @return объект AbstractUser с заполненным полем userName для поиска пользователя
     * по userName
     * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     * @see org.sgu.oecde.core.IBasicDao#getBySimpleExample(org.sgu.oecde.core.BasicItem)
     */
    public static final AbstractUser getUserWithName(String userName){
        Assert.notNull(userName,"user name is null");
        return new AbstractUser(userName){};
    }

    public AbstractUser() {
       authorities = new GrantedAuthority[1];
    }

    /**
     * доступен ли
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * пароль
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * имя пользователя
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return онлайн ли
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * онлайн ли
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    /**
     *
     * @return имеет ли полный доступ
     */
    public Boolean getFullAccess() {
        return fullAccess;
    }

    /**
     *  имеет ли полный доступ
     * @param fullAccess
     */
    public void setFullAccess(Boolean fullAccess) {
        this.fullAccess = fullAccess;
    }

    /**
     * большая фото
     * @return
     */
    public String getLargePhoto() {
        return largePhoto;
    }

    /**
     * большая фото
     * @param largePhoto
     */
    public void setLargePhoto(String largePhoto) {
        this.largePhoto = largePhoto;
    }

    /**
     *
     * @return средняя фото
     */
    public String getMediumPhoto() {
        return mediumPhoto;
    }

    /**
     * средняя фото
     * @param mediumPhoto
     */
    public void setMediumPhoto(String mediumPhoto) {
        this.mediumPhoto = mediumPhoto;
    }

    /**
     *
     * @return маленькая фото
     */
    public String getSmallPhoto() {
        return smallPhoto;
    }

    /**
     * маленькая фото
     * @param smallPhoto
     */
    public void setSmallPhoto(String smallPhoto) {
        this.smallPhoto = smallPhoto;
    }

    /**
     * не используется
     * {@inheritDoc }
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * не используется
     * {@inheritDoc }
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * роль
     * @param authority
     */
    public void setAuthority(GrantedAuthority authority) {
        authorities[0]=authority;
    }

    /**
     *
     * @return роль
     */
    public GrantedAuthority getAuthority(){
        return (authorities!=null&&authorities.length>0)?authorities[0]:null;
    }

    /**
     * не используется
     * {@inheritDoc }
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("Username: ").append(this.username).append(";\n");
        sb.append("Enabled: ").append(this.enabled).append(";\n");

        if (this.getAuthority() != null) {
            sb.append("Granted Authorities: ");
            sb.append(this.getAuthority()).append(";\n");
        } else {
            sb.append("Not granted any authorities\n");
        }

        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
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
    
    /**
     * {@inheritDoc }
     */
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
