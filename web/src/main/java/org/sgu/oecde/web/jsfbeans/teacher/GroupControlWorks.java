package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWork;
import org.sgu.oecde.controlworks.ControlWorkService;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="groupControlWorksForTeacher")
@ViewScoped
public class GroupControlWorks extends AbstractStudentsListBean{

    @ManagedProperty(value="#{controlWorkService}")
    private  ControlWorkService cwService;

    private static final long serialVersionUID = 114L;

    Map<Student,ControlWork>works;

    public Map<Student,ControlWork> getGroupControlWorks() {
        if(works==null){
            List<DeCurriculum> c =  cwService.getCurriculumsWithControlWorks(getCurriculum());
            if(!CollectionUtils.isEmpty(c)&&c.size()==1)
                works = cwService.<Student,ControlWork>getCurriculumControlWorks(getStudentsList(), c.get(0));
            }
        return works;
    }

    public void setCwService(ControlWorkService cwService) {
        this.cwService = cwService;
    }
}
