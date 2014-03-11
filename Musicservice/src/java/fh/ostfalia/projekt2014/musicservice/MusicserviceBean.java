/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice;


import fh.ostfalia.projekt2014.musicservice.dao.Mp3DaoLocal;
import fh.ostfalia.projekt2014.musicservice.entities.Mp3ArtistBean;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.entities.Mp3;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Mettbrötchen
 */
@Stateless
public class MusicserviceBean implements Musicservice, Serializable {
    
    private static final long serialVersionUID = 1L;
    @EJB
    private Mp3DaoLocal mp3Dao;
    
    //@EJB
    //private Mp3ArtistDaoLocal mp3ArtistDao;
    //@EJB
    //private Mp3DaoImpl mp3DaoImpl;

    private List<Mp3ArtistBean> lArtists;

    @Override
    public Mp3 getMp3(int mp3_id) {
        return mp3Dao.getMp3(mp3_id);
    }

    @Override
    public List<Mp3> getAllMp3s() {
        return mp3Dao.getAllMp3();
    }

    @Override
    public byte[] getMp3File(int mp3Id) {
        return mp3Dao.getMp3File(mp3Id);
    }

    @Override
    public void upload(String part) {

        mp3Dao.upload(part);

    }

    @Override
    public String getIdParameter() {
        return mp3Dao.getIdParameter();

    }
    
    @Override
    public void downloadMp3File(String filename, int mp3Id){
        mp3Dao.downloadMp3File(filename, mp3Id);
    }
    @Override
    public void whoAmI(){
        System.out.println("Ich bin Musicservice 1");
    }
    /*
     @Override
     public String getMp3ArtistNameByArtistBean(Mp3Artist mp3Artist) {
     return null;
     }
     */
}
