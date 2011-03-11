package org.sgu.oecde.web;

import org.sgu.oecde.core.users.AbstractUser;

/**
 *
 * @author ShihovMY
 */
public interface IBeanWithAvatarAdding {
    public String uploadAvatar();
    public AbstractUser getUser();
    public void setAvatarBuilder(AvatarBuilder avatarBuilder);
}
