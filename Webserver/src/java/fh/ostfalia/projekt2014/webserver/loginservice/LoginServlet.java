package fh.ostfalia.projekt2014.webserver.loginservice;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** LoginServlet
 *  Ist die Startseite des Projektes (welcomefile in der web.xml)
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 
 * Abhängig vom Loginstatus (hat sich ein Nutzer bereits eingeloggt) wird hier auf die korrekte Startseite weitergeleitet.
 * Aufruf von:
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

      
        if (request.isUserInRole("admin")) {
            //Als Admin starten
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rolle: Admin", null));
            response.sendRedirect("faces/LoginPages/MusicservicePages/index.xhtml");
            

        } else if (request.isUserInRole("user")) {
            //Als User starten
             fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rolle: User", null));
            response.sendRedirect("faces/LoginPages/MusicservicePages/index.xhtml");
           

        } else {
            // Man ist noch nicht angemeldet
            fc.addMessage(null, new FacesMessage("Noch nicht angemeldet."));
            response.sendRedirect("faces/LoginPages/login.xhtml");
            
        }
        
        
    }
}
