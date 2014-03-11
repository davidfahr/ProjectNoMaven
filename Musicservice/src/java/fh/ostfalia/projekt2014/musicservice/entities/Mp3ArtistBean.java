/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice.entities;

import fh.ostfalia.projekt2014.musicservice.entities.Mp3Bean;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Remote;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 *
 * @author David
 */

@Entity(name = "Mp3Artist")
@NamedQueries({
  @NamedQuery(name = "Mp3Artist.getAll", query = "SELECT e FROM Mp3Artist e"),
    @NamedQuery(name = "getMp3ArtistByName", query = "SELECT e FROM Mp3Artist e where e.artistName=:name")
})

public class Mp3ArtistBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int artistId;
    private String artistName;
    private Set<Mp3Bean> mp3Beans = new HashSet();
     
     
    public Mp3ArtistBean(){
    }

    public Mp3ArtistBean(String artistName) {
        this.artistName = artistName;
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artist_id", unique = true, nullable = false)
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artist_id) {
        this.artistId = artist_id;
    }
    
    public void addMp3Bean(Mp3Bean mp3Bean){
        mp3Beans.add(mp3Bean);
    }

    @Column(name = "artist_name")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artist_name) {
        this.artistName = artist_name;
    }
    
    /*
    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "Mp3Bean")
    public Set<Mp3Bean> getMp3Beans() {
        return this.mp3Beans;
    }*/
    

    public void setMp3Beans(Set<Mp3Bean> mp3Beans) {
        this.mp3Beans = mp3Beans;
    }

}
