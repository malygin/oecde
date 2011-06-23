package org.sgu.oecde.web.jsfbeans.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.controlworks.ControlWorkCalendarConstantName;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.controlworks.dao.IControlWorkDao;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FacesUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FileUploadUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.MultipartRequestWrapper;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.UploadFile;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="controlWorksBean")
@ViewScoped
public class ControlWorksBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    @ManagedProperty(value="#{controlWorkDao}")
    private IControlWorkDao<ControlWork> controlWorkDao;

    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;

    private List<Object[]>works;

    @ManagedProperty(value="#{cwDatesGetter}")
    private StringConstantsGetter cwDatesGetter;

    private String controlWorksBeginDate;
    private String controlWorksEndDate;
    private String reExameBeginDate;
    private String reExameEndDate;

    private ControlWork currentControlWorks;

    private static final long serialVersionUID = 105L;

    public List<Object[]>getControlWorks(){
        if(works==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstanceByCurrentDate(student, semester));
            Map<DeCurriculum,ControlWork> m = cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
            Iterator<DeCurriculum>cI = c.iterator();
            String currentDate = DateConverter.currentDate();
            works = new LinkedList();
            while(cI.hasNext()){
                DeCurriculum cr = cI.next();
                ControlWork w = m.get(cr);
                boolean available=false;
                Object[] data = new Object[5];
                works.add(data);
                data[0] = cr;
                if(((currentDate.compareTo(controlWorksBeginDate)>=0
                    &&currentDate.compareTo(controlWorksEndDate)<0)
                    ||(currentDate.compareTo(reExameBeginDate)>=0
                    &&currentDate.compareTo(reExameEndDate)<0)
                    )&&(w==null||!ControlWorkProgress.passed.equals(w.getProgress()))
                    &&!cr.getControlWorksPaperOnly()
                    &&student.getFullAccess()){
                    available = true;
                }
                if(w == null){
                    w = new ControlWork(student, cr);
                }
                data[1] = w;
                data[2] = available;
                if(getCurriculumAndTeacher().containsKey(cr))
                    data[3] = getCurriculumAndTeacher().get(cr);
                data[4] = (cr.getControlWorksPaperOnly()!=null&&cr.getControlWorksPaperOnly())?"в рукописном":"";
            }
            Collections.sort(works,new OrderByDisciplineName());
        }
        return works;
    }

    public void setCwDatesGetter(StringConstantsGetter cwDatesGetter) {
        this.cwDatesGetter = cwDatesGetter;
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }

    @PostConstruct
    public void postConstract(){
        reExameBeginDate = semesterGetter.getConstant(CalendarConstantName.reExameBeginDate).toString();
        reExameEndDate = semesterGetter.getConstant(CalendarConstantName.reExameEndDate).toString();
        controlWorksBeginDate = cwDatesGetter.getConstant(ControlWorkCalendarConstantName.controlWorksBeginDate).toString();
        controlWorksEndDate = cwDatesGetter.getConstant(ControlWorkCalendarConstantName.controlWorksEndDate).toString();
    }

    public void setCw(AjaxBehaviorEvent event){
        currentControlWorks = (ControlWork) event.getComponent().getAttributes().get("cw");
    }

    public String saveCw() throws IOException{
         HttpServletRequest req = FacesUtil.getRequest();
         if(req instanceof MultipartRequestWrapper){
            MultipartRequestWrapper multi = (MultipartRequestWrapper)req;
            //CwFile -  имя файла в форме
            UploadFile uf = multi.findFile("CwFile");
            if(uf != null && currentControlWorks!=null){
                ControlWorkAttempt a = new ControlWorkAttempt();
                List<ControlWorkAttempt> s = (List<ControlWorkAttempt>) currentControlWorks.getCwAttempt();
                if(CollectionUtils.isEmpty(s)){
                    s = new ArrayList<ControlWorkAttempt>(1);
                    currentControlWorks.setCwAttempt(s);
                }
                s.add(a);
                a.setAttemptDate(DateConverter.convert(System.currentTimeMillis()));
                a.setWork(currentControlWorks);
                //controlWorks -  в данном случае имя папки и имя префикса в именах файлов
                String name = FileUploadUtil.Upload(uf, multi, "controlWorks",true);
                if(name!=null){
                    a.setFilePath(name);
                    if (currentControlWorks.getProgress()==null) currentControlWorks.setProgress(ControlWorkProgress.available);
                    controlWorkDao.save(currentControlWorks);
                    journalService.save(EventType.TASK_HAS_BEEN_SEND_TO_PREP, student, currentControlWorks.getCurriculum());
                }
            }
        }
        return "controlWorks?faces-redirect=true&amp;s="+getSemester();
    }

    public ControlWork getCurrentControlWorks() {
        return currentControlWorks;
    }

    public void setControlWorkDao(IControlWorkDao<ControlWork> controlWorkDao) {
        this.controlWorkDao = controlWorkDao;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    private class OrderByDisciplineName implements Comparator<Object[]>{

        @Override
        public int compare(Object[] o1, Object[] o2) {
            int discipline = 0;
            if(o1!=null &&o2!=null &&o1[1]!=null&&o2[1]!=null&&
                    ((ControlWork)o1[1]).getCurriculum()!=null&&((ControlWork)o2[1]).getCurriculum()!=null&&
                    ((ControlWork)o1[1]).<DeCurriculum>getCurriculum().getDiscipline()!=null &&((ControlWork)o2[1]).<DeCurriculum>getCurriculum().getDiscipline()!=null &&
                    ((ControlWork)o1[1]).<DeCurriculum>getCurriculum().getDiscipline().getName()!=null
                ){
                discipline = ((ControlWork)o1[1]).<DeCurriculum>getCurriculum().getDiscipline().getName().compareTo( ((ControlWork)o2[1]).<DeCurriculum>getCurriculum().getDiscipline().getName());
            }
            return discipline;
        }

    }
}
