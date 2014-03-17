package fh.ostfalia.projekt2014.musicservice.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Mp3ArtistBean ist eine Entity bildet die Tabelle Mp3Artist der Datenbank.
 * @author M.Tönjes, D.Fahr, Y.Weißflog Aufruf von:
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


    public Mp3ArtistBean() {
    }

    /**
     * 
     * @param artistName
     */
    public Mp3ArtistBean(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Primärschlüssel
     * @return int id
     */
    @Id
    @GeneratedValue
    @Column(name = "artist_id", unique = true, nullable = false)
    public int getArtistId() {
        return artistId;
    }

    /**
     * Setzt eine Artist Id
     * @param artist_id
     */
    public void setArtistId(int artist_id) {
        this.artistId = artist_id;
    }

    /**
     * Artistname 
     * @return String artistname
     */
    @Column(name = "artist_name")
    public String getArtistName() {
        return artistName;
    }

    /**
     * Setzet einen Artistnamen
     * @param artist_name Sting
     */
    public void setArtistName(String artist_name) {
        this.artistName = artist_name;
    }
}
