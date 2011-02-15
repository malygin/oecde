package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
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
import org.sgu.oecde.web.jsfbeans.util.NewEntry;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="lowGradedCwBean")
@ViewScoped
public class LowGradedControlWorks extends StudentCurriculumBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    private List<NewEntry<DeCurriculum,ControlWork>>m;

    private static final long serialVersionUID = 106L;

    public List<NewEntry<DeCurriculum,ControlWork>>getWorks(){
        if(m==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstanceByCurrentDate(student, semester));
            Map<DeCurriculum,ControlWork>map = cwService.<DeCurriculum,ControlWork>getStudensControlWorks(student, c);
            m = new ArrayList();
            NewEntry<DeCurriculum,ControlWork>e;
            for(DeCurriculum cur:c){
                ControlWork w = map.get(cur);
                if(w!=null&&(w.getCwAttempt()!=null&&w.getCwAttempt().size() > 0&&w.getProgress().equals(ControlWorkProgress.passed)))
                   continue; 
                e = new NewEntry<DeCurriculum, ControlWork>(cur, w);
                m.add(e);
            }
        }
        return m;
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }
}