
package org.sgu.oecde.web.jsfbeans.messages;
import java.util.Map;
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
 * Бин для чтения сообщения
 */

@ManagedBean(name="MessageReadBean")
@ViewScoped
public class MessageReadBean {

    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

  
    private boolean noAccess=true;
    private int id_message;
    //false если просмотр из исходящих сообщени
    AbstractUser userOut;
    private boolean type=true;
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
          if (message.getMessage().getAuthor().getId().equals(SecurityContextHandler.getUser().getId())){
               noAccess=false;
          }
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

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
         this.type = type;
         if(!type){
             userOut=(message.getMessage().getRecipients().get(0)).getRecipient();
         }
    }

    public AbstractUser getUserOut() {
        return userOut;
    }

    

}
