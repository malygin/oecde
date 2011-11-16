/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.core.users.AbstractUser;
import org.sgu.oecde.core.users.StudentGroup;
import org.sgu.oecde.core.util.SemesterGetter;
import org.sgu.oecde.de.users.Student;
import org.sgu.oecde.de.users.Group;
import org.sgu.oecde.schedule.Lesson;
import org.sgu.oecde.schedule.dao.LessonDao;
import org.sgu.oecde.web.jsfbeans.admin.ConstantsFormBean;
import org.sgu.oecde.core.util.NumberUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author malygin
 */
@WebServlet(name = "SheduleServlet", urlPatterns = {"/SheduleServlet"})
public class SheduleServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
               StringBuffer str=new StringBuffer();
               String month = request.getParameter("month");
               String monthEnd="";
               String year = request.getParameter("year");
               int monthNumber = Integer.parseInt(month);
              // int yearNumber = Integer.parseInt(year);
               
//               if (monthNumber<10) month="0"+month;
//               if (++monthNumber<10)  monthEnd="0"+monthNumber;
//                   else monthEnd=monthNumber+"";
               
               AbstractUser user = (AbstractUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
               ServletContext context = getServletContext();
               WebApplicationContext applicationContext =WebApplicationContextUtils.getWebApplicationContext(context);
               LessonDao dao = (LessonDao) applicationContext.getBean("lessonDao");
               SemesterGetter calendareConstant = (SemesterGetter) applicationContext.getBean("semesterGetter");
               List<Lesson> list=dao.getLessonsForStudent(calendareConstant.getCurrentSemester()==1,(Group)((Student)user).getGroup(),((Student)user).getCity(),40,1, year+"."+NumberUtil.NumberToDateFormat(monthNumber)+".01",year+"."+NumberUtil.NumberToDateFormat(++monthNumber)+".01");
            //  List<Lesson> list=dao.getLessonsForStudent(false,(org.sgu.oecde.de.users.Group)((Student)user).getGroup(),((Student)user).getCity(),40,1,year+".0"+month+".01", year+".0"+ ++monthNumber+".01");

               if (!list.isEmpty()){
                     str.append("{\"Super\": [");
                   for(Lesson l:list){
                   
                              str.append("{\"teacherFIO\": \"").append(l.getTeacher().getInitials()).
                              append("\", \"discipline\": \"").append(l.getDiscipline().getName()).
                              append("\", \"day\": \"").append(Integer.parseInt(l.getLessonDate().substring(8, 10))).
                              append("\", \"time\": \"").append(l.getLessonDate().substring(11, 16)).
                              append("\", \"room\": \"").append(l.getRoom()/13 +1).append("\"},");
                   }
                     str.append(" ]}");
               }
               out.print(str);
             
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
