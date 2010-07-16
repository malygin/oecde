
package org.sgu.oecde.web.jsfbeans.messages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.*;
import org.sgu.oecde.core.IBasicDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.MessageRecipient;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;


/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 05.07.2010
 * Бин для диалога в сообщениях
 */

@ManagedBean(name="messageDialogBean")
@ViewScoped
public class MessageDialogBean implements  Serializable {

    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

    @ManagedProperty(value="#{userDao}")
    private IBasicDao userDao;

    private AbstractUser  currentUser;
    private String recipientId;    
    private boolean noDialog=true;
    private MessageRecipient recipient;
    private List<MessageImpl> messagesDialog;

 
    public MessageDialogBean() {
        currentUser = SecurityContextHandler.getUser();
    }


    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;     
        messagesDialog=messageService.getListDialog(currentUser,new Long(recipientId));
        if (!messagesDialog.isEmpty()){
            noDialog=false;
        }
    }

    public MessageRecipient getRecipient() {       
        if (recipient==null){
           recipient=new MessageRecipient();
           recipient.setRecipient((AbstractUser) userDao.getById(new Long(recipientId)));
        
        }
        return recipient;
    }

    public void setRecipient(MessageRecipient recipient) {
        this.recipient = recipient;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public List<MessageImpl> getMessagesDialog() {
        return  messagesDialog;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public AbstractUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AbstractUser currentUser) {
        this.currentUser = currentUser;
    }

    public IBasicDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IBasicDao userDao) {
        this.userDao = userDao;
    }

    public boolean isNoDialog() {
        return noDialog;
    }

    public void setNoDialog(boolean noDialog) {
        this.noDialog = noDialog;
    }


}
