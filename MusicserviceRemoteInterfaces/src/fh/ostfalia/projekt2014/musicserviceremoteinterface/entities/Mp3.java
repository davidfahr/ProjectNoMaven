/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.musicserviceremoteinterface.entities;

import javax.ejb.Remote;

/**
 *
 * @author Mettbrötchen
 */

public interface Mp3 {
    
    public int getArtistId();
    public String getArtistName();
    public int getMp3Id();
    public String getMp3Title();

}
