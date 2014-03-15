/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loginservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.loginserviceremoteinterfaces.interfaces.Login;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yannick
 */
public class LoginMBean extends RemoteManagedBean {

    private Login loginBean;
    private String username;
    private String password;

    public LoginMBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Loginservice/LoginBean");
    }

    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        loginBean = (Login) super.getObject();
    }

    public String login() {

        System.out.println("Ich wurde aufgerufen! :D");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request;
        
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
       
        try {
            System.out.println("Login wird gestartet ... ");
            request.login(username, password);
            Principal principal = request.getUserPrincipal();
            if (request.isUserInRole("admin")) {
                String msg = "User: " + principal.getName() + ", Role: admin";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "/AdminPages/index";
            } else if (request.isUserInRole("user")) {
                String msg = "User: " + principal.getName() + ", Role: user";
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
                return "/LoginPages/MusicservicePages/index";
            }
            return "du_musst_die_rollen_noch_definieren";
        } catch (ServletException e) {

            fc.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
    }
    
    public String getStatus(){
        FacesContext fc = FacesContext.getCurrentInstance();
        //Hole aktuelle Session, erzeuge aber keine neue Session, falls keine existiert (-> daher getSession(false)
        Object session = fc.getExternalContext().getSession(false);
        System.out.println(session.toString());
        if(session==null){
            return "login";
        }
        else{
           return "MusicservicePages/index"; 
        }
    }

    public String logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        //Hole aktuelle Session, erzeuge aber keine neue Session, falls keine existiert (-> daher getSession(false)
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        HttpServletRequest request;
        request = (HttpServletRequest) fc.getExternalContext().getRequest();
        
        if (session != null) {
            try {
                
                session.invalidate();
                request.logout();
                
            } catch (ServletException ex) {
                Logger.getLogger(LoginMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
      
        fc.addMessage(username, new FacesMessage(username + " wurde erfolgreich abgemeldet."));
        return "/LoginPages/login.xhtml";

    }

    public String addUser() {
        System.out.println("Username: " + username);
        System.out.println("Passwort: " + password);
        return loginBean.addUser(username, password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
