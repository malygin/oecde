package org.sgu.oecde.saschedule;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.LessonType;
import org.sgu.oecde.schedule.dao.ILessonDao;


/**
 *
 * @author Malygin
 */
@ManagedBean(name="EditDayBean")
@SessionScoped
public class EditDayBean implements Serializable {

    @ManagedProperty(value="#{lessonDao}")
    ILessonDao lessonDao;
    @ManagedProperty(value="#{CalendarBean}")
    CalendarBean calendare;
    @ManagedProperty(value="#{EditLessonBean}")
    EditLessonBean lessonBean;

    private List<Lesson> allLessons = new ArrayList();
    private boolean render=false;

    //private String currentDay;


    final private int numberRoom=13;
    private int numberTimeInterval=13;
    static private  List<String> TimeInterval= new ArrayList();
    static private  List<String> TimeIntervalEnd= new ArrayList();

    static private String RoomColor[][];
   

    public EditDayBean() {
    }

    @PostConstruct
    public void  init() {
        TimeInterval.clear();
        TimeInterval.add("09:10:00");
        TimeInterval.add("10:00:00");
        TimeInterval.add("10:50:00");
        TimeInterval.add("12:05:00");
        TimeInterval.add("12:55:00");

        TimeInterval.add("13:45:00");
        TimeInterval.add("14:35:00");
        TimeInterval.add("15:25:00");
        TimeInterval.add("16:15:00");
        TimeInterval.add("17:20:00");

        TimeInterval.add("18:10:00");
        TimeInterval.add("19:00:00");
        TimeInterval.add("19:50:00");

        TimeIntervalEnd.clear();
        TimeIntervalEnd.add("09:55:00");
        TimeIntervalEnd.add("10:45:00");
        TimeIntervalEnd.add("11:35:00");
        TimeIntervalEnd.add("12:50:00");
        TimeIntervalEnd.add("13:40:00");

        TimeIntervalEnd.add("14:30:00");
        TimeIntervalEnd.add("15:20:00");
        TimeIntervalEnd.add("16:10:00");
        TimeIntervalEnd.add("17:00:00");
        TimeIntervalEnd.add("18:05:00");

        TimeIntervalEnd.add("18:55:00");
        TimeIntervalEnd.add("19:45:00");
        TimeIntervalEnd.add("20:35:00");

        RoomColor=new String[numberRoom][2];
       
        RoomColor[0][0]="#6495ED";
        RoomColor[0][1]="#b3cefe";
     
        RoomColor[1][0]="#D4A017";
        RoomColor[1][1]="#f8d57a";
      
        RoomColor[2][0]="#CD5C5C";
        RoomColor[2][1]="#f6a5a5";
 
        RoomColor[3][0]="#4AA02C";
        RoomColor[3][1]="#9cfc7b";
     
        RoomColor[4][0]="#ea608e";
        RoomColor[4][1]="#f697b6";

        RoomColor[5][0]="#6495ED";
        RoomColor[5][1]="#b3cefe";
        
        RoomColor[6][0]="#CD5C5C";
        RoomColor[6][1]="#f6a5a5";
 
        RoomColor[7][0]="#4AA02C";
        RoomColor[7][1]="#9cfc7b";
     
        RoomColor[8][0]="#ea608e";
        RoomColor[8][1]="#f697b6";

        RoomColor[9][0]="#6495ED";
        RoomColor[9][1]="#b3cefe";
       
        RoomColor[10][0]="#D4A017";
        RoomColor[10][1]="#f8d57a";
        
        RoomColor[11][0]="#CD5C5C";
        RoomColor[11][1]="#f6a5a5";
 
        RoomColor[12][0]="#4AA02C";
        RoomColor[12][1]="#9cfc7b";
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }
//
//    public String getCurrentDay() {
//        return currentDay;
//    }
//
//    public void setCurrentDay(String currentDay) {
//       // this.currentDay = currentDay;
//        this.render=true;
//    }
    public void ShowEditDay(){
     //   System.out.println("render!!!");
          this.render=true;
    }
    public void ShowSelectDate() throws MalformedURLException, IOException{
        //прикрыли редактировани занятии todo - повесить эффект
       lessonBean.setRender(false);      
       this.render=true;
       List<Lesson> allLessonsTemp=null;
       allLessons=new ArrayList();

       DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
       String today = formatter.format(calendare.getSelectedDate());
       //составили список дней сеткой
       int i=0;
       int timeInter=0;
       for(int k=0; k<numberRoom;k++){
           for(String c:TimeInterval){
             //  System.out.println(" " +r.get(0)+" "+r.get(1));
             allLessons.add(new Lesson(i++, today+" "+c, today+" "+TimeIntervalEnd.get(timeInter++), true, RoomColor[k][1], RoomColor[k][0]));
           }
            timeInter=0;
       }
       //пометилили дни где есть занятия
       Lesson l = new Lesson();
       l.setLessonDate(today);
       allLessonsTemp=lessonDao.getLessonsByDate(l);
       for(Lesson c:allLessons){
            for(Lesson ctemp:allLessonsTemp){
              //  System.out.println("" +ctemp.getLessonDate() +" "+ c.getLessonDate());
             //   System.out.println("" +ctemp.getRoom() +" "+ c.getRoom());
              if ((c.getLessonDate().equals(ctemp.getLessonDate())) && (c.getRoom().equals(ctemp.getRoom()))){
                c.setNotUsed(false);
                c.setTeacher(ctemp.getTeacher());
                c.setCitiesWithGroups(ctemp.getCitiesWithGroups());
                c.setId(ctemp.getId());
                c.setDiscipline(ctemp.getDiscipline());
                c.setLessonType(ctemp.getLessonType());
              }
          }
       }  
   }



    public ILessonDao getLessonDao() {
        return lessonDao;
    }

    public void setLessonDao(ILessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public List<Lesson> getAllLessons() {
        return allLessons;
    }

    public void setAllLessons(List<Lesson> allLessons) {
        this.allLessons = allLessons;
    }

    public CalendarBean getCalendare() {
        return calendare;
    }

    public void setCalendare(CalendarBean calendare) {
        this.calendare = calendare;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

 

    public int getNumberTimeInterval() {
        return numberTimeInterval;
    }

    public void setNumberTimeInterval(int numberTimeInterval) {
        this.numberTimeInterval = numberTimeInterval;
    }

    public EditLessonBean getLessonBean() {
        return lessonBean;
    }

    public void setLessonBean(EditLessonBean lessonBean) {
        this.lessonBean = lessonBean;
    }   

}
