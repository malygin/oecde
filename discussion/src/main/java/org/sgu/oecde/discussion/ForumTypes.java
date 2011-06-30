package org.sgu.oecde.discussion;

/**
 * типы объектов для обсуждения
 * @author Basakov
 */
public enum ForumTypes {
    /**
     * студенческий технический
     */
    STUDENT_FAQ,
    /**
     * студенческий организационный
     */
    STUDENT_ORG,
    /**
     * город
     */
    STUDENT_CITY,
    /**
     * преподавательский технический
     */
    TEACHER_FAQ,
    /**
     * преподавательский организационный
     */
    TEACHER_ORG,
    /**
     * новость
     */
    NEWS;
    private static final long serialVersionUID = 89L;

    public String getName() {
        switch (this) {
            case STUDENT_CITY:
                return "stcity";
            case STUDENT_FAQ:
                return "stfaq";
            case STUDENT_ORG:
                return "storg";
            case TEACHER_FAQ:
                return "teachfaq";
            case TEACHER_ORG:
                return "teachorg";
            case NEWS:
                return "news";
            default:
                //Заглушка. Недостижима.
                throw new RuntimeException("No string representation found for " + this);
        }
    }

    public static ForumTypes parse(String val) {
        for (ForumTypes type : ForumTypes.values()) {
            if (type.getName().equals(val)) return type;
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getTitle(){
        switch (this) {
            case STUDENT_CITY:
                return "stcity";
            case STUDENT_FAQ:
                return "Студенческий технически форум";
            case STUDENT_ORG:
                return "Студенческий организационный форум";
            case TEACHER_FAQ:
                return "Преподавательский технический форум";
            case TEACHER_ORG:
                return "Преподавательский организационный форум";
            case NEWS:
                return "Новостной форум";
            default:
                //Заглушка. Недостижима.
                throw new RuntimeException("No string representation found for " + this);
        }
    }
}
