package org.sgu.oecde.web;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import org.sgu.oecde.web.TaskServlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sgu.oecde.web.jsfbeans.util.fileUpload.FileUploadUtil;
import org.springframework.util.ObjectUtils;

/**
 *
 * @author Malygin
 * сервлет, который подгружает в фрейм хтмлку тест
 *
 */
@WebServlet(value="/TestServlet")
public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
          if (request.getParameter("task").indexOf("$$")!=-1){
              String s=request.getParameter("task");
              while (s.indexOf("$")!=-1){
                 s= s.replaceFirst("\\$+"," <img src='http://oec.sgu.ru/latex/latex.php?code=");
                 s= s.replaceFirst("\\$+"," '/> ");
              }
                  out.print("<meta http-equiv='Content-Type' content='text/htm; charset=utf-8'>"+s);
              return;
          }
          String str="";
          String[] urlTask=request.getParameter("task").split("/");
          URL url = new URL(TaskServlet.urlServer+request.getParameter("task"));
          StringBuilder strbuf = new StringBuilder();
          BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
          while ((str = in.readLine()) != null) {strbuf.append(str);}
          //в некоторых тестах пути нестандартные - сразу с тектбукс
          if (strbuf.lastIndexOf("textbooks")==-1){
             str=strbuf.toString().replaceAll("src='", "src='"+TaskServlet.urlServer+"/"+urlTask[0]+"/"+urlTask[1]+"/");
             str=str.replaceAll("src=\"", "src=\""+TaskServlet.urlServer+urlTask[0]+"/"+urlTask[1]+"/"+urlTask[2]+"/");
           }else{
             str=strbuf.toString().replaceAll("src=\"../textbooks/", "src=\""+TaskServlet.urlServer);;
          }            
          out.print(str);}

  

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
