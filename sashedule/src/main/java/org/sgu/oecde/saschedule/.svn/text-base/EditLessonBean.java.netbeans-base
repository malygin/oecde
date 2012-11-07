package org.sgu.oecde.saschedule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Discipline;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.schedule.Lesson;


/**
 *
 * @author Malygin
 */
@ManagedBean(name="EditLessonBean")
@SessionScoped
public class EditLessonBean implements Serializable {
    @ManagedProperty(value="#{teacherDao}")
    IBasicDao teacherDao;
    private List<Discipline> disciplanes = new ArrayList();
    private boolean render=false;

    private String currentTime;
    private String currentTimeEnd;

    private Lesson Lesson;
    private String currentRoom;
    private String currentLessonId;


    public EditLessonBean() {
    }


     public void ShowEditLesson(){
  
        this.render=true;
    }
    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public List<Discipline> getDiscipline() {
        return disciplanes;
    }

    public void setDiscipline(List<Discipline> disciplanes) {
        this.disciplanes = disciplanes;
    }

    public IBasicDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(IBasicDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentLessonId() {
        return currentLessonId;
    }

    public void setCurrentLessonId(String currentLessonId) {
        if (currentLessonId.equals("")) {this.currentLessonId=null;}
        else {this.currentLessonId=currentLessonId;}

    }

    public String getCurrentTimeEnd() {
        return currentTimeEnd;
    }

    public void setCurrentTimeEnd(String currentTimeEnd) {
        this.currentTimeEnd = currentTimeEnd;
    }

    public Lesson getLesson() {
        return Lesson;
    }

    public void setLesson(Lesson Lesson) {
        this.Lesson = Lesson;
    }

  


    }
