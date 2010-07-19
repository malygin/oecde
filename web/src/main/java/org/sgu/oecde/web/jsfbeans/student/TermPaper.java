package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.de.education.DeCurriculum;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="termPaperBean")
@SessionScoped
public class TermPaper extends StudentCurriculumBean{

    public boolean isGotTermPaper(){
        boolean got=false;
        setSemester(0);
        List<DeCurriculum> list = getCurriculums();
        for(DeCurriculum s:list){
            if(s.getTermPapersNumber()>0){
                got=true;
                break;
            }
        }
        return got;
    }
}
