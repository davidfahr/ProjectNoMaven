/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice2.entities;

import fh.ostfalia.projekt2014.musicservice2remoteinterface.entities.Mp3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;

/**
 *
 * @author Mettbrötchen
 */

@Entity(name = "Mp3")
@NamedQueries({
    @NamedQuery(name = "getMp3ByName", query = "SELECT m FROM Mp3 m where m.mp3Title=:name")
})
public class Mp3Bean implements Serializable, Mp3{
    
  private static final long serialVersionUID = 1L;
  
    
    private Mp3ArtistBean mp3Artist;
    private int mp3Id;
    private int artist_id;
    private byte[] mp3File;
    private String mp3Title;

    public Mp3Bean() {
    }

    public Mp3Bean(Mp3ArtistBean mp3Artist, byte[] mp3File, String mp3Title) {
        this.mp3Artist = mp3Artist;
        this.mp3File = mp3File;
        this.mp3Title = mp3Title;
    }

    @Id
    @Column(name = "mp3_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Override
    public int getMp3Id() {
        return mp3Id;
    }

    public void setMp3Id(int mp3Id) {
        this.mp3Id = mp3Id;
    }
    

    @Column(name = "mp3_file")
    @Lob
    public byte[] getMp3File() {
        return mp3File;
    }

    public void setMp3File(byte[] mp3_file) {
        this.mp3File = mp3_file;
    }

    @Column(name = "mp3_title")
    @Override
    public String getMp3Title() {
        return mp3Title;
    }

    
    public void setMp3Title(String mp3_title) {
        this.mp3Title = mp3_title;

    }
    
    @ManyToOne(cascade=CascadeType.ALL ,targetEntity=Mp3ArtistBean.class)
    @JoinColumn(name = "artist_id", nullable = true)
    public Mp3ArtistBean getMp3ArtistBean() {
        return this.mp3Artist;
    }
    
    public void setMp3ArtistBean(Mp3ArtistBean mp3Artist) {
        this.mp3Artist = mp3Artist;
    }
    
    @Override
    public int getArtistId(){
        return mp3Artist.getArtistId();
    }

    public void setMp3ByteCodeFromFile(File file) {

        try {

            mp3File = new byte[(int) file.length()];

            FileInputStream inputStream = new FileInputStream(file);
            try {
                inputStream.read(mp3File);
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Mp3Bean.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mp3Bean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    
    public void editArtistId(int artist_id){
        mp3Artist.setArtistId(artist_id);
    }

    @Override
    public String getArtistName() {
        return mp3Artist.getArtistName();
    }

    


}
