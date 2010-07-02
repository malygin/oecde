
package org.sgu.oecde.web.jsfbeans.messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.service.MessageService;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 01.07.2010
 * 
 */

@ManagedBean(name="MessageCountNewBean")
@RequestScoped
public class MessageCountNewBean {
    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

      public int getNewMessages() {
        return messageService.getCountNewMessage(SecurityContextHandler.getUser());
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

}
