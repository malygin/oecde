package org.sgu.oecde.web.jsfbeans.journal;

import javax.faces.bean.ManagedBean;
import org.sgu.oecde.journal.EventBodyElement;
import org.springframework.stereotype.Service;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
public class PageSetterBean {

    public PageSetterBean() {
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
        EventBodyElement.umkPage = "umk.xhtml";
    }
}
