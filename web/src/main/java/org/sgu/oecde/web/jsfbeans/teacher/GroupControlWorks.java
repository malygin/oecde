package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.journal.Journal;
import org.sgu.oecde.web.jsfbeans.util.NewEntry;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupControlWorksForTeacher")
@ViewScoped
public class GroupControlWorks extends AbstractStudentsListBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    @ManagedProperty(value="#{journalServise}")
    private Journal journalServise;

    private List<NewEntry<Student,ControlWork>>groupControlWorks;

    private boolean error = false;
    private boolean saved = false;

    private static final long serialVersionUID = 114L;

    public List<NewEntry<Student,ControlWork>> getGroupControlWorks() {
        if(groupControlWorks==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(getCurriculum());
            Map<Student,ControlWork>mp = null;
            groupControlWorks = new ArrayList<NewEntry<Student,ControlWork>>();
            if(c!=null&&c.size()==1){
                mp = cwService.<Student,ControlWork>getCurriculumControlWorks(getStudentsList(), c.get(0));
                for(Map.Entry<Student,ControlWork> entry:mp.entrySet() ){
                    NewEntry<Student,ControlWork>e = new NewEntry<Student,ControlWork>();
                    e.setKey(entry.getKey());
                    ControlWork w = entry.getValue();
                    if(w==null){
                        w = new ControlWork();
                        w.setCurriculum(getCurriculum());
                        w.setStudent(e.getKey());
                    }
                    e.setValue(w);
                    groupControlWorks.add(e);
                }
            }
        }
        return groupControlWorks;
    }

    public void setGroupControlWorks(List<NewEntry<Student,ControlWork>> groupControlWorks) {
        this.groupControlWorks = groupControlWorks;
    }

    public void save(){
        try{
            for(NewEntry<Student,ControlWork>w:groupControlWorks){
                cwService.save(w.getValue());
            }
        }catch(Exception e){
            e.fillInStackTrace();
            error = true;
        }
        saved=true;
    }

    public void saveEmpty(AjaxBehaviorEvent event){
        ControlWork w = (ControlWork) event.getComponent().getAttributes().get("work");
        try{
            cwService.saveEmptyCw(w);
        }catch(Exception e){
            e.fillInStackTrace();
            error = true;
        }
        journalServise.logHandWrittenWorkGot(teacher, getCurriculum(), w.getStudent());
    }

    public void logWorkDownload(AjaxBehaviorEvent event){
        Student s = (Student) event.getComponent().getAttributes().get("student");
        ControlWorkAttempt a = (ControlWorkAttempt) event.getComponent().getAttributes().get("attempt");
        cwService.setWorkRead(a);
        journalServise.logTaskHasBeenRead(teacher, getCurriculum(), s);
    }

    public ControlWorkProgress[] getValues(){
        return ControlWorkProgress.values();
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public void setJournalServise(Journal journalServise) {
        this.journalServise = journalServise;
    }
}
