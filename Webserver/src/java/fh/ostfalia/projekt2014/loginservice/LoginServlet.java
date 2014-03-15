/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loginservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yannick
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Herzlich Willkommen im Musikdienst!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LoginServlet");
        FacesContext fc = FacesContext.getCurrentInstance();

        //request = (HttpServletRequest) fc.getExternalContext().getRequest();

        System.out.println("Login wird gestartet ... ");

        //Principal principal = request.getUserPrincipal();
        if (request.isUserInRole("admin")) {
            
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rolle: Admin", null));
            response.sendRedirect("faces/AdminPages/index.xhtml");

        } else if (request.isUserInRole("user")) {
            //String msg = "User: " + principal.getName() + ", Role: user";
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rolle: User", null));
            response.sendRedirect("faces/LoginPages/MusicservicePages/index.xhtml");

        } else {
            fc.addMessage(null, new FacesMessage("Noch nicht angemeldet."));
            response.sendRedirect("faces/LoginPages/login.xhtml");
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
