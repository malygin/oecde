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
import org.springframework.test.context.ContextConfiguration;



/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 07.06.2010
 *
 */

@ContextConfiguration(locations={"../applicationContext.xml","../spring/messageBeans.xml","../spring/deBeans.xml"})
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
        st1.setId(new Long(320815));
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
        setDao("messageDao");
        this.<IMessageDao>getDao().save(message);
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
            System.out.println(" "+UserType.fromRole(m.getAuthor()));
            System.out.println(" "+m.getFiles());
            System.out.println(" "+m.getRecipients());
        }

 }

    @Ignore
    @Test
    public void getMessageById(){
          setDao("messageDao");
          Message mess= new Message();
         // mess.setId(10);
          mess=this.<IMessageDao>getDao().getById(new Long(90));
           System.out.println(" "+mess.getFullText());

 }
    //@Ignore
    @Test
    public void getListIn(){
          setDao("messageDao");
         Student st1=new Student();
         st1.setId(new Long(320815));
          List<Message> list = this.<IMessageDao>getDao().getListInAll(st1);
          for(Message l:list){
              System.out.println(" "+l.getId());
             // System.out.println(" "+l.isArchived());
          //    System.out.println(" "+l.isDeleted());
              System.out.println("readed  "+l.isReaded());
              System.out.println("               ");
          }
      //    System.out.println("! "+list);

 }
    @Ignore
    @Test
    public void getListOut(){
          setDao("messageDao");
          Student st=new Student(new Long(324725));
          List<Message> list = this.<IMessageDao>getDao().getListOutAll(st);
          System.out.println("! "+list);
 }
   @Ignore
    @Test
    public void update(){
        Message message= new Message();
        message.setId(new Long(92));
        Student st1=new Student();
        st1.setId(new Long(324725));
        setDao("messageDao");       
        this.<IMessageDao>getDao().delete(message, st1);
        this.<IMessageDao>getDao().archive(message, st1);
        this.<IMessageDao>getDao().read(message, st1);
 }


   @Ignore
    @Test
    public void getCountListIn(){
          setDao("messageDao");
         Student st1=new Student();
         st1.setId(new Long(320815));
          System.out.println("! "+this.<IMessageDao>getDao().getCountMessage(st1));

 }

    @Ignore
    @Test
    public void getListArchive(){
         setDao("messageDao");
         Student st1=new Student();
         st1.setId(new Long(320815));
          List<Message> list = this.<IMessageDao>getDao().getListArchive(st1);
          for(Message l:list){
              System.out.println(" "+l.getId()); 
              System.out.println("               ");
          }
     }

    @Ignore
    @Test
    public void getListDialog(){
         setDao("messageDao");
         Student st1=new Student();
         st1.setId(new Long(320815));
         Student st2=new Student();
         st2.setId(new Long(324725));
          List<Message> list = this.<IMessageDao>getDao().getListDialog(st1, st2);
          for(Message l:list){
              System.out.println(" "+l.getId());
              System.out.println("               ");
          }
     }
}

