package org.sgu.oecde.web.jsfbeans.messages;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;


/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
@ManagedBean(name="MessageBean")
@ViewScoped
public class MessageBean implements Serializable{    

    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

    private AbstractUser  currentUser;
    
    private List<MessageImpl> messages;
    private List<MessageImpl> messagesArchive;
    private List<MessageImpl> messagesOut;



    private boolean renderDeleteSuccess=false;

    private String currentMessageId;
    private String currentUserId;

    //Сообщений на странице
    private int messageOnPage=5;
    private int currentPage=1;

    public MessageBean() {
        currentUser = SecurityContextHandler.getUser();
       
    }
  
    public void deleteMessage(){
        //this.renderArchiveSuccess=false;
        this.renderDeleteSuccess=true;
        System.out.println("delete! "+currentMessageId);
        messageService.delete(new Long(currentMessageId), currentUser);
    }
 

   //-------- получение списков
    public List<MessageImpl> getMessages() {
        if (messages==null)messages=messageService.getListInAll(currentUser,messageOnPage,currentPage);
        return  messages;
    }

    public void setMessages(List<MessageImpl> messages) {
        this.messages = messages;
    }
  
    public List<MessageImpl> getMessagesArchive() {
        if (messagesArchive==null) messagesArchive=messageService.getListArchive(currentUser,2,1);
        return  messagesArchive;
    }

    public void setMessagesArchive(List<MessageImpl> messagesArchive) {
        this.messagesArchive = messagesArchive;
    }

    public List<MessageImpl> getMessagesOut() {
        if (messagesOut==null) messagesOut=messageService.getListOutAll(currentUser,2,1);
        return  messagesOut;
    }


    public void setMessagesOut(List<MessageImpl> messagesOut) {
        this.messagesOut = messagesOut;
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
