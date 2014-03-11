/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.entities.Mp3;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.Part;


/**
 *
 * @author Mettbrötchen
 */
public class MusicserviceMBean extends RemoteManagedBean {
    private String part;
     public MusicserviceMBean() {
        super("localhost", "3700", "java:global/Webserver/MusicserviceBean");
        System.out.println("ENDELookup");
    }
     private Musicservice musicserviceBeanWebserver;
     
    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        musicserviceBeanWebserver = (Musicservice) super.getObject();
    }
    public Musicservice getMusicserviceBean() {
        return musicserviceBeanWebserver;
    }

    public void setMusicserviceBean(Musicservice musicserviceBean) {
        this.musicserviceBeanWebserver = musicserviceBean;
    }
 
    public List<Mp3> getAllMp3s() {
        return musicserviceBeanWebserver.getAllMp3s();
    }


    public Mp3 getMp3(int mp3Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void upload() {
        musicserviceBeanWebserver.upload(part);
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }
    
    

 
}
