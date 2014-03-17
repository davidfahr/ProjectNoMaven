package fh.ostfalia.projekt2014.musicservice2.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
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

    /**
     *
     */
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
     *
     * @return
     */
    @Id
    @GeneratedValue
    @Column(name = "artist_id", unique = true, nullable = false)
    public int getArtistId() {
        return artistId;
    }

    /**
     *
     * @param artist_id
     */
    public void setArtistId(int artist_id) {
        this.artistId = artist_id;
    }

    /**
     *
     * @return
     */
    @Column(name = "artist_name")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artist_name) {
        this.artistName = artist_name;
    }

}
