/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.web.jsfbeans.messages;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.dao.IMessageDao;
import org.sgu.oecde.messages.dao.MessageDao;


/**
 *
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * @date 17.08.2010
 */
@ManagedBean(name="TestMessageBean")
@SessionScoped
public class TestMessageBean {
    private String text="hello i am bean!";

    @ManagedProperty(value="#{messageDao}")
    IMessageDao messageDao;

    private boolean renderTest=false;

    List<Message> messages;

    private int newMessages=0;
private String name="привет!";

    public TestMessageBean() {
   
    }
   
    @PostConstruct
    public void  init(){
        newMessages=10;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IMessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }


    public List<Message> getMessages() {
        Student st1=new Student();
        st1.setId(new Long(320815));
        messages=messageDao.getListInAll(st1);
        return messages;
    }

    public void actionRender(){
        this.renderTest=!this.renderTest;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getNewMessages() {
       Student st1=new Student();
       st1.setId(new Long(320815));
       return  messageDao.getCountMessage(st1);
      
    }

    public void setNewMessages(int newMessages) {
        this.newMessages = newMessages;
    }

    public boolean isRenderTest() {
        return renderTest;
    }

    public void setRenderTest(boolean renderTest) {
        this.renderTest = renderTest;
    }


    public String getName() {
        System.out.println("get!");
        return name;
      }

  public void setName(String name) {
      System.out.println("set!");
    this.name = name;
  }



  public String getReverseName() {
    return name+"dsfsdfs";
  }


    
}
