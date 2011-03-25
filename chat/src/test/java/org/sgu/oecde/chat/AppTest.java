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
        room.setId(2L);
      
      ChatMessage message=new ChatMessage();       
      Admin author = new Admin();
      author.setId(11L);
      message.setAuthor(author);
      message.setDateMessage(DateConverter.convert(System.currentTimeMillis()));
      message.setRoom(room);
      message.setMessage("22222222222");
      setDao("chatDao");
      this.<IChatDao>getDao().save(message);


    }

//  @Ignore
    @Test
    public void getList() {
      setDao("chatDao");
      List<ChatMessage> list =this.<IChatDao>getDao().getChatList(new ChatRoom(2L), 5);
      for(ChatMessage l:list){
          System.out.println(l.getDateMessage()+" "+l.getMessage());
      }

    }
   

}
