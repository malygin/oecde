package org.sgu.oecde.web.jsfbeans.messages;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.sgu.oecde.core.util.DateConverter;
import org.sgu.oecde.core.util.SecurityContextHandler;
import org.sgu.oecde.messages.Message;
import org.sgu.oecde.messages.MessageFile;
import org.sgu.oecde.messages.MessageRecipient;
import org.sgu.oecde.messages.MessageType;
import org.sgu.oecde.messages.service.MessageImpl;
import org.sgu.oecde.messages.service.MessageService;

/**
 * @author Andrey Malygin (mailto: anmalygin@gmail.com)
 * created 02.07.2010
 * 
 */

@ManagedBean(name="MessageWriteBean")
@ViewScoped
public class MessageWriteBean  implements Serializable{
    @ManagedProperty(value="#{messageService}")
    private MessageService messageService;

    private List<MessageFile> files = new ArrayList();
    private List<MessageRecipient> recipients= new ArrayList();
    private  boolean renderSuccessSend=false;

    private MessageImpl message;
    private String theme;
    private String fullText;

    public MessageWriteBean()  {
    
    }


    public void saveFromRead(){
        Message messageSave=new Message();
        messageSave.setFullText(fullText);
        messageSave.setTheme(theme);
        messageSave.setType(MessageType.askMessage);
        messageSave.setRecipients(recipients);
        messageSave.setDateMessage(DateConverter.currentDate());
        messageSave.setAuthor(SecurityContextHandler.getUser());
        messageService.save(messageSave);
        this.renderSuccessSend=true;

    }
    public void Save() {
        System.out.println("save1");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("_"+((HttpServletRequest) context.getRequest()).getParameterMap().get("file"));
          MultipartParser parser = null;
        String s = null;
        Part part;
        try{
             parser = new MultipartParser(((HttpServletRequest) context.getRequest()), 5 * 1024 * 1024);
             System.out.println("!!! "+parser);
             while ((part = parser.readNextPart()) != null) {
                    if (part.isFile()) {
                        System.out.println("----"+part);
                        // It's a file part I am
                        //  after
                        FilePart filePart = (FilePart) part;
                     //   filePart.setRenamePolicy(fileRename);
                        String fileName = filePart.getFileName();
                        if (fileName != null) {
                       //     filePart.setRenamePolicy(fileRename);
                         //   long filesize = filePart.writeTo(new java.io.File(webTempPath));
                           // System.out.println("filename: " + fileName+"__"+ filePart.getFileName());
//                            ServletContext servletContext = this.getServletContext();
                          //  PhotoDAO dao = (PhotoDAO) SpringContext.getApplicationContext().getBean("photoDAO");

                    }
                }}

        
    }catch(IOException ex){
            System.out.println("error! "+ex);
    }
    }

    
    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public MessageImpl getMessage() {
        return message;
    }

    public void setMessage(MessageImpl message) {
             this.message = message;
            MessageRecipient recipient=new MessageRecipient();
            recipient.setRecipient(this.message.getMessage().getAuthor());
            recipients.add(recipient);
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public boolean isRenderSuccessSend() {
        return renderSuccessSend;
    }

    public void setRenderSuccessSend(boolean renderSuccessSend) {
        this.renderSuccessSend = renderSuccessSend;
    }


}
