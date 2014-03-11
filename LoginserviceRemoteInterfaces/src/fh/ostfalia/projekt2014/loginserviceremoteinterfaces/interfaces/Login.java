/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loginserviceremoteinterfaces.interfaces;

import javax.ejb.Remote;


@Remote
public interface Login {

    public String addUser(String username, String password);
      
}

