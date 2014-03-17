package fh.ostfalia.projekt2014.webserver.loginservice;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** LoginServlet
 * 
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 
 * 
 * Aufruf von:
 * Beispiele: 
 */
public class LoginServlet extends HttpServlet {

 
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
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
            
            response.sendRedirect("faces/LoginPages/MusicservicePages/index.xhtml");
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rolle: Admin", null));

        } else if (request.isUserInRole("user")) {
            //String msg = "User: " + principal.getName() + ", Role: user";
            response.sendRedirect("faces/LoginPages/MusicservicePages/index.xhtml");
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rolle: User", null));

        } else {
            
            response.sendRedirect("faces/LoginPages/login.xhtml");
            fc.addMessage(null, new FacesMessage("Noch nicht angemeldet."));
        }
        
        
    }
}
