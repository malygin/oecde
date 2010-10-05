package org.sgu.oecde.core.users;

import org.springframework.util.Assert;

/**
 * типы пользователей
 * @author ShihovMY
 */
public enum UserType {

    /**
     * студент
     */
    STUDENT,
    /**
     * наблюдатель
     */
    SUPERVISOR,
    /**
     * преподаватель
     */
    TEACHER,
    /**
     * админ
     */
    ADMIN,
    /**
     * гость
     */
    GUEST;

    public static UserType toType(AbstractUser user) {
        if (user instanceof AbstractStudent) {
            return STUDENT;
        } else if (user instanceof Teacher) {
            return TEACHER;
        } else if (user instanceof Supervisor ) {
            return SUPERVISOR;
        } else if (user instanceof Admin ) {
            return ADMIN;
        } 
        return null;
    }

    public int toInt() {
        switch (this) {
            case ADMIN:
                return 1;
            case SUPERVISOR:
                return 2;
            case TEACHER:
                return 3;
            case STUDENT:
                return 4;
            case GUEST:
                return 5;
            default:
                return 0;
        }
    }

    public static UserType parse(int i) {
        switch (i) {
            case 1:
                return ADMIN;
            case 2:
                return SUPERVISOR;
            case 3:
                return TEACHER;
            case 4:
                return STUDENT;
            case 5:
                return GUEST;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case ADMIN:
                return "Администратор";
            case SUPERVISOR:
                return "Наблюдатель";
            case TEACHER:
                return "Преподаватель";
            case STUDENT:
                return "Студент";
            case GUEST:
                return "Гость";
            default:
                return "";
        }
    }

    public String toEngString() {
        switch (this) {
            case ADMIN:
                return "admin";
            case SUPERVISOR:
                return "supervisor";
            case TEACHER:
                return "teacher";
            case STUDENT:
                return "student";
            case GUEST:
                return "guest";
            default:
                return "";
        }
    }
      public String toNameFolder() {
        switch (this) {
            case ADMIN:
                return "Admin";
            case SUPERVISOR:
                return "Supervisor";
            case TEACHER:
                return "Teacher";
            case STUDENT:
                return "Student";
            case GUEST:
                return "Guest";
            default:
                return "";
        }
    }
}
