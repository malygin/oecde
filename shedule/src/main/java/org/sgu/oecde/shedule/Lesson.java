package org.sgu.oecde.shedule;

import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.StudentGroup;

/**
 * занятие на видеоконференции
 * @author shihovmy
 */
public class Lesson extends BasicItem{

    private Set<? extends StudentGroup> group;
    private Discipline discipline;
    private Teacher teacher;
    private Integer number;
    private Integer room;
    private Boolean warning;
    private String lessonDate;
    private String updateDate;
    private String color;
    private String colorDisable;
    private Boolean notUsed = true;
    private static final long serialVersionUID = 44L;

    public Lesson() {
    }

    public Lesson(int room, String lessonDate,boolean notUsed, String color, String color2) {
        this.room=room;
        this.lessonDate=lessonDate;
        this.notUsed=notUsed;
        this.color=color;
        this.colorDisable=color2;
    }
    /**
     * @return коллекция город-специальность-курс, которые присутствуют на занятии
     */
    public <T extends StudentGroup>Set<T> getGroup() {
        return (Set<T>) group;
    }

    public void setGroup(Set<? extends StudentGroup> css) {
        this.group = css;
    }
    /**
     * @return количество человек на занятии
     */
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    /**
     * @return номер комнаты
     */
    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    /**
     * @return привязка из плана
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    /**
     * @return преподаватель
     */
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isWarning() {
        return warning;
    }

    public void setWarning(boolean warning) {
        this.warning = warning;
    }

    /**
     * @return время проведения занятия
     */
    public String getLessonDate() {
        return lessonDate;
    }
    
    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }
    
    /**
     * @return время изменения расписания
     */
    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return цвет комнаты
     */
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isNotUsed() {
        return notUsed;
    }

    public void setNotUsed(boolean notUsed) {
        this.notUsed = notUsed;
    }

    public String getShortDate(){
        return this.lessonDate.substring(11, 16);
    }

    public String getColorDisable() {
        return colorDisable;
    }

    public void setColorDisable(String colorDisable) {
        this.colorDisable = colorDisable;
    }
      public String getColorPick(){
        if (this.isNotUsed()){
            return this.color;
        }else return this.colorDisable;
    }
}
