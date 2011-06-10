/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgu.oecde.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author ShihovMY
 */
@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = {"/Admin/UploadServlet", "/Teacher/UploadServlet", "/Student/UploadServlet", "/Supervisor/UploadServlet"})
public class UploadServlet extends HttpServlet {

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        for (Part p : request.getParts()) {
            String pageName = null;
            if ("avatar".equals(p.getName())) {
                pageName = "settings";
            } else if ("CwFile".equals(p.getName())){
                pageName = "controlWorks";
            } else if ("file".equals(p.getName())){
                pageName = "pageEdit";
            } else if ("MessageFile".equals(p.getName())){
                pageName = "messages_write";
            }
            request.setAttribute(p.getName(), p);
            request.getRequestDispatcher(pageName+".xhtml").forward(request, response);
        }
    }
}
