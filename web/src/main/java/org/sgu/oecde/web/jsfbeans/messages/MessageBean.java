/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.web.jsfbeans.messages;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.dao.IMessageDao;
import org.sgu.oecde.messages.dao.MessageDao;


/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
@ManagedBean(name="MessageBean")
@SessionScoped
public class MessageBean {

    @ManagedProperty(value="#{messageDao}")
    private IMessageDao messageDao;

    private AbstractUser  currentUser;
    private List<Message> messages;
    private int newMessages=0;

    public MessageBean() {
        currentUser = SecurityContextHandler.getUser();
    }

    public IMessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessages() {
        return messageDao.getListInAll(currentUser);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getNewMessages() {     
        return messageDao.getCountMessage(this.currentUser);
    }

    public void setNewMessages(int newMessages) {
        this.newMessages = newMessages;
    }
    
}
