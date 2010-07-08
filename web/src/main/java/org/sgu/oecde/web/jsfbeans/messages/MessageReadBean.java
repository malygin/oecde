
package org.sgu.oecde.web.jsfbeans.messages;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.MessageRecipient;
import org.sgu.oecde.messages.MessageType;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 28.06.2010
 *
 */

@ManagedBean(name="MessageReadBean")
@ViewScoped
public class MessageReadBean {

    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

    private MessageType mt=MessageType.privateMessage;

    private boolean noAccess=true;

    private int id_message;
    private MessageImpl message;
 
    public MessageReadBean() {
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;    
        if (message==null){ message=messageService.getById(new Long(id_message));
           //проверяем доступ к письму
           for(MessageRecipient r: message.getMessage().getRecipients()){
              if (r.getRecipient().getId().equals(SecurityContextHandler.getUser().getId())){
                   noAccess=false;
                   messageService.read(new Long(id_message), SecurityContextHandler.getUser());
               }
           }
        }       
    }

    public String delete(){
        messageService.delete(new Long(id_message), SecurityContextHandler.getUser());
        return "messages_list";
    }
    public MessageImpl getMessage() {
      
        return message;
    }

    public void setMessage(MessageImpl message) {
        this.message = message;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public boolean isNoAccess() {
        return noAccess;
    }

    public void setNoAccess(boolean noAccess) {
        this.noAccess = noAccess;
    }

    public MessageType getMt() {
        return mt;
    }

    public void setMt(MessageType mt) {
        this.mt = mt;
    }


}
