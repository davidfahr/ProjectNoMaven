/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.musicservice.dao;


import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.musicservice2remoteinterface.interfaces.Musicservice;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author Yannick
 */
@Stateless
public class Mp3DBSyncBean extends RemoteBean{
    private Musicservice musicservice;
    
    public Mp3DBSyncBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Musicservice2/Musicservice2Bean");
        //Holen der entfernten Bean bzw. deren Stub-Objekt   
    }

    @PostConstruct
    public void initBean() {
        musicservice = (Musicservice) super.getObject();
    }
    
    public void update(Mp3 mp3Bean){
        musicservice.update(mp3Bean);
    }
    
    
    
}
