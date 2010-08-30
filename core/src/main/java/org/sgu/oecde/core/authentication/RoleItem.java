package org.sgu.oecde.core.authentication;

import org.sgu.oecde.core.users.AbstractStudent;
import org.sgu.oecde.core.users.AbstractUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * роль-тип пользователя
 * @author ShihovMY
 * @see org.springframework.security.GrantedAuthority
 */
public enum RoleItem implements GrantedAuthority {

    /**
     * студент
     */
    ROLE_STUDENT,
    /**
     * наблюдатель
     */
    ROLE_SUPERVISOR,
    /**
     * преподаватель
     */
    ROLE_TEACHER,
    /**
     * админ
     */
    ROLE_ADMIN,
    /**
     * гость
     */
    GUEST;
    private static final long serialVersionUID = 41L;

    /**
     * {@inheritDoc}
     * @return роль
     */
    @Override
    public String getAuthority() {
        return this.valueOf();
    }

    public static RoleItem parse(String str) {
        if ("role_student".equalsIgnoreCase(str)) {
            return ROLE_STUDENT;
        } else if ("role_teacher".equalsIgnoreCase(str)) {
            return ROLE_TEACHER;
        } else if ("role_supervisor".equalsIgnoreCase(str) ) {
            return ROLE_SUPERVISOR;
        } else if ("role_admin".equalsIgnoreCase(str) ) {
            return ROLE_ADMIN;
        } else if ("role_guest".equalsIgnoreCase(str)) {
            return GUEST;
        }
        throw new AssertionError();
    }

    public Integer toInt() {
        switch (this) {
            case ROLE_ADMIN:
                return 1;
            case ROLE_SUPERVISOR:
                return 2;
            case ROLE_TEACHER:
                return 3;
            case ROLE_STUDENT:
                return 4;
            case GUEST:
                return 5;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ROLE_ADMIN:
                return "Администратор";
            case ROLE_SUPERVISOR:
                return "Наблюдатель";
            case ROLE_TEACHER:
                return "Преподаватель";
            case ROLE_STUDENT:
                return "Студент";
            case GUEST:
                return "Гость";
            default:
                return "";
        }
    }

    public String valueOf() {
        switch (this) {
            case ROLE_ADMIN:
                return "ROLE_ADMIN";
            case ROLE_SUPERVISOR:
                return "ROLE_SUPERVISOR";
            case ROLE_TEACHER:
                return "ROLE_TEACHER";
            case ROLE_STUDENT:
                return "ROLE_STUDENT";
            case GUEST:
                return "ROLE_GUEST";
            default:
                throw new AssertionError();
        }
    }

    public String toNameFolder() {
        switch (this) {
            case ROLE_ADMIN:
                return "Admin";
            case ROLE_SUPERVISOR:
                return "Supervisor";
            case ROLE_TEACHER:
                return "Teacher";
            case ROLE_STUDENT:
                return "Student";
            case GUEST:
                return "Guest";
            default:
                return "";
        }
    }
}
