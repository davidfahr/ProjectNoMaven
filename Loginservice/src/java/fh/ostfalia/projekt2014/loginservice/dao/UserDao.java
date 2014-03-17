/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loginservice.dao;

import fh.ostfalia.projekt2014.loginservice.entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;



@Stateless
public class UserDao implements Serializable {
    @PersistenceContext(unitName="LoginservicePU")
 
    private EntityManager em;
  //  @Resource
    //UserTransaction ut;
    
    public void addUser(User user) throws PersistenceException {
         System.out.println("-----> Wir sind in Adduser im UserDAO!");
        
          
             em.persist(user);

       
    }

    public void editUser(User user) {
        em.merge(user);
    }


    public void deleteUser(int userId) {
        em.remove(getUser(userId));
    }


    public User getUser(int userId) {
        return em.find(User.class, userId);
    }


    public List<User> getAllUsers() {
        return em.createNamedQuery("User.getAll").getResultList();
    }

}
