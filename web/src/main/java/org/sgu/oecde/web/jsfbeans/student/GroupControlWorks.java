package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupCwBean")
@ViewScoped
public class GroupControlWorks extends StudentCurriculumBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    private Map<Student,ControlWork>map;

    private static final long serialVersionUID = 107L;

    public Map<Student,ControlWork> getControlWorkInfo4Group() {
        if(map==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(curriculumBuilder.getInstanceByCurrentDate(student, semester));
            map = cwService.<Student,ControlWork>getAllControlWorks(new ArrayList(student.getGroup().getPersons()), c);
        }
        return map;
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }
}