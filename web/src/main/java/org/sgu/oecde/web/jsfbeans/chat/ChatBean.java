package org.sgu.oecde.web.jsfbeans.chat;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.sgu.oecde.chat.ChatMessage;
import org.sgu.oecde.chat.ChatRoom;
import org.sgu.oecde.chat.IChatDao;
import org.sgu.oecde.core.users.AbstractUser;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author ShihovMY
 */
@ManagedBean(name="chatBean")
@ViewScoped
public class ChatBean implements Serializable{
    @ManagedProperty(value="#{chatDao}")
    private IChatDao chatDao;
    
    private List<ChatMessage> listMessages=null;

    protected AbstractUser user;

    //по умолчанию тянем студенческий чат
    private String idChat="1";
    private int numberMessage=500;

    private static final long serialVersionUID = 163L;

    public ChatBean() {
    }

    public IChatDao getChatDao() {
        return chatDao;
    }

    public void setChatDao(IChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public String getIdChat() {
        return idChat;
    }

    @Secured("ROLE_ADMIN")
    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }

    public int getNumberMessage() {
        return numberMessage;
    }

    public void setNumberMessage(int numberMessage) {
        this.numberMessage = numberMessage;
    }

    public List<ChatMessage> getListMessages() {
        if (listMessages==null)  listMessages=chatDao.getChatList(new ChatRoom(new Long(idChat)), numberMessage);
        return listMessages;
    }

    public void setListMessages(List<ChatMessage> listMessages) {
        this.listMessages = listMessages;
    }

  
}
