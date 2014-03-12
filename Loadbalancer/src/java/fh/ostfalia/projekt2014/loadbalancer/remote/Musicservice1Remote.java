/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer.remote;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.entities.Mp3;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author KingDCB
 */
public class Musicservice1Remote extends RemoteBean implements Musicservice, Serializable{
    private static final long serialVersionUID = 1L;
    private Musicservice musicservice1Bean;

    public Musicservice1Remote() {
        super("localhost", "3700", "java:global/Loadbalancer/MusicserviceBean");
        System.out.println("ENDELookup");
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

    @Override
    public void whoAmI() {
        musicservice1Bean.whoAmI();
    }

    @Override
    public List<Mp3> getAllMp3s() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<Comment> getAllArtistCommentsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addComment(String text, long id, String identfier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
