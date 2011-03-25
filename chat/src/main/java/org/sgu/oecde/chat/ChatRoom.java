
package org.sgu.oecde.chat;

import org.sgu.oecde.core.BasicItem;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 25.10.2010
 * комната для чата
 */
public class ChatRoom extends BasicItem {
    private String name;

    public ChatRoom() {
    }
    public ChatRoom(Long id) {
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
