
package org.sgu.oecde.web.jsfbeans.messages;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.dao.IMessageDao;
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

    private int id_message;
    private MessageImpl message;
 
    public MessageReadBean() {
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        AbstractUser currentUser = SecurityContextHandler.getUser();
       // System.out.println("читаю !"+id_message +" "+currentUser.toString());
        messageService.read(new Long(id_message), currentUser);
        this.id_message = id_message;
    }

    public MessageImpl getMessage() {
        if (message==null) message=messageService.getById(new Long(id_message));
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

}
