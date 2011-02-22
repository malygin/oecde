package org.sgu.oecde.web.jsfbeans.student;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="termPaperBean")
@SessionScoped
public class TermPaper extends StudentCurriculumBean{

    private Boolean gotTermPaper;

    public boolean isGotTermPaper(){
        if(gotTermPaper==null){
            gotTermPaper=false;
            setSemester(0);
            List<DeCurriculum> list = getCurriculums();
            for(DeCurriculum s:list){
                if(s.getTermPapersNumber()>0){
                    gotTermPaper=true;
                    break;
                }
            }
        }
        return gotTermPaper.booleanValue();
    }

    public String getPath(){
        StringBuilder path = new StringBuilder();
        path.append(student.<Group>getGroup().getYear());
        path.append("/").append(student.getCity().getEngShort()).append(student.<Group>getGroup().getYear())
                .append(student.<Group>getGroup().getSpeciality().getEngShort()).append(".xls");
        return path.toString();
    }
}
