package org.sgu.oecde.web.jsfbeans.tabs;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletException;
import org.sgu.oecde.core.util.LangEnum;
import org.sgu.oecde.tabs.Page;
import org.sgu.oecde.tabs.Tab;
import org.sgu.oecde.tabs.TabType;
import org.sgu.oecde.tabs.dao.ITabsDao;
import org.sgu.oecde.web.jsfbeans.UserSessionBean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="tabsViewBean")
@ViewScoped
public class TabsViewBean implements Serializable{
    
    @ManagedProperty(value="#{tabsDao}")
    private ITabsDao tabsDao;
   
    @ManagedProperty(value="#{userSessionBean}")
    private UserSessionBean userSessionBean;
    
    private List<Tab>tabs;
    private Tab tab;
    private Page currentPage;
    private String type;
    private TabType tabtype;
    private int idTab;
    private Boolean isSaved=false;
    
    private static final long serialVersionUID = 161L;

   @Secured("ROLE_ADMIN")
    public void addTabs(){
       tab.setType(tabtype);
       tabsDao.update(tab);
       isSaved=true;
        
    }
   
      @Secured("ROLE_ADMIN")
    public void deleteTabs(){
       tab=tabsDao.getById(new Long(idTab));
       tabsDao.delete(tab);
       tab=null;
       isSaved=true;        
    }
    
    
    public List<Tab> getTabs(){
        if(tabs == null||isSaved){         
            Tab example = new Tab(tabtype);
            example.setOrderTab(null);
            tabs = tabsDao.getByExample(example);
            isSaved=false;
//            if(!CollectionUtils.isEmpty(tabs))
//                if(!CollectionUtils.isEmpty(tabs.get(0).getPages()))
//                    currentPage = tabs.get(0).getPages().iterator().next();
        }
        return tabs;
    }
    
    

    public Tab getTab(String t){
        if(tab ==null){
            TabType type = TabType.valueOf(t.toUpperCase());
            if(type.isSingleton()){
                Tab example = new Tab(type);
                List<Tab>tabs = tabsDao.getByExample(example);
                if(tabs.size()==1){
                    tab = tabs.get(0);
                }
            }
        }
        return tab;
    }

    public List<Tab> getAll(){
        if(tabs == null){
            tabs = tabsDao.getAll();
        }
        return tabs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        try{
           tabtype = TabType.valueOf(type.toUpperCase());}
        catch(IllegalArgumentException e){
            tabtype= TabType.STUDENT_HELP;
        }
    }

    public void setCurrentPage(AjaxBehaviorEvent event){
        currentPage = (Page) event.getComponent().getAttributes().get("page");
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setTabsDao(ITabsDao tabsDao) {
        this.tabsDao = tabsDao;
    }

    public Tab getTab() {
        if (tab==null) tab=new Tab();
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public TabType getTabtype() {
        return tabtype;
    }

    public void setTabtype(TabType tabtype) {
        this.tabtype = tabtype;
    }

    public int getIdTab() {
        return idTab;
    }

    public void setIdTab(int idTab) {
        this.idTab = idTab;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

 
    
    
}