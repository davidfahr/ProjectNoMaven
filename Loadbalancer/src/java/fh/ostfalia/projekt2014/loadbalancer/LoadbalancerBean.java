/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.loadbalancer.remote.IMusicserviceRemote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mettbroetchen
 */
@Stateless
public class LoadbalancerBean implements Serializable, Loadbalancer {

    private static final long serialVersionUID = 1L;
    /*
     * Hier wird Musiservice1Remote geladen
     */
    @EJB 
    private Musicservice1Remote m1;
    @EJB 
    private Musicservice2Remote m2;

    private IMusicserviceRemote targetService = m1;

    private boolean status;
    
    /**
     * Boolean zur Bestimmung des angesprochenen Server
     * true = Methoden werden zu Musicservice 1 umgeleitet
     * false = Methoden werden zu Musicservice 2 umgeleitet
     */
    private boolean switchServer = false;

    private int randNumber = (int) ((Math.random() * 20) + 1);
    private int requests;

    /**
     * Algorythmus zum zuweisen des Musicdienstes
     */
    private void assignMusicservice() {
        if (requests > randNumber) {
              /**
             * Neue Zufallsn ummer generieren
             */
            randNumber = (int) ((Math.random() * 20) + 1);
            
            /**
             * Prüfen ob schon ein lookup gemacht wurde Wenn nicht, dann lookup
             * ansonsten tue nichts
             */
            if (switchServer == true) {
                targetService = m1;
                switchServer = false;
            } else {
                targetService = m2;
                switchServer = true;
            }
        } 
        else {      
            requests++;
        /**
         * Neuer Lookup
         */

    }
}

    public void whoAmI() {
        assignMusicservice();
        if(status ==true){
            m1.whoAmI();
            status = false;
        }
        else{
            m2.whoAmI();
            status = true;
        }  
    }


    public List<Mp3> getAllMp3s() {
        assignMusicservice();
        return null;
    }


    public Mp3 getMp3(int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public byte[] getMp3File(int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public String getIdParameter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void upload(String part) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void downloadMp3File(String filename, int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment> getAllArtistCommentsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public List<fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment> getAllMp3CommentsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public void addComment(String text, long id, String identfier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
