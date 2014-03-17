/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
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
    
    /**
     * Boolean zur Bestimmung des angesprochenen Server
     * true = Methoden werden zu Musicservice 1 umgeleitet
     * false = Methoden werden zu Musicservice 2 umgeleitet
     */
    private boolean switchServer = true;
    
    /*
    * maxRequests wird hier per Zufallsgenerator erzeugt
    * maxRequests kann nach diesem Generator zwischen 1 und 20 liegen
    * Wenn maxRequests erreicht ist, wird die nächste Anfrage auf den anderen Server weitergeletitet
    * Dies wird durch die Methode assignMusicservice() geschaltet.
    */
    private int maxRequests = (int) ((Math.random() * 20) + 1);
    private int requests;

    /**
     * Algorythmus zum zuweisen des Musicdienstes
     */
    private void assignMusicservice() {
        System.out.println("aktueller Request: " + requests);
        System.out.println("Zufallszahl: " + maxRequests);
        if (requests > maxRequests) {
              /**
             * Neue Zufallsn ummer generieren
             */
            maxRequests = (int) ((Math.random() * 20) + 1);
            requests = 1;
             /**
             * Prüfen ob schon ein lookup gemacht wurde Wenn nicht, dann lookup
             * ansonsten tue nichts
             */
            if (switchServer == true) {
                //targetService = m1;
                switchServer = false;
            } else {
                //targetService = m2;
                switchServer = true;
            }
        }else{
           requests++; 
        }
         
}


    public List<Mp3> getAllMp3s() {
        assignMusicservice();
        if(switchServer==true){
            return m1.getAllMp3s();
        }
        else{
            return m2.getAllMp3s();
        }
        
    }


    public Mp3 getMp3(int mp3Id) {
        assignMusicservice();
        if(switchServer==true){
            return m1.getMp3(mp3Id);
        }
        else{
            return m2.getMp3(mp3Id);
        }
    }

  
    public byte[] getMp3File(int mp3Id) {
        assignMusicservice();
        if(switchServer==true){
            return m1.getMp3File(mp3Id);
        }
        else{
            return m2.getMp3File(mp3Id);
        }
    }


    @Override
    public String getIdParameter() {
          assignMusicservice();
        if(switchServer==true){
            return m1.getIdParameter();
        }
        else{
            return m2.getIdParameter();
        }
    }


    public void upload(String path) {
        assignMusicservice();
        if(switchServer==true){
            System.out.println("UPLOAD in LOADBALNCERBEAN!!!!");
            m1.upload(path);
            
        }
        else{
             System.out.println("UPLOAD in LOADBALNCERBEAN!!!!");
            m2.upload(path);
        }
    }


    public void downloadMp3File(String filename, int mp3Id) {
        assignMusicservice();
        if(switchServer==true){
            m1.downloadMp3File(filename, mp3Id);
        }
        else{
            m2.downloadMp3File(filename, mp3Id);
        }
    }

    
    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
       assignMusicservice();
        if(switchServer==true){
           return m1.getAllArtistCommentsById(id);
        }
        else{
            return m2.getAllArtistCommentsById(id);
        }
    }

   
    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        assignMusicservice();
        if(switchServer==true){
           return m1.getAllMp3CommentsById(id);
        }
        else{
            return m1.getAllMp3CommentsById(id);
        }
    }

  
    public void addComment(String text, long id, String identfier) {
          assignMusicservice();
        if(switchServer==true){
           m1.addComment(text, id, identfier);
        }
        else{
            m1.addComment(text, id, identfier);
        }
    }
    
    @Override
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId){
         assignMusicservice();
        if(switchServer==true){
            return m1.getMp3ArtistByArtistId(mp3ArtistId);
        }
        else{
            return m2.getMp3ArtistByArtistId(mp3ArtistId);
        }
    }
    
    @Override
    public List<Mp3> getMp3ByArtist(int mp3ArtistId){
         assignMusicservice();
        if(switchServer==true){
            return m1.getMp3ByArtist(mp3ArtistId);
        }
        else{
            return m2.getMp3ByArtist(mp3ArtistId);
        }
    }
}
