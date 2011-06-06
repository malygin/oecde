
package org.sgu.oecde.web.jsfbeans.messages;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.service.MessageService;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 01.07.2010
 * Бин для получения количества новых сообщений
 */

@ManagedBean(name="MessageCountNewBean")
@ViewScoped
public class MessageCountNewBean implements Serializable{
    private int number=-1;
    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

      public int getNewMessages() {
         if(number==-1) number=messageService.getCountNewMessage(SecurityContextHandler.getUser());
        return number;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
