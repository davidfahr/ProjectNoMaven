/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.webserver.loginservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.loginserviceremoteinterfaces.interfaces.Login;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * LoginMBean
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog Siehe Abschnitt
 *
 * erbt von: RemoteBean, damit Lookup durchgeführt werden kann. Aufruf von:
 * Beispiele:
 */
public class LoginMBean extends RemoteBean {

    private Login loginBean;
    private String username;
    private String password;

    /**
     * Konstruktor mit IP, Port und JNDI-Namen Zweck: Festlegung der
     * Konfigurationsparameter des entfernten Objektes
     */
    public LoginMBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Loginservice/LoginBean");
    }

    /**
     * Anforderung und Setzen des entfernten Objektes in eine lokale Variable
     * über die zuvor konfigurierte entfernte Bean (s. Konstruktor)
     */
    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        loginBean = (Login) super.getObject();
    }

    /**
     * Anmelden eines Benutzers
     *
     * @return
     */
    public String login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request;
        request = (HttpServletRequest) fc.getExternalContext().getRequest();

        try {
            //Anmeldung über http-request-Objekt bzw. über web.xml zugeordneten realm
            request.login(username, password);

            Principal principal = request.getUserPrincipal();

            if (request.isUserInRole("admin")) {
                String msg = "User: " + principal.getName() + ", Role: admin";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "/LoginPages/MusicservicePages/index";
            } else if (request.isUserInRole("user")) {
                String msg = "User: " + principal.getName() + ", Role: user";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "/LoginPages/MusicservicePages/index";
            }
            return "falsche Rolle";
        } catch (ServletException e) {

            fc.addMessage(null, new FacesMessage("Login failed."));
            return "/LoginPages/error";
        }
    }

    /**
     * Gibt die Rolle des aktuellen Benutzers zurück
     *
     * @return
     */
    public String getRole() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request;
        //Hole das http-request-Object aus FacesContext
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        String role = "";
        //Ist es ein Admin? (admin)
        if (request.isUserInRole("admin")) {
            role = "admin";
            //Oder ein normaler Nutzer? (user)
        } else if (request.isUserInRole("user")) {
            role = "user";
        }
        //Rückgabe der Rolle
        return role;
    }

    /**
     * Rückgabe des Status
     *
     * @return
     */
    public String getStatus() {
        FacesContext fc = FacesContext.getCurrentInstance();
        //Hole aktuelle Session, erzeuge aber keine neue Session, falls keine existiert (-> daher getSession(false)
        Object session = fc.getExternalContext().getSession(false);
        System.out.println(session.toString());
        if (session == null) {
            return "login";
        } else {
            return "MusicservicePages/index";
        }
    }

    /**
     * Abmelden des aktuellen Nutzers und Auflösen der aktuellen Session um
     * Überreste zu bereinigen
     *
     * @return
     */
    public String logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        //Hole aktuelle Session, erzeuge aber keine neue Session, falls keine existiert (-> daher getSession(false)
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        HttpServletRequest request;
        request = (HttpServletRequest) fc.getExternalContext().getRequest();

        if (session != null) {
            try {
                //abmelden über Realm (in web.xml) definiert
                request.logout();
                //Auflösen der Session
                session.invalidate();

            } catch (ServletException ex) {
                Logger.getLogger(LoginMBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        fc.addMessage(username, new FacesMessage(username + " was logged out!"));
        return "/LoginPages/login.xhtml";

    }

    /**
     * Entferter Aufruf auf die LoginBean im Logindienst zum Anlegen eines neuen
     * Nutzers (als Rolle: "user")
     */
    public void addUser() {

        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            loginBean.addUser(username, password);
            fc.addMessage(username, new FacesMessage(username + " was registered!"));
        } catch (EJBException exc) {
            fc.addMessage(username, new FacesMessage(username + " is already registered. Please select an other username!"));
        }
    }

    /**
     * Holen des Passworts
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setzen des Passworts
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setzen des Usernamens
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Holen des Usernamens
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
}
