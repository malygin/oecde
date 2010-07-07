package org.sgu.oecde.web.jsfbeans.student;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkCalendarConstantName;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.core.education.CalendarConstantName;
import org.sgu.oecde.core.education.StringConstantsGetter;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="controlWorksBean")
@ViewScoped
public class ControlWorksBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    private List<Object[]>works;

    @ManagedProperty(value="#{cwDatesGetter}")
    private StringConstantsGetter cwDatesGetter;

    private String controlWorksBeginDate;
    private String controlWorksEndDate;
    private String reExameBeginDate;
    private String reExameEndDate;

    private static final long serialVersionUID = 105L;

    public List<Object[]>getControlWorks(){
        if(works==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstanceByCurrentDate(student, semester));
            Map<DeCurriculum,ControlWork> m = cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
            Iterator cwI = m.entrySet().iterator();
            String currentDate = DateConverter.currentDate();
            works = new LinkedList();
            while(cwI.hasNext()){
                Map.Entry<DeCurriculum,ControlWork> v = (Map.Entry)cwI.next();
                ControlWork w = v.getValue();
                boolean available=false;
                Object[] data = new Object[3];
                works.add(data);
                data[0] = v.getKey();
                data[1] = w;
                if(((currentDate.compareTo(controlWorksBeginDate)>=0
                    &&currentDate.compareTo(controlWorksEndDate)<0)
                    ||(currentDate.compareTo(reExameBeginDate)>=0
                    &&currentDate.compareTo(reExameEndDate)<0)
                    )&&!ControlWorkProgress.passed.equals(w.getProgress())){
                    available = true;
                }
                data[2] = available;
            }
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
        reExameBeginDate = semesterGetter.getConstant(CalendarConstantName.reExameBeginDate);
        reExameEndDate = semesterGetter.getConstant(CalendarConstantName.reExameEndDate);
        controlWorksBeginDate = cwDatesGetter.getConstant(ControlWorkCalendarConstantName.controlWorksBeginDate);
        controlWorksEndDate = cwDatesGetter.getConstant(ControlWorkCalendarConstantName.controlWorksEndDate);
    }
}
