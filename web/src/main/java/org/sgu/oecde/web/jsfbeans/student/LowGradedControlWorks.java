package org.sgu.oecde.web.jsfbeans.student;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkProgress;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="lowGradedCwBean")
@ViewScoped
public class LowGradedControlWorks extends StudentCurriculumBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    private Map<DeCurriculum,ControlWork>m;

    private static final long serialVersionUID = 106L;

    public Map<DeCurriculum,ControlWork>getWorks(){
        if(m==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstanceByCurrentDate(student, semester));
            m = cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
            Iterator specsI = m.entrySet().iterator();
            while(specsI.hasNext()){
                Map.Entry<DeCurriculum,ControlWork> v = (Map.Entry)specsI.next();
                ControlWork w = v.getValue();
                if(w!=null)
                    if(w.getCwAttempt()!=null&&w.getCwAttempt().size() > 0&&w.getProgress().equals(ControlWorkProgress.passed)){
                        specsI.remove();
                    }
            }
        }
        return m;
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }
}