/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sgu.oecde.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.web.jsfbeans.util.ArrayListUtil;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FileUploadUtil;


/**
 *
 * @author Malygin
 * сервлет, который подгружает в фрейм занятие от умк, пергружает пути к картинкам и добавляет стили
 */
@WebServlet(value="/TaskServlet")
public class TaskServlet extends HttpServlet {
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
      try{
          
          String str="";
          String[] urlTask=request.getParameter("task").split("/");
          URL url = new URL(getServletContext().getInitParameter("textbookUrl") +request.getParameter("task"));
          StringBuilder strbuf = new StringBuilder();
          BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
          String mime =  this.getServletContext().getMimeType(url.toString());
         // System.out.println("begin! "+mime);
          if(!ArrayListUtil.containsElement(FileUploadUtil.mimetypes, mime)){
              while ((str = in.readLine()) != null) {strbuf.append(" ").append(str);}
           //   System.out.println(strbuf);
              str=strbuf.toString().replaceAll("src='", "src='"+getServletContext().getInitParameter("textbookUrl")+"/"+urlTask[0]+"/"+((urlTask.length>2)?urlTask[1]:"")+"/");
              str=str.replaceAll("src=\"", "src=\""+getServletContext().getInitParameter("textbookUrl")+"/"+urlTask[0]+"/"+((urlTask.length>2)?urlTask[1]:"")+"/");
            //  System.out.println(str);
              out.print("<link href=\"resources/css/default.css\" rel=\"stylesheet\" type=\"text/css\" /> "+str);}
          else out.print("Вы можете скачать этот файл! <br/>"
              + "<a href=\""+url+"\"> скачать файл</a>"); 
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
