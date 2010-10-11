package org.sgu.oecde.web.jsfbeans.teacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.controlworks.ControlWorkAttempt;
import org.sgu.oecde.controlworks.dao.ControlWorkAttemptDao;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.de.users.Student;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="controlWorksAttemptsBean")
@ViewScoped
public class ControlWorksAttemptsBean extends AbstractStudentsListBean{

    @ManagedProperty(value="#{controlWorkAttemptDao}")
    private ControlWorkAttemptDao controlWorkAttemptDao;

    private int elementsOnPage = 20;

    private int page = 0;
    
    private List<ControlWorkAttempt>attempts;

    private static final long serialVersionUID = 175L;

    public List<ControlWorkAttempt> getAttempts() {
        if(attempts == null){
            List<Group>tmpGroups = new ArrayList<Group>(teacherSessionBean.getGroups(semester));
            List<DeCurriculum>curriculums = new ArrayList<DeCurriculum>();
            List<Group>groups = new ArrayList<Group>();
            Iterator<DeCurriculum>i;
            List<DeCurriculum>tmp;
            for(Group g:tmpGroups){
                tmp = getGroupCurriculums(g);
                i = tmp.iterator();
                while(i.hasNext()){
                    DeCurriculum c = i.next();
                    if(c!= null && c.getGotControlWork()&&!c.getControlWorksPaperOnly()){
                        curriculums.add(c);
                    }else
                        i.remove();
                }
                if(tmp.size()>0)
                    groups.add(g);
            }
            int first = page*elementsOnPage;
            attempts = controlWorkAttemptDao.getAttemptsList(first, elementsOnPage, groups, curriculums);
        }
        return attempts;
    }

    public void setControlWorkAttemptDao(ControlWorkAttemptDao controlWorkAttemptDao) {
        this.controlWorkAttemptDao = controlWorkAttemptDao;
    }

    public int getElementsOnPage() {
        return elementsOnPage;
    }

    public void setElementsOnPage(int elementsOnPage) {
        this.elementsOnPage = elementsOnPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
