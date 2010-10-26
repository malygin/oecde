package org.sgu.oecde.chat;


import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.util.DateConverter;



public class AppTest extends BasicTest{

   @Ignore
    @Test
    public void addMessage() {
        ChatRoom room=new ChatRoom();
        room.setId(1L);
      
      ChatMessage message=new ChatMessage();       
      Admin author = new Admin();
      author.setId(1L);
      message.setAuthor(author);
      message.setDateMessage(DateConverter.convert(System.currentTimeMillis()));
      message.setRoom(room);
      message.setMessage("11111111111111111");
      setDao("chatDao");
      this.<IChatDao>getDao().save(message);


    }

   @Ignore
    @Test
    public void getList() {
      setDao("chatDao");
      List<ChatMessage> list =this.<IChatDao>getDao().getChatList(1L, 5);
      for(ChatMessage l:list){
          System.out.println(l.getDateMessage()+" "+l.getMessage());
      }

    }
   

}
