/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.musicinterfaces.entities.Mp3;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.musicinterfaces.remote.Musicservice;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Mettbroetchen
 */
public class MusicserviceRemote implements Musicservice, Serializable{
    private static final long serialVersionUID = 1L;
    @EJB
    private MusicserviceLocal m1;


    private Musicservice targetMusicservice;
    private int randNumber = (int) ((Math.random() * 20) + 1);
    private int requests;
    
    /**
     * Algorythmus zum zuweisen des Musicdienstes
     */
    private void assignMusicservice(){
        if(requests < randNumber){
            /**
             * Prüfen ob schon ein lookup gemacht wurde
             * Wenn nicht, dann lookup
             * ansonsten tue nichts
             */
            if(targetMusicservice == null){
                /**
                 * Lookup
                 */
                targetMusicservice = (Musicservice)m1;
            }
        } else {
            /**
             * Neue Zufallsn ummer generieren
             */
            randNumber = (int) ((Math.random() * 20) + 1);
            /**
             * Neuer Lookup
             */
            
        }
    }
    
    @Override
    public void whoAmI() {
        assignMusicservice();
        targetMusicservice.whoAmI();
        
    }

    @Override
    public List<Mp3> getAllMp3s() {
        return targetMusicservice.getAllMp3s();
    }

    @Override
    public Mp3 getMp3(int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getMp3File(int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdParameter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void upload(String part) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void downloadMp3File(String filename, int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment> getAllArtistCommentsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment> getAllMp3CommentsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addComment(String text, long id, String identfier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
