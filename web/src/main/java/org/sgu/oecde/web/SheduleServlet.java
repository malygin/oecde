/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
               str.append("{\"Super\": [").
                   append("{\"teacherFIO\": \"").append("Амелин Р.В.").
                   append("\", \"discipline\": \"").append("Политология").
                   append("\", \"day\": \"").append("4").
                   append("\", \"time\": \"").append("14:30").
                   append("\", \"room\": \"").append("4").append("\"},").
                       
                   append("{\"teacherFIO\": \"").append("Петров Р.В.").
                   append("\", \"discipline\": \"").append("Социология").
                   append("\", \"day\": \"").append("4").
                   append("\", \"time\": \"").append("17:30").
                   append("\", \"room\": \"").append("4").append("\"},").
                   
                   append("{\"teacherFIO\": \"").append("Амелин Р.В.").
                   append("\", \"discipline\": \"").append("Политология").
                   append("\", \"day\": \"").append("14").
                   append("\", \"time\": \"").append("14:30").
                   append("\", \"room\": \"").append("2").append("\"}").
                   
                   append("{\"teacherFIO\": \"").append("Иванов В.А.").
                   append("\", \"discipline\": \"").append("Политология").
                   append("\", \"day\": \"").append("22").
                   append("\", \"time\": \"").append("14:30").
                   append("\", \"room\": \"").append("2").append("\"}").
                       
                       
                       append(" ]}");
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
