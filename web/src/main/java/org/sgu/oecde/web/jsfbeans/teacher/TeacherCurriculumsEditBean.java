package org.sgu.oecde.web.jsfbeans.teacher;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherCurriculumsEditBean")
@ViewScoped
public class TeacherCurriculumsEditBean implements Serializable{
    private int semester;
    private List<DeCurriculum>curriculums;

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;

    @ManagedProperty(value="#{curriculumDao}")
    private ICurriculumDao<DeCurriculum> curriculumDao;

    private static final long serialVersionUID = 174L;

    public String save(){
        for(DeCurriculum c:curriculums){
            curriculumDao.update(c);
        }
        return "curriculums.xhtml.xhtml?faces-redirect=true&s="+semester;
    }

    public List<DeCurriculum> getCurriculums() {
        if(curriculums == null){
            curriculums = teacherSessionBean.getDisciplines(semester);
        }
        return curriculums;
    }

    public void setCurriculums(List<DeCurriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }
}
