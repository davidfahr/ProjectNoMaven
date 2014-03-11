/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice.remote;

import fh.ostfalia.projekt2014.musicserviceremoteinterface.entities.Mp3;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author Yannick
 */
@Stateless
public class Mp3BeanRemote implements Mp3,Serializable{
    private static final long serialVersionUID = 1L;
    private String mp3Id;
    private String mp3Name;
  
    
    @Override
    public int getArtistId() {
      return 0;
    }

    @Override
    public String getArtistName() {
        return null;
    }

    @Override
    public int getMp3Id() {
return 0;
    }

    @Override
    public String getMp3Title() {
return null;
    }
 
    

}
