package org.sgu.oecde.schedule;

import java.util.List;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.StudentGroup;

/**
 * занятие на видеоконференции
 * @author shihovmy
 */
public class Lesson extends BasicItem{

    private List<? extends StudentGroup> groups;
    private Discipline discipline;
    private Teacher teacher;
    private Integer number;
    private Integer room;
    private String lessonDate;
    private String updateDate;
    private String color;
    private String colorDisable;
    private Boolean winter;
    private Integer year;
    
    private boolean notUsed = true;
    private boolean warning;
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
    public <T extends StudentGroup>List<T> getGroups() {
        return (List<T>) groups;
    }

    public void setGroups(List<? extends StudentGroup> groups) {
        this.groups = groups;
    }
    /**
     * @return количество человек на занятии
     */
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    /**
     * @return номер комнаты
     */
    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    /**
     * @return привязка из плана
     */
    public <T extends Discipline>T getDiscipline() {
        return (T) discipline;
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
        if(this.lessonDate!=null&&this.lessonDate.length()>16)
            return this.lessonDate.substring(11, 16);
        else
            return "";
    }

    public Boolean getWinter() {
        return winter;
    }

    public void setWinter(Boolean winter) {
        this.winter = winter;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
