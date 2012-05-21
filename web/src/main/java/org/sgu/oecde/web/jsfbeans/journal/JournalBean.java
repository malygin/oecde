package org.sgu.oecde.web.jsfbeans.journal;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.journal.EventBodyElement;
import org.sgu.oecde.journal.EventItem;
import org.sgu.oecde.journal.dao.IJournalDao;
import org.sgu.oecde.journal.FilterType;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@ViewScoped
public class JournalBean implements Serializable{

    private List<FilterType.EventForChoise>availableEvents;

    private List<EventItem>events;

    private int pageNumber = 1;
    
    private int numberOfEvents=15;

    private FilterType filter;

    private Integer eventsCount;

    @ManagedProperty(value="#{journalDao}")
    private IJournalDao journalDao;

    @ManagedProperty(value="#{journalFilters}")
    private JournalFilters journalFilters;
    
    
  
    private String IdUser;

    private static final long serialVersionUID = 185L;

    static {
        EventBodyElement.adminPage = "admin.xhtml";
        EventBodyElement.curriculumPage = "curriculum.xhtml";
        EventBodyElement.disciplinePage = "discipline.xhtml";
        EventBodyElement.forumStudentTechPage = "forum.xhtml?type=stfaq";
        EventBodyElement.forumStudentOrgPage = "forum.xhtml?type=storg";
        EventBodyElement.forumTeacherOrgPage = "forum.xhtml?type=teachorg";
        EventBodyElement.forumTeacherTechPage = "forum.xhtml?type=teachfaq";
        EventBodyElement.forumAdminBagPage = "forum.xhtml?type=adminbags";
        EventBodyElement.groupPage = "group.xhtml";
        EventBodyElement.newsPage = "news_read.xhtml";
        EventBodyElement.studentPage = "student.xhtml";
        EventBodyElement.supervisorPage = "supervisor.xhtml";
        EventBodyElement.taskPage = "task.xhtml";
        EventBodyElement.teacherPage = "teacher.xhtml";
        EventBodyElement.testPage = "test.xhtml";
        EventBodyElement.umkPage = "umk.xhtml";
        EventBodyElement.whoIs = "http://whois.domaintools.com/";
    }

    public String clearEvents() throws IOException{
        events = null;
        pageNumber = 1;
        eventsCount = null;
        if (filter == FilterType.adminStudentEvents){
             FacesContext.getCurrentInstance().getExternalContext().redirect("studentEvents.xhtml?id="+IdUser);
              return "studentEvents.xhtml?id=11"+IdUser;
        }
        return "index.xhtml?faces-redirect=true";
    }

    public FilterType filter(String type, Object object){
        if(filter == null){
            filter = FilterType.valueOf(type);
            filter.setObject(object);
        }
        return filter;
    }

    public List<FilterType.EventForChoise>getAvailableEvents(String type, Object object){
    
        if(availableEvents == null){
           if (object!= null) {
               if(object instanceof Long) IdUser=((Long) object).toString();
               else IdUser=( (AbstractUser) object).getId().toString();
           }
            filter(type,object);
            availableEvents = journalFilters.getFilter(filter);
        }
        return availableEvents;
    }

    public List<EventItem>getEvents(){
        return events(null,null,0);
    }

    public List<EventItem>events(String type,Object object, int numberOfEvents){
        
        if(events == null){
            if(type!=null||object == null)
                filter(type,object);
            if (numberOfEvents!=0) filter.setCapacity(numberOfEvents);
            events = journalDao.getEvents(filter, pageNumber);
        }
        return events;
    }

    public int getEventsCount() {
        if(eventsCount == null){
            eventsCount = journalDao.getCountOfEvents(filter);
        }
        return eventsCount;
    }

    public FilterType getFilter() {
        return filter;
    }

    public void setAvailableEvents(List<FilterType.EventForChoise> availableEvents) {
        this.availableEvents = availableEvents;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setJournalDao(IJournalDao journalDao) {
        this.journalDao = journalDao;
    }
    
    public void setJournalFilters(JournalFilters journalFilters) {
        this.journalFilters = journalFilters;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }


}