package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.List;
import javax.faces.bean.ManagedProperty;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.education.DeCurriculumBuilder;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
public abstract class AbstractTeacherCurriculumBean extends AbstractTeacherBean{
    private DeCurriculum curriculum;
    private Long curriculumId;

    @ManagedProperty(value="#{curriculumBuilder}")
    protected DeCurriculumBuilder curriculumBuilder;

    @ManagedProperty(value="#{teacherSessionBean}")
    protected TeacherSessionBean teacherSessionBean;

    private static final long serialVersionUID = 116L;

    public DeCurriculum getCurriculum(){
        if(curriculum==null){
            if(curriculumId!=null&&curriculumId!=0){
                DeCurriculum c = curriculumBuilder.getInstance(curriculumId);
                List<DeCurriculum>l = teacherSessionBean.getDisciplines(semester);
                if(!CollectionUtils.isEmpty(l)){
                    for(DeCurriculum d:l){
                        if(d.equals(c)){
                            curriculum = d;
                            break;
                        }
                    }
                }
            }
            setAccessDenied(curriculum==null);
        }
        return curriculum;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
        curriculum = null;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public TeacherSessionBean getTeacherSessionBean() {
        return teacherSessionBean;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }

    public void setCurriculumBuilder(DeCurriculumBuilder curriculumBuilder) {
        this.curriculumBuilder = curriculumBuilder;
    }
}
