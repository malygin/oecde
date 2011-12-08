package org.sgu.oecde.web.jsfbeans.messages;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.MessageType;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;


/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 * Бин для работы со списками сообщений
 */
@ManagedBean(name="MessageBean")
@ViewScoped
public class MessageBean implements Serializable{    

    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;  
    
//    @ManagedProperty (value="messageDao")
//    private IMessageDao messageDao;
    
    private List<MessageImpl> messages;
    private List<MessageImpl> messagesArchive;
    private List<MessageImpl> messagesOut;

    private boolean renderDeleteSuccess=false;

    private AbstractUser  currentUser;
    private String currentMessageId;
    private String currentUserId;

    //Сообщений на странице
    private int messageOnPage=10;
    private int currentPage=1;

    //число сообщений
   int numOfMessages = 0;

     private MessageType type = MessageType.all;
     private int typeInt=0;

     public Map getTypes() {
        return type.toMap();
    }

    public void setTypes(Map values) {
    }
    
    public MessageType getType() {
        return type;
    }
    // перенаправление на себя с сохранением параметра "тип"
    public void changeListByType() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("messages_list.xhtml?page=1&type="+type.toInt());
    }
    // 

    public int getTypeInt() {
        return typeInt;
    }

    public void setTypeInt(int typeInt) {
        this.typeInt = typeInt;
        this.type= MessageType.parse(typeInt);
    }
    
    
        
     //      
    public void setType(MessageType type) {
        
        this.type = type;
    }
    
    public MessageBean() {
        currentUser = SecurityContextHandler.getUser();
       
    }
  
    public void deleteMessage(){
 
        this.renderDeleteSuccess=true;       
        messageService.delete(new Long(currentMessageId), currentUser);
    }
 

   //-------- получение списков
    //все
    public List<MessageImpl> getMessages() {
        if (messages==null)messages=messageService.getListInAll(currentUser,messageOnPage,currentPage);
        return  messages;
    }
    //По типу 
   public List<MessageImpl> getMessagesByType() {
      if (messages==null){
          if(type==MessageType.all) messages=messageService.getListInAll(currentUser,messageOnPage,currentPage); //потому что не нулл
          else messages=messageService.getListSortedInAll(currentUser, type, messageOnPage, currentPage);
      }
      return  messages;
    }
  
    public List<MessageImpl> getMessagesArchive() {
        if (messagesArchive==null) messagesArchive=messageService.getListArchive(currentUser,2,1);
        return  messagesArchive;
    }

    public List<MessageImpl> getMessagesOut() {
        if (messagesOut==null) messagesOut=messageService.getListOutAll(currentUser,messageOnPage,currentPage);
        return  messagesOut;
    }


    //-------Получение количества сообщений в списках

    public int getNumOfMessages() {
        return messageService.getCountMessageIn(currentUser);
    }

    public int getNumOfMessagesArchive() {
      return messageService.getCountMessageArchive(currentUser);
    }

    public int getNumOfMessagesOut() {
         return messageService.getCountMessageOut(currentUser);
    }
    //получение списов по типу см MessageType

    public int getNumOfMessagesByType() {
         if(numOfMessages==0){
                 numOfMessages = messageService.getCountMessageByType(currentUser, type);
         }
        return numOfMessages;
    }

    //------------
  
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public AbstractUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AbstractUser currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isRenderDeleteSuccess() {
        return this.renderDeleteSuccess;
    }

    public void setRenderDeleteSuccess(boolean renderDeleteSuccess) {
        this.renderDeleteSuccess = renderDeleteSuccess;
    }

    public String getCurrentMessageId() {
        return currentMessageId;
    }

    public void setCurrentMessageId(String currentMessageId) {
        this.currentMessageId = currentMessageId;
    }

    public int getMessageOnPage() {
        return messageOnPage;
    }

    public void setMessageOnPage(int messageOnPage) {
        this.messageOnPage = messageOnPage;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }
    

}
