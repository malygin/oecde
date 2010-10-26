/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.web.jsfbeans.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.chat.ChatMessage;
import org.sgu.oecde.chat.IChatDao;
import org.sgu.oecde.core.users.Admin;
import org.sgu.oecde.core.users.Teacher;
import org.sgu.oecde.core.users.UserType;
import org.sgu.oecde.de.users.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Malygin
 * получение листа сообщений в чат
 */
@WebServlet(value="/ChatList", loadOnStartup=1)
public class ChatList extends HttpServlet {
    private static final int number=25;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            IChatDao myDao = (IChatDao) context.getBean("chatDao");

            List<ChatMessage> list = myDao.getChatList(1L, number);
            StringBuffer str=new StringBuffer();
            str.append("{\"Super\": [");
                    for(ChatMessage l:list ){
                              str.append("{\"fio\": \"");
                              switch(UserType.toType(l.getAuthor())){
                                  case ADMIN :
                                      Admin admin =(Admin)l.getAuthor();
                                              str.append(admin.getSurname()+" "+admin.getName()).
                                              append("\", \"date\": \"").append(l.getDateMessage().substring(11)).
                                              append("\", \"type\": \"").append("linkToAdminsProfile");
                                      break;
                                  case STUDENT :
                                     Student student=(Student)l.getAuthor();
                                              str.append(student.getSurname()+" "+student.getName()).
                                              append("\", \"date\": \"").append(l.getDateMessage().substring(11)).
                                              append("\", \"type\": \"").append("linkToStudentsProfile");;
                                      break;
                                   case TEACHER :
                                     Teacher teacher=(Teacher)l.getAuthor();
                                              str.append(teacher.getSurname()+" "+teacher.getName()).
                                              append("\", \"date\": \"").append(l.getDateMessage().substring(11)).
                                              append("\", \"type\": \"").append("linkToTeachersProfile");

                                      break;
                              }
                              str.append("\", \"message\": \"").append(l.getMessage()).append("\"},");
                        }
             str.deleteCharAt(str.length()-1);
             str.append(" ]}");
             out.println(str);

        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>




}