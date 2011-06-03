
package org.sgu.oecde.chat;

import org.sgu.oecde.core.BasicItem;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.AbstractUser;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 19.10.2010
 * сообщение для чата
 */
public class ChatMessage extends BasicItem  {

    private AbstractUser author;
    private ChatRoom room;
    private String message;
    private String dateMessage;

    public ChatMessage() {
    }

    public AbstractUser getAuthor() {
        return author;
    }

    public AbstractPerson getPerson(){
        return (AbstractPerson) author;
    }

    public void setAuthor(AbstractUser author) {
        this.author = author;
    }

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(String dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatRoom getRoom() {
        return room;
    }

    public void setRoom(ChatRoom room) {
        this.room = room;
    }


    
}
