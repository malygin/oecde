package org.sgu.oecde.web.jsfbeans.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.education.work.AdditionalSelfDependentWork;
import org.sgu.oecde.tests.TestAttemptService;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.student.StudentCurriculumBean;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="testResultsBean")
@ViewScoped
public class TestResultsBean extends StudentCurriculumBean{

    @ManagedProperty(value="#{testAttemptService}")
    private TestAttemptService testAttemptService;

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    private Long curriculumId;

    boolean reExame;
    
    private String currentDiscipline = null;
    private String currentTest = null;
    
    private List<AdditionalSelfDependentWork>attempts;
    private List<AdditionalSelfDependentWork>attemptsByCID;
    private String currentCID="";

    private static final long serialVersionUID = 98L;

    public List<AdditionalSelfDependentWork>getAttempts(){
        if(attempts==null){
            if(curriculumId==null||curriculumId==0)
                attempts = testAttemptService.getStudentAttempts(getCurriculums(),student,reExame);
            else{
                attempts = testAttemptService.getStudentSingleCurriculumTestsWithAttempts(curriculumBuilder.getInstance(curriculumId),student);
            }
        }
        return attempts;
    }
    
    public List<AdditionalSelfDependentWork>getAttemptsByCID(String id){
        if(!currentCID.equals(id) && !"".equals(id)){
            
            attemptsByCID= new ArrayList<AdditionalSelfDependentWork>(getAttempts());        
            Iterator<AdditionalSelfDependentWork> i=attemptsByCID.iterator();
            while (i.hasNext()){
                AdditionalSelfDependentWork asw=i.next();
               // System.out.println("id="+id+" cid="+asw.getCurriculum().getId());
                if (asw.getCurriculum().getId().equals(new Long(id))) continue;
                i.remove();            
            }
            currentCID=id;
           // System.out.println("");
        }
        return attemptsByCID;
    }

    public void setTestAttemptService(TestAttemptService testAttemptService) {
        this.testAttemptService = testAttemptService;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
        attempts = null;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public boolean isReExame() {
        return reExame;
    }

    public void setReExame(boolean reExame) {
        this.reExame = reExame;
        attempts = null;
    }

    public String getRegularAttemtpsCount() {
        return resourceService.getRegularAttemtpsCount(getSemester());
    }

    public String getConcludingAttemtpsCount() {
        return resourceService.getConcludingAttemtpsCount(getSemester());
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    public String getCurrentDiscipline() {
        return currentDiscipline;
    }
    
    public void setCurrentDiscipline(String currentDiscipline) {
        this.currentDiscipline = currentDiscipline;
    }
    
    public boolean IsCurrentDiscipline(String disciplineName, String disciplineWork){
        if((currentDiscipline!=null)&&(currentTest.equals(disciplineWork))) currentDiscipline=null;
        if((currentDiscipline==null)){
            currentDiscipline =disciplineName;
            currentTest=disciplineWork;
            return true;
        } else if((currentDiscipline.equals(disciplineName)) && !(currentTest.equals(disciplineWork))){
             return false;                    
             } else{
                currentDiscipline =disciplineName;   
                currentTest=disciplineWork;
                return true;
             }  
   }
    
}
