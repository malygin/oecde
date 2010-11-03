package org.sgu.oecde.web.jsfbeans.journal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
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

    private int pageNumber;

    private FilterType filter;

    private Integer eventsCount;

    @ManagedProperty(value="#{journalDao}")
    private IJournalDao journalDao;

    @ManagedProperty(value="#{journalFilters}")
    private JournalFilters journalFilters;

    private static final long serialVersionUID = 185L;

    static {
        EventBodyElement.adminPage = "admin.xhtml";
        EventBodyElement.curriculumPage = "curriculum.xhtml";
        EventBodyElement.disciplinePage = "discipline.xhtml";
        EventBodyElement.forumPage = "forum.xhtml";
        EventBodyElement.groupPage = "group.xhtml";
        EventBodyElement.newsPage = "news_read.xhtml";
        EventBodyElement.studentPage = "student.xhtml";
        EventBodyElement.supervisorPage = "supervisor.xhtml";
        EventBodyElement.taskPage = "task.xhtml";
        EventBodyElement.teacherPage = "teacher.xhtml";
        EventBodyElement.testPage = "test.xhtml";
        EventBodyElement.umkPage = "exbook.xhtml";
    }

    public void clearEvents(AjaxBehaviorEvent event){
        events = null;
        pageNumber = 0;
        eventsCount = null;
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
            filter(type,object);
            availableEvents = journalFilters.getFilter(filter);
        }
        return availableEvents;
    }

    public List<EventItem>getEvents(){
        return events(null,null);
    }

    public List<EventItem>events(String type,Object object){
        if(events == null){
            if(type!=null||object == null)
                filter(type,object);
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
}