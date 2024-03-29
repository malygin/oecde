package org.sgu.oecde.web.jsfbeans.messages;


import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.UpdateDao;
import org.sgu.oecde.core.education.LevelTypeSpeciality;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.de.education.City;
import org.sgu.oecde.de.education.dao.IGroupDao;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.journal.EventType;
import org.sgu.oecde.journal.JournalService;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.MessageFile;
import org.sgu.oecde.messages.MessageRecipient;
import org.sgu.oecde.messages.MessageType;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;
import org.sgu.oecde.web.jsfbeans.teacher.TeacherSessionBean;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FacesUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.UploadFile;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FileUploadUtil;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 02.07.2010
 * бин для написания сообщения
 */

@ManagedBean(name="MessageWriteBean")
@ViewScoped
public class MessageWriteBean  implements Serializable{

    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

    @ManagedProperty(value="#{userDao}")
    private UpdateDao userDao;
    
    
    @ManagedProperty(value="#{groupDao}")
    private IGroupDao groupDao;
    
    @ManagedProperty(value="#{journalService}")
    private JournalService journalService;
    
    
    private List<MessageFile> files = new ArrayList();
    private List<MessageRecipient> recipients= new ArrayList();
    private List<StudentGroup> groups = new ArrayList();
    
    private String groupId;

    private String messageNameForDelete="";
    private String recipientId="";
    private String recipientIdRemove="";
    private List<SelectItem> availableRecipients= new ArrayList();
    private SelectItem currentRecipient= new SelectItem();
    private  boolean renderSuccessSend=false;

    private MessageImpl message;
    private String theme="";
    private String fullText;

    private Integer countStudent;

    @ManagedProperty(value="#{teacherSessionBean}")
    private TeacherSessionBean teacherSessionBean;


    
    //!edited//////////////////////////////////////////////
    private MessageType type = MessageType.privateMessage;
    
     public Map getTypes() {
        return type.toMapForWrite();
    }

    public void setTypes(Map values) {
    }
    
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    public MessageWriteBean()  {
    
    }

    
    public void save() throws IOException{
        Message messageSave=new Message();
        messageSave.setFullText(fullText);
        messageSave.setTheme(theme);
       messageSave.setType(type); 
        if (! groups.isEmpty()){
            for(StudentGroup gr:groups){
                for(Object u: gr.getPersons()){
                          MessageRecipient recipient= new MessageRecipient();
                          recipient.setRecipient((AbstractUser)u);
                          recipients.add(recipient);                                  
                 }
            }
        }
        messageSave.setRecipients(recipients);
        recipientId=(recipients.get(0)).getRecipient().getId().toString();
        messageSave.setDateMessage(DateConverter.currentDate());
        messageSave.setAuthor(SecurityContextHandler.getUser());
        messageSave.setFiles(files);
        messageService.save(messageSave);
        if (!groups.isEmpty()){
            recipients =new ArrayList<MessageRecipient> ();
            journalService.save(EventType.SPAM_GROUP, SecurityContextHandler.getUser(),groups.get(0).getId());
        }
             
     // recipients= new ArrayList();
      this.renderSuccessSend=true;
    //FacesContext.getCurrentInstance().getExternalContext().redirect("messages_write.xhtml?user="+user);
    }

    public void saveFromRead(){
        Message messageSave=new Message();
        messageSave.setFullText(fullText);
        messageSave.setTheme(theme);
        messageSave.setType(MessageType.askMessage);//?
           messageSave.setRecipients(recipients);
        messageSave.setDateMessage(DateConverter.currentDate());
        messageSave.setAuthor(SecurityContextHandler.getUser());
        messageService.save(messageSave);
        this.renderSuccessSend=true;
    }

    /**
     * добавляем файл к списку файлов письма
     * @throws IOException
     */
    public void addFile() throws IOException, ServletException {    
        HttpServletRequest multi = FacesUtil.getRequest();  
        //MessageFile -  имя файла в форме
        UploadFile uf = FileUploadUtil.findFile(multi,"MessageFile");
        if((uf != null)&&(!uf.getFileName().equals(""))){
         MessageFile mfile= new MessageFile();
         //messages -  в данном случае имя папки и имя префикса в именах файлов
         String name = FileUploadUtil.Upload(uf, multi, "messages",true);
         if(name!=null){
             mfile.setName(name);
             files.add(mfile);
         }
      }
    }

   /** 
    * Удаление файла, такое хитрое -  потому что джсф, сука, не хочет пустой список воспринимать
    * @throws IOException
    */
    public void removeFile() throws IOException {
       if (files.size()==1){
           files= new ArrayList();
       }else{           
       for(MessageFile m:files){
           if (m.getName().equals(messageNameForDelete)){
               files.remove(m);
           }
       }}   
    }

    /**
     * Удаление адресата
     * @throws IOException
     */
     public void removeRecipient() throws IOException {
        // System.out.println("_"+recipientIdRemove);
       if (recipients.size()==1){
           recipients= new ArrayList();
         //  System.out.println("!"+recipients.size());
       }else{

       for(MessageRecipient m:recipients){
            //   System.out.println("!!"+recipients.size());
           if (m.getRecipient().getId().equals(new Long(recipientIdRemove))){
               recipients.remove(m);
           }
       }}
    }
   /**
    * фиксируем адресата из адрессной строки, так как у нас страничка перегружается (из за мультипа
    * рт формы), то как загрузили адресата - пометили чтобы больше не грузился
    * @param recipient_id2
    */
   public void setRecipient_id(String recipient_id2) {
//       System.out.println("1 - "+recipientId.equals("exist"));
//       System.out.println("2 - "+recipientId.equals(""));
        if (!recipient_id2.equals("")){
            if (!recipientId.equals("exist")){
            recipients= new ArrayList();
            MessageRecipient recipient= new MessageRecipient();
            recipient.setRecipient((AbstractUser) userDao.getById(new Long(recipient_id2)));
            recipients.add(recipient);
            recipientId="exist";
            }
        }
    }
   /**
    * загрузим сообщений из чтения сообщния, достанем из него автора и засунем в получатели нового письма
    * @param message
    */
     public void setMessage(MessageImpl message) {
             this.message = message;
            MessageRecipient recipient=new MessageRecipient();
            recipient.setRecipient(this.message.getMessage().getAuthor());
            recipients.add(recipient);
    }
        
    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) throws UnsupportedEncodingException {
       this.fullText=fullText;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) throws UnsupportedEncodingException {
        this.theme=theme;
       
    }

    public MessageImpl getMessage() {
        return message;
    }
  
    public boolean isRenderSuccessSend() {
        return renderSuccessSend;
    }

    public void setRenderSuccessSend(boolean renderSuccessSend) {
        this.renderSuccessSend = renderSuccessSend;
    }

    public List<MessageFile> getFiles() {
        return files;
    }

    public void setFiles(List<MessageFile> files) {
        this.files = files;
    }

    public String getMessageNameForDelete() {
        return messageNameForDelete;
    }

    public void setMessageNameForDelete(String messageNameForDelete) {
        this.messageNameForDelete = messageNameForDelete;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getRecipient_id() {
        return recipientId;
    }

  

    public List<MessageRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<MessageRecipient> recipients) {
        this.recipients = recipients;
    }

    public UpdateDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UpdateDao userDao) {
        this.userDao = userDao;
    }

    public String getRecipient_id_remove() {
        return recipientIdRemove;
    }

    public void setRecipient_id_remove(String recipient_id_remove) {
        this.recipientIdRemove = recipient_id_remove;
    }

    public List<SelectItem> getAvailableRecipients() {
      //  System.out.println("get!");
        SelectItem s=new  SelectItem("1", "Шихов");
        SelectItem s1=new SelectItem("2", "Шиховский");
        SelectItem s2=new SelectItem("3", "Коржов");
        availableRecipients.add(s);
        availableRecipients.add(s1);
        availableRecipients.add(s2);
        return availableRecipients;
    }

    public SelectItem getCurrentRecipient() {
        return currentRecipient;
    }

    public void setCurrentRecipient(SelectItem currentRecipient) {
    //    System.out.println("set current!");
        this.currentRecipient = currentRecipient;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        if (groupId.equals("all")){
            groups.addAll(teacherSessionBean.getGroups(teacherSessionBean.getSemester()));
            count_students(groups);
        }else if(groupId.equals("allall")){
            List<Object[]>l = groupDao.getAllGroupsAndCities(2012);
            for(Object[] e: l){
                Group g = (Group) e[0];
                //City city = (City) e[1];
//                if ( !g.getSpeciality().getId().equals(new Long(2839)) && !g.getSpeciality().getId().equals(new Long(2646)) && g.getSpeciality().getLevelTypeSpeciality() != LevelTypeSpeciality.magistracy) {
                    if (!groups.contains(g)) groups.add(g);
//                }
            }
            count_students(groups);
        }else{
            this.groupId = groupId;
            Group g =  groupDao.getById(new Long(groupId));
            if (!groups.contains(g)) groups.add(g);  }
    }

    private void count_students(List<StudentGroup> groups){
        countStudent =0;
        for (StudentGroup g:groups){
                countStudent+=g.getNumber();
        }
    }

    public void setLetterStudents(String groupId) {
        groups.addAll(teacherSessionBean.getGroups(teacherSessionBean.getSemester()))  ;
    }


    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<StudentGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<StudentGroup> groups) {
        this.groups = groups;
    }

    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }

    public void setTeacherSessionBean(TeacherSessionBean teacherSessionBean) {
        this.teacherSessionBean = teacherSessionBean;
    }

    public Integer getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(Integer countStudent) {
        this.countStudent = countStudent;
    }
}
