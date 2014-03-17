package fh.ostfalia.projekt2014.loginservice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/** User
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 4.4.2.1
 * Aufruf von: UserDao->User
 * Entität, die User persistiert
 */
@Entity
@Table
@NamedQueries({@NamedQuery(name="User.getAll",query="SELECT e FROM User e")})
public class User implements Serializable{
    
     /**
      * Benutzer werden auf der Seite Webserver->WebPages->LoginPages->registrierung.xhtml angelegt
     * Konstruktor zur Erstellung eines Benutzers
     * @param username Benutzername des Users
     * @param password Passwort des Users
     * @param role Rolle des Users -> mögliche Rollen sind "user" oder "admin"
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * leerer Standard-Konstruktor (für Entity benötigt)
     */
    public User(){}
    
    //ID des Users
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (name = "userid", unique=true)
    private int userId;
    
    //Name des Users
    @Column (name = "username", unique=true)
    private String username;
    
    //Passwort des Users
    @Column (name = "password")
    private String password;
    
    //Rolle des Users
    @Column (name = "role")
    private String role;

    /**
     *
     * @return gibt UserID zurück
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId setzt UserID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return gibt Username zurück
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username setzt Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return holt Passwort
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password setzt Passwort
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return holt die Rolle des Benutzers
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role setzt die Rolle des Benutzers
     */
    public void setRole(String role) {
        this.role = role;
    }

   
}
