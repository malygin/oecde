package org.sgu.oecde.web.jsfbeans.admin;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.tests.TestEntity;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 */
@ManagedBean
@SessionScoped
public class UmkEventsBean implements Serializable{

    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;

    @ManagedProperty(value="#{adminSessionBean}")
    private AdminSessionBean adminSessionBean;

    private static final long serialVersionUID = 184L;

    @Secured({"ROLE_ADMIN"})
    public void logUmkEvent(AjaxBehaviorEvent event){
        Umk u = (Umk) event.getComponent().getAttributes().get("umk");
        String e = (String) event.getComponent().getAttributes().get("event");
        journalService.saveByStringType(e,adminSessionBean.getAdmin(), u);
    }

    @Secured({"ROLE_ADMIN"})
    public void logTestEvent(AjaxBehaviorEvent event){
        Umk u = (Umk) event.getComponent().getAttributes().get("umk");
        TestEntity t = (TestEntity) event.getComponent().getAttributes().get("umk");
        String e = (String) event.getComponent().getAttributes().get("event");
        journalService.saveByStringType(e,adminSessionBean.getAdmin(), u,t);
    }

    public void setAdminSessionBean(AdminSessionBean adminSessionBean) {
        this.adminSessionBean = adminSessionBean;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }
}
