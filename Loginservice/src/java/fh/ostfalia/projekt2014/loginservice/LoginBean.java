package fh.ostfalia.projekt2014.loginservice;

import fh.ostfalia.projekt2014.loginservice.dao.UserDao;
import fh.ostfalia.projekt2014.loginservice.entities.User;
import java.io.Serializable;
import fh.ostfalia.projekt2014.loginserviceremoteinterfaces.interfaces.Login;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.PersistenceException;



/** LoginBean
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 4.4.1
 * Aufruf von: Webserver->loginservice->LoginMBean
 */
@Stateful
public class LoginBean implements Serializable, Login {

    /*
    * Das UserDAO wird hier per DI geholt
    */
    @EJB
    private UserDao userdao;
    /**
     * Wird von der LoginMBean des Webservers aufgerufen
     * Legt einen Benutzer in der Datenbank an
     * @param username Nutzername des Users
     * @param password Passwort des Users 
     * @return index.xhtml, damit auf die Startseite zurückgesprungen wird
     */
    @Override
    public String addUser(String username, String password) throws PersistenceException {
        
        //Erstellen des Users
        User user = new User(username, password,"user");
       
        //Hinzufügen zur Datenbank (siehe dao.UserDao)
        userdao.addUser(user);
        
        //Rückgabe der nächsten Seite
        return "index.xhtml";
    }

}
