/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer.remote;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author KingDCB
 */
@Stateless
public class Musicservice1Remote extends RemoteBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private Musicservice musicservice1Bean;

    public Musicservice1Remote() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Musicservice/MusicserviceBean");
        System.out.println("ENDELookup --> Musikdienst 1");
    }

    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        musicservice1Bean = (Musicservice) super.getObject();
    }
    
    
    public Musicservice getMusicservice1Bean() {
        return musicservice1Bean;
    }

    public void setMusicservice1Bean(Musicservice musicservice1Bean) {
        this.musicservice1Bean = musicservice1Bean;
    }

    public void whoAmI() {
        musicservice1Bean.whoAmI();
    }

  
    public List<Mp3> getAllMp3s() {
        return musicservice1Bean.getAllMp3s();
    }

   
    public Mp3 getMp3(int mp3Id) {
        return musicservice1Bean.getMp3(mp3Id);
    }

   
    public byte[] getMp3File(int mp3Id) {
        return musicservice1Bean.getMp3File(mp3Id);
    }

   
    public String getIdParameter() {
        return musicservice1Bean.getIdParameter();
    }

   
    public void upload(String part) {
        System.out.println("UPLOAD IN MUSICSERVICE1REMOTE");
        musicservice1Bean.upload(part);
    }


    public void downloadMp3File(String filename, int mp3Id) {
        musicservice1Bean.downloadMp3File(filename, mp3Id);
    }


    public List<Comment> getAllArtistCommentsById(int id) {
       return musicservice1Bean.getAllArtistCommentsById(id);
    }


    public List<Comment> getAllMp3CommentsById(int id) {
        return musicservice1Bean.getAllMp3CommentsById(id);
    }

 
    public void addComment(String text, long id, String identfier) {
        musicservice1Bean.addComment(text, id, identfier);
    }


}
