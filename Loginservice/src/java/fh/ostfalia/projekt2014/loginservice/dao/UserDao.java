package fh.ostfalia.projekt2014.loginservice.dao;

import fh.ostfalia.projekt2014.loginservice.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/** UserDao
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 4.4.2
 * Aufruf von: Loginservice->loginservice->LoginBean
 */
@Stateless
public class UserDao implements Serializable {
    /*
     * eigene PersistenceUnit für den Login.
     * UnitName muss zur Unterscheidung der verschiedenen
     * PersistenceUnits pro Tabelle definiert werden.
     * Tabelle muss in DB erstellt werden; Ressourcen & Connectionpool sollte automatisch erstellt werden
    */
    @PersistenceContext(unitName="LoginservicePU")
    private EntityManager em;

    
    /**
     * Persistiert den User in der Datenbank
     * @param user ein User-Objekt aus Loginservice->entities->User wird übergeben
     */
    public void addUser(User user) throws PersistenceException {
      
          //Persistierung eines neuen Benutzers
          em.persist(user);

    }

    /**
     * wird nicht genutzt, aufgrund DAO-Pattern im Sinne der Erweiterbarkeit schon vorgefertigt
     * kann später benutzt werden
     * @param user ein User-Objekt aus Loginservice->entities->User wird übergeben
     */
    public void editUser(User user) {
        em.merge(user);
    }

    /**
     * wird nicht genutzt, aufgrund DAO-Pattern im Sinne der Erweiterbarkeit schon vorgefertigt
     * kann später benutzt werden
     * @param userId
     */
    public void deleteUser(int userId) {
        em.remove(getUser(userId));
    }

    /**
     * wird nicht genutzt, aufgrund DAO-Pattern im Sinne der Erweiterbarkeit schon vorgefertigt
     * kann später benutzt werden
     * @param userId
     * @return
     */
    public User getUser(int userId) {
        return em.find(User.class, userId);
    }

    /**
     * wird nicht genutzt, aufgrund DAO-Pattern im Sinne der Erweiterbarkeit schon vorgefertigt
     * kann später benutzt werden
     * @return
     */
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.getAll").getResultList();
    }

}
