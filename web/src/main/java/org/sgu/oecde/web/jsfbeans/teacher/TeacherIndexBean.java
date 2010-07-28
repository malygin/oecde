package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.dao.IControlWorkAttemptDao;
import org.sgu.oecde.core.education.dao.IEstimateDao;
import org.sgu.oecde.de.users.Group;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherIndexBean")
@ViewScoped
public class TeacherIndexBean extends AbstractTeacherBean{
    @ManagedProperty(value="#{controlWorkAttemptDao}")
    IControlWorkAttemptDao controlWorkAttemptDao;

    @ManagedProperty(value="#{teacherSessionBean}")
    protected TeacherSessionBean teacherSessionBean;

    @ManagedProperty(value="#{estimateDao}")
    protected IEstimateDao estimateDao;

    public int getReadCwAttempsCount(){
        return controlWorkAttemptDao.getAttemptCountForTeacher(teacherSessionBean.getDisciplines(0), teacher, true);
    }

    public int getCwAttempsCount(){
        return controlWorkAttemptDao.getAttemptCountForTeacher(teacherSessionBean.getDisciplines(0), teacher, false);
    }

    public int getGroupsCount(){
        return teacherSessionBean.getGroups(0).size();
    }

    public int getEstimatedGroupsCount(){
        return estimateDao.getEstimatedGroupsCount(teacherSessionBean.getDisciplines(0), teacher);
    }

    public void setControlWorkAttemptDao(IControlWorkAttemptDao controlWorkAttemptDao) {
        this.controlWorkAttemptDao = controlWorkAttemptDao;
    }

    public void setEstimateDao(IEstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }
}