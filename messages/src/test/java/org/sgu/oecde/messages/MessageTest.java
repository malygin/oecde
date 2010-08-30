package org.sgu.oecde.messages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.sgu.oecde.messages.dao.IMessageDao;
import org.sgu.oecde.core.BasicTest;
import org.sgu.oecde.core.users.AbstractPerson;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Department;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.de.education.DeCurriculum;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;
import org.springframework.test.context.ContextConfiguration;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 07.06.2010
 *
 */

@ContextConfiguration(locations={"../applicationContext.xml","../spring/deBeans.xml"})
public class MessageTest extends BasicTest{

    @Ignore
    @Test
    public void save(){
        Message message= new Message();
        message.setTheme("ПРиветт");
        message.setType(MessageType.askMessage);
        message.setFullText("полный текст");
        message.setDateMessage(DateConverter.currentDate());
            Student person=new Student();
            person.setId(new Long(324725));
        message.setAuthor(person);
            MessageFile file= new MessageFile();
            file.setName("file");
            MessageFile file2= new MessageFile();
            file2.setName("file2");
            List files= new ArrayList();
            files.add(file);
            files.add(file2);

        Student st1=new Student();
        st1.setId(new Long(321073));
        MessageRecipient r1=new MessageRecipient();
        r1.setRecipient(st1);

        Admin st2=new Admin();
        st2.setId(new Long(1));
        MessageRecipient r2=new MessageRecipient();
        r2.setRecipient(st2);
      
        Teacher st3=new Teacher();
        st3.setId(new Long(44240));
        MessageRecipient r3=new MessageRecipient();
        r3.setRecipient(st3);
        
        List recps= new ArrayList();
        recps.add(r3);
        recps.add(r2);
        recps.add(r1);
        
        message.setFiles(files);
        message.setRecipients(recps);
        MessageService s = getBean("messageService");
        s.save(message);
    }

    @Ignore
    @Test
    public void getListAll(){
          setDao("messageDao");
          Message mess= new Message();
         // mess.setId(10);
         List <Message> list = this.<IMessageDao>getDao().getAll();
        for(Message m:list){
            System.out.println("__"+m.getFullText());
            System.out.println(" "+m.getAuthor().getUsername());
            System.out.println(" "+UserType.toType(m.getAuthor()));
            System.out.println(" "+m.getFiles());
            System.out.println(" "+m.getRecipients());
        }

 }

   @Ignore
    @Test
    public void getMessageById(){
          MessageService s = getBean("messageService");
          MessageImpl mess= s.getById(new Long(92));
         // mess.setId(10);
       
           System.out.println(" "+mess.getFioAuthor());
           System.out.println(" "+mess.getFilesExist());

 }
//    @Ignore
    @Test
    public void getListIn(){
          MessageService s = getBean("messageService");
          Student st1=new Student();
          st1.setId(new Long(321073));
          List<MessageImpl> list = s.getListInAll(st1, 2, 1);
          for(MessageImpl l:list){
              System.out.println(" "+l.getTypeAuthor());
              System.out.println(" "+l.getFioAuthor());
              System.out.println(" "+l.getMessage().getId());
             // System.out.println(" "+l.getRecipients());
             // System.out.println(" "+l.isArchived());
          //    System.out.println(" "+l.isDeleted());
              for(MessageRecipient r:l.getMessage().getRecipients()){
                  System.out.println("---"+l.getFioAuthor());
              }
              System.out.println("readed  "+l.getNew());
              System.out.println("               ");
          }
      //    System.out.println("! "+list);

 }
//    @Ignore
    @Test
    public void getListOut(){
          MessageService s = getBean("messageService");
          Student st=new Student(new Long(321073));
          List<MessageImpl> list = s.getListOutAll(st, 5, 2);
           for(MessageImpl l:list){
              System.out.println(" "+l.getMessage().getDateMessage());
              System.out.println(" "+l.getFioAuthor());
              System.out.println(" "+l.getMessage().getId());
             // System.out.println(" "+l.getRecipients());
             // System.out.println(" "+l.isArchived());
          //    System.out.println(" "+l.isDeleted());

              System.out.println("readed  "+l.getNew());
              System.out.println("               ");
          }
          System.out.println("! "+list);
 }
    @Ignore
    @Test
    public void update(){
      
        Student st1=new Student();
        st1.setId(new Long(321073));
        MessageService s = getBean("messageService");
     //   s.delete(message.getId(), st1);
      // s.archive(message.getId(), st1);
        s.read(new Long(102), st1);
 }


    @Ignore
    @Test
    public void getCountListIn(){
          MessageService s = getBean("messageService");
          Student st1=new Student();
          st1.setId(new Long(321073));
          System.out.println("new "+s.getCountNewMessage(st1));
          System.out.println("in  "+s.getCountMessageIn(st1));
          System.out.println("arch  "+s.getCountMessageArchive(st1));
          System.out.println("out  "+s.getCountMessageOut(st1));

 }

    @Ignore
    @Test
    public void getListArchive(){
         MessageService s = getBean("messageService");
         Student st1=new Student();
         st1.setId(new Long(321073));
          List<MessageImpl> list = s.getListArchive(st1, 5, 1);
          for(MessageImpl l:list){
              System.out.println(" "+l.getMessage().getId());
              System.out.println("               ");
          }
     }

//   @Ignore
    @Test
    public void getListDialog(){
        MessageService s = getBean("messageService");
         Student st1=new Student();
         st1.setId(new Long(321073));
         Student st2=new Student();
         st2.setId(new Long(324725));
          List<MessageImpl> list = s.getListDialog(st1, new Long(1));
          for(MessageImpl l:list){
              System.out.println(" "+l.getFioAuthor());
              System.out.println("               "+l.getMessage().getTheme());

          }
     }
     @Ignore
    @Test
    public void getCount(){
         MessageService s = getBean("messageService");
         Student st1=new Student();
         st1.setId(new Long(321073));
          System.out.println("_______"+s.getCountMessageOut(st1));
     }
}

