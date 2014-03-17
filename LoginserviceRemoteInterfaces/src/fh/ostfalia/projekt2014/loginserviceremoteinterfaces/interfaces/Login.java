package fh.ostfalia.projekt2014.loginserviceremoteinterfaces.interfaces;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

/** Login
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 4.2
 * Aufruf von: Webserver->LoginMBean
 * Interfaces des Loginservices
 * Deklariert Methodenvorgaben für den Logindienst
 * Gibt den Logindienst per @Remote-Annotation nach außen frei und erzeugt JNDI-Referenz
 */
@Remote
public interface Login {

    /**
     * nähere Beschreibung siehe Loginservice->Dao-Paket->UserDao und 4.4.2.1
     * @param username
     * @param password
     * @return
     */
    public String addUser(String username, String password) throws PersistenceException;
      
}

