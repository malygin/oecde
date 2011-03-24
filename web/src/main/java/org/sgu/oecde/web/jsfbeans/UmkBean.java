
package org.sgu.oecde.web.jsfbeans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.education.Module;
import org.sgu.oecde.core.education.Umk;
import org.sgu.oecde.core.education.resource.AbstractResource;
import org.sgu.oecde.core.education.resource.Task;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.web.ResourceService;
import org.sgu.oecde.web.jsfbeans.util.HTMLSanitiser;
import org.springframework.util.ObjectUtils;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 20.07.2010
 * бин для просмотра умк
 * @todo переработать
 */
@ManagedBean(name="umkBean")
@ViewScoped
public class UmkBean implements Serializable {

    @ManagedProperty(value="#{resourceService}")
    private ResourceService resourceService;

    @ManagedProperty(value="#{umkDao}")
    private IBasicDao<Umk>umkDao;

    private DeCurriculum curriculum;
    private Umk currentUmk;
    private Module currentModule;
    private Module nextModule;
    private Module prevModule;

    private Task currentTask;
    private Task prevTask;
    private Task nextTask;

    private String cId;
    private String moduleId;
    private String taskId;

    private List<Task> tasks =new ArrayList<Task>();
    private List<SelectItem> modules ;

    final private String mainUrl="";
    private String currentUrl="";
 

 
   // private String

    public UmkBean() {
    }


    public String getUrl() throws MalformedURLException{  
          return currentUmk.getFolder()+"/"+currentTask.getUrl();
    }

    public String getcId() {
        return cId;
    }

/**
 * @todo проверить на доступ и метод сервиса будет другой и возвращает он не только таски- учесть и это!
 * @todo сделать как работу с обычным массивом!
 */
    public void setcId(String cId) {
          this.cId = cId;
          if (SecurityContextHandler.getUser() instanceof Student){
             curriculum = resourceService.getDisciplineForStudent((Student) SecurityContextHandler.getUser(),new Long(cId),null);
             currentUmk=curriculum.getUmk();

          }else if(SecurityContextHandler.getUser() instanceof Admin){
              currentUmk=umkDao.getById(new Long(this.cId));
          }

          //храним временно значение для предыдущего модуля
          Module prevModuleTemp=null;
          currentUrl=mainUrl+currentUmk.getFolder();
          prevTask= new Task(0L);
          nextTask= new Task(0L);

          // вычисляем текущий таск, также следующий и последующие, кроме того - формируем список тасков
          for(Module m:currentUmk.getModules()){
            if (m == null)
                continue;
              //вытаскиваем первый таск следующего модуля, если мы смотрим последний таск текущего модуля
             if((currentModule!=null)&&(nextTask.getId().equals(0L))&&( !m.getResources().isEmpty())){
                 nextModule=m;
                 nextTask= m.<Task>getResources().iterator().next();
                 break;
             }
             //если это нужный нам модуль - вытаскиеваем нужный таск (+ следующий и предыдущий) из него и список всех тасков модуля
             if (m.getId().toString().equals(moduleId)){
                  currentModule=m;
                  for(Task t:m.<Task>getResources()){                  
                     if ((currentTask!=null)&&(nextTask.getId().equals(0L))) nextTask=t;
                     if (t.getId().toString().equals(taskId))  currentTask=t;
                     if (currentTask==null) prevTask=t;
                     tasks.add(t);
                   }                  
              }
             //если мы вытащили текущий модуль, он не первый, а занятие в нем первое, то вытаскиваем
             //для предыдущего модуля и таска соотвествующие значения
            //и если у этого модуля 1 занятие, продолжим итерацию цикла на проверку есть ли следующее занятие
             if((currentModule!=null)&&(prevModuleTemp!=null)&&(prevTask.getId().equals(0L))&&(currentModule.getResources().size()!=1)){
                 int length=prevModuleTemp.getResources().toArray().length;
                 if (length!=0){                 
                     prevTask=(Task) prevModuleTemp.getResources().toArray()[length-1];
                     prevModule=prevModuleTemp;
                    }
                  break;
             }
             // нашли текущий модуль, и если нужно предущий, сделаем еще одну итерацию для того чтобы следующий модуль получить
             if (currentModule!=null) continue;
             prevModuleTemp=m;
          }
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public DeCurriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(DeCurriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Module getCurrentModule() {
        return currentModule;
    }

    public void setCurrentModule(Module currentModule) {
        this.currentModule = currentModule;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<SelectItem> getModules() {
        modules=new ArrayList<SelectItem>();
         for(Module m:currentUmk.getModules()){
             modules.add(new SelectItem(m, m.getName()));
          }

        return modules;
    }

 public ResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public Task getNextTask() {
        return nextTask;
    }

    public void setNextTask(Task nextTask) {
        this.nextTask = nextTask;
    }

    public Task getPrevTask() {
        return prevTask;
    }

    public void setPrevTask(Task prevTask) {
        this.prevTask = prevTask;
    }

    public Module getNextModule() {
        if (nextModule==null) return currentModule;
        return nextModule;
    }

    public void setNextModule(Module nextModule) {
        this.nextModule = nextModule;
    }

    public Module getPrevModule() {
        if (prevModule==null) return currentModule;
        return prevModule;
    }

    public void setPrevModule(Module prevModule) {
        this.prevModule = prevModule;
    }

    public IBasicDao<Umk> getUmkDao() {
        return umkDao;
    }

    public void setUmkDao(IBasicDao<Umk> umkDao) {
        this.umkDao = umkDao;
    }

    public Umk getCurrentUmk() {
        return currentUmk;
    }

    public void setCurrentUmk(Umk currentUmk) {
        this.currentUmk = currentUmk;
    }

}
