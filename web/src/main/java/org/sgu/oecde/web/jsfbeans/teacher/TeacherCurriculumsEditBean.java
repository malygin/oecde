package org.sgu.oecde.web.jsfbeans.teacher;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.dao.ICurriculumDao;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="teacherCurriculumsEditBean")
@ViewScoped
public class TeacherCurriculumsEditBean implements Serializable{
    private int semester ;
    private List<DeCurriculum>curriculums;
    
    private DeCurriculum curriculum;
    private long curriculimId;
    
    private boolean error = false;
    private boolean saved = false;

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;

    @ManagedProperty(value="#{curriculumDao}")
    private ICurriculumDao<DeCurriculum> curriculumDao;

    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;

    private static final long serialVersionUID = 174L;

    public String save(){
        try {
            for(DeCurriculum c:curriculums){
                curriculumDao.update(c);
            }
            journalService.save(EventType.CURRICULUMS_CHANGING_BY_TEACHER, teacherSessionBean.getTeacher());
        } catch (Exception e) {
            e.fillInStackTrace();
        }finally{
            return "curriculums.xhtml.xhtml?faces-redirect=true&s="+semester;
        }
    }
    
        public void saveWeight(){
            if (curriculum.getWeightTest() <=40 ){
                        DeCurriculum curriculumR = curriculumDao.getById(curriculimId);
                        curriculum.setGotControlWork(curriculumR.getGotControlWork());
                        curriculumDao.update(curriculum);

                        journalService.save(EventType.CURRICULUMS_CHANGING_BY_TEACHER, teacherSessionBean.getTeacher());
                        saved=true;
                        error=false;
                teacherSessionBean.reloadCur();
            }else{
              DeCurriculum curriculumR = curriculumDao.getById(curriculimId);
                curriculum.setWeightTest(curriculumR.getWeightTest());
                curriculum.setWeightAud(curriculumR.getWeightAud());
                curriculum.setWeightOutAud(curriculumR.getWeightOutAud());
                curriculum.setWeightPers(curriculumR.getWeightPers());
                curriculum.setWeightAtt(curriculumR.getWeightAtt());
                //curriculum.setGotControlWork(curriculumR.getGotControlWork());

                error=true;
                saved=false;
            }    
        }


    public List<DeCurriculum> getCurriculums() {
        if(curriculums == null){
            curriculums = teacherSessionBean.getDisciplines(this.semester);
//            curriculums.addAll(teacherSessionBean.getDisciplines(Math.abs(this.semester -1)));
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
        semester = teacherSessionBean.semesterGetter.getCurrentSemester();
    }

    public void setCurriculumDao(ICurriculumDao<DeCurriculum> curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    public DeCurriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(DeCurriculum curriculum) {
        this.curriculum = curriculum;

    }

    public long getCurriculimId() {
        return curriculimId;
    }

    public void setCurriculimId(long curriculimId) {
        this.curriculimId = curriculimId;
        this.curriculum = curriculumDao.getById(curriculimId);
//        for (DeCurriculum d: getCurriculums()){
//            if (d.getId() == this.curriculimId)
//                curriculum = d;
//        }
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
    
    
}
