package org.sgu.oecde.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;
import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.core.users.Teacher;
/**
 * занятие на видеоконференции
 * @author shihovmy
 */
public class Lesson extends BasicItem{

    private Discipline discipline;
    private Teacher teacher;
    private Integer number;
    private Integer room;
    private String lessonDate;
    private String lessonEndDate;
    private LessonType lessonType;
    private String updateDate;
    private String color;
    private String colorDisable;
    private Boolean winter;
    private Integer year;
    private Set<CityWithGroup>citiesWithGroups;
    
    private boolean notUsed = true;
    private boolean warning;
    private static final long serialVersionUID = 44L;
    
    
  //  private FileFind finder;
    
    public Lesson() {
    }

    public Lesson(int room, String lessonDate, String lessonEndDate, boolean notUsed, String color, String color2) throws MalformedURLException, IOException {
        this.room=room;
        this.lessonDate=lessonDate;
        this.lessonEndDate=lessonEndDate;
        this.notUsed=notUsed;
        this.color=color;
        this.colorDisable=color2;
      //  finder = FileFind.getInstance();
    }
    /**
     * @return true if file with recording lesson exists
     */
    
   
    
//     public boolean fileExist() throws MalformedURLException, IOException{
//        getContent();
//        boolean exists=folderContent.contains(";"+this.getId()+"roomVideoStream");
//        return exists;
//    }
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

      public String getShortDateEnd(){
        if(this.lessonEndDate!=null&&this.lessonEndDate.length()>16)
            return this.lessonEndDate.substring(11, 16);
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

    public String getLessonEndDate() {
        return lessonEndDate;
    }

    public void setLessonEndDate(String lessonEndDate) {
        this.lessonEndDate = lessonEndDate;
    }

    public String getColorPick(){
        if (this.isNotUsed()){
            return this.color;
        }else return this.colorDisable;
    }

    public Set<CityWithGroup> getCitiesWithGroups() {
        return citiesWithGroups;
    }

    public void setCitiesWithGroups(Set<CityWithGroup> citiesWithGroups) {
        this.citiesWithGroups = citiesWithGroups;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

}
