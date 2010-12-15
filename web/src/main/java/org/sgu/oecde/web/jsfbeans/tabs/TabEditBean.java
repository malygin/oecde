package org.sgu.oecde.web.jsfbeans.tabs;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.tabs.Page;
import org.sgu.oecde.tabs.PageFile;
import org.sgu.oecde.tabs.Tab;
import org.sgu.oecde.tabs.TabType;
import org.sgu.oecde.tabs.dao.ITabsDao;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FacesUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FileUploadUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.MultipartRequestWrapper;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.UploadFile;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="tabEditBean")
@ViewScoped
public class TabEditBean implements Serializable{
    
    @ManagedProperty(value="#{tabsDao}")
    private ITabsDao tabsDao;
    private Page page;
    private Tab tab;
    private Long id,pageId;

    private static final long serialVersionUID = 162L;

    @Secured("ROLE_ADMIN")
    public String updateTab(){
        String redirect = null;
        if(pageId == null&&page!=null){
            tab.getPages().add(page);
            page.setTab(tab);
            redirect = "tabEdit.xhtml?faces-redirect=true&id="+tab.getId();
        } else
            redirect = "tabsView.xhtml?faces-redirect=true";
        try {
            tabsDao.update(tab);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return redirect;
    }

    @Secured("ROLE_ADMIN")
    public void deletePage(AjaxBehaviorEvent event){
        Page np = (Page) event.getComponent().getAttributes().get("page");
        tab.getPages().remove(np);
        tabsDao.update(tab);
    }

    @Secured("ROLE_ADMIN")
    public String deleteTab(){
        tabsDao.delete(tab);
        return "tabsView.xhtml?faces-redirect=true";
    }

    @Secured("ROLE_ADMIN")
    public String addFile() throws IOException {
        HttpServletRequest req = FacesUtil.getRequest();
        if(req instanceof MultipartRequestWrapper){
           MultipartRequestWrapper multi = (MultipartRequestWrapper)req;
           UploadFile uf = multi.findFile("file");
           if(uf != null){
              String name = FileUploadUtil.Upload(uf, multi, "tabs",true);
              if(name!=null){
                  PageFile pageFile = new PageFile();
                  pageFile.setImage(false);
                  pageFile.setName(name);
                  page.getFiles().add(pageFile);
                  pageFile.setPage(page);
                  tabsDao.update(tab);
              }
           }
        }
        return "pageEdit.xhtml?faces-redirect=true&id="+tab.getId()+"&p="+page.getId();
    }

    @Secured("ROLE_ADMIN")
    public void deleteFile(AjaxBehaviorEvent event){
        PageFile f = (PageFile) event.getComponent().getAttributes().get("file");
        page.getFiles().remove(f);
        tabsDao.update(tab);
//        tabsDao.removeFile(f);
    }

    public Tab getTab() {
        if(tab == null)
            tab = new Tab();
        return tab;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(id!=null&&!id.equals(0L)){
            this.id = id;
            tab = tabsDao.getById(id);
        }
    }

    public List<TabType> getTypes(){
        return TabType.getAllowedTypes();
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public Page getPage() {
        if(page == null){
            page = new Page();
            page.setFiles(new HashSet<PageFile>());
        }
        return page;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        if(pageId!=null && tab!=null&& tab.getPages()!=null)
            for(Page p: tab.getPages()){
                if(pageId.equals(p.getId())){
                    page = p;
                    break;
                }
            }
        this.pageId = pageId;
    }

    public void setTabsDao(ITabsDao tabsDao) {
        this.tabsDao = tabsDao;
    }
}
