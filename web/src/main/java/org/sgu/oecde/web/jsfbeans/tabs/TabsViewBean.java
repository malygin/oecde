package org.sgu.oecde.web.jsfbeans.tabs;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.sgu.oecde.tabs.Page;
import org.sgu.oecde.tabs.Tab;
import org.sgu.oecde.tabs.TabType;
import org.sgu.oecde.tabs.dao.ITabsDao;
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
    
    private List<Tab>tabs;
    private Tab tab;
    private Page currentPage;
    
    private static final long serialVersionUID = 161L;

    public List<Tab> getTabs(String t){
        if(tabs == null&&t!=null){
            TabType type = TabType.valueOf(t.toUpperCase());
            Tab example = new Tab(type);
            tabs = tabsDao.getByExample(example);
            if(!CollectionUtils.isEmpty(tabs))
                if(!CollectionUtils.isEmpty(tabs.get(0).getPages()))
                    currentPage = tabs.get(0).getPages().iterator().next();
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

    public void setCurrentPage(AjaxBehaviorEvent event){
        currentPage = (Page) event.getComponent().getAttributes().get("page");
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setTabsDao(ITabsDao tabsDao) {
        this.tabsDao = tabsDao;
    }
}