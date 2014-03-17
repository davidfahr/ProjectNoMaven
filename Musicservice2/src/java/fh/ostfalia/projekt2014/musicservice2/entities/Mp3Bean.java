package fh.ostfalia.projekt2014.musicservice2.entities;

import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Mp3ArtistBean ist eine Entity bildet die Tabelle Mp3Artist der Datenbank.
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog Aufruf von:
 */
@Entity(name = "Mp3")
@NamedQueries({
    @NamedQuery(name = "getMp3ByName", query = "SELECT m FROM Mp3 m where m.mp3Title=:name"),
    @NamedQuery(name = "getMp3ByMp3ArtistId", query = "SELECT m FROM Mp3 m where m.mp3ArtistBean.artistId=:id")
})
public class Mp3Bean implements Serializable, Mp3 {

    private static final long serialVersionUID = 1L;

    private Mp3ArtistBean mp3Artist;
    private int mp3Id;
    private int artist_id;
    private byte[] mp3File;
    private String mp3Title;

    /**
     *
     */
    public Mp3Bean() {
    }

    /**
     *
     * @param mp3Artist Mp3ArtistBean
     * @param mp3File byte[]
     * @param mp3Title String
     */
    public Mp3Bean(Mp3ArtistBean mp3Artist, byte[] mp3File, String mp3Title) {
        this.mp3Artist = mp3Artist;
        this.mp3File = mp3File;
        this.mp3Title = mp3Title;
    }

    /**
     * Primärschlüssek mp3_id
     *
     * @return int id
     */
    @Id
    @Column(name = "mp3_id")
    @GeneratedValue
    @Override
    public int getMp3Id() {
        return mp3Id;
    }

    /**
     * Setzt eine mp3_id
     *
     * @param mp3Id int id
     */
    public void setMp3Id(int mp3Id) {
        this.mp3Id = mp3Id;
    }

    /**
     * File in der Datenbank
     *
     * @return byte[]
     */
    @Column(name = "mp3_file")
    @Lob
    @Override
    public byte[] getMp3File() {
        return mp3File;
    }

    /**
     * Setzt ein File
     *
     * @param mp3_file byte[]
     */
    public void setMp3File(byte[] mp3_file) {
        this.mp3File = mp3_file;
    }

    /**
     * Mp3 Titel
     *
     * @return String
     */
    @Column(name = "mp3_title")
    @Override
    public String getMp3Title() {
        return mp3Title;
    }

    /**
     * Setzt ein Mp3 Titel
     *
     * @param mp3_title String
     */
    public void setMp3Title(String mp3_title) {
        this.mp3Title = mp3_title;

    }

    /**
     * Hier wird die ManyToOne Beziehung mit der ArtistBean realisiert
     *
     * @return
     */
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Mp3ArtistBean.class)
    @JoinColumn(name = "artist_id", nullable = true)
    public Mp3ArtistBean getMp3ArtistBean() {
        return this.mp3Artist;
    }

    /**
     * Setzt ein Mp3Artist
     *
     * @param mp3Artist Mp3Artist
     */
    public void setMp3ArtistBean(Mp3ArtistBean mp3Artist) {
        this.mp3Artist = mp3Artist;
    }

    /**
     * Holt die Artist Id
     *
     * @return int id
     */
    @Override
    public int getArtistId() {
        return mp3Artist.getArtistId();
    }

    /**
     * Wandelt das ein File in ein byte Array um, welches für den Blob Datentyp
     * in der Datenbank notwendig ist.
     *
     * @param file File welches umgewandelt werden soll
     */
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

    /**
     * Setzt eine Artist Id
     *
     * @param artist_id int id
     */
    public void editArtistId(int artist_id) {
        mp3Artist.setArtistId(artist_id);
    }

    /**
     * Holt den Namen eines Artists
     *
     * @return String
     */
    @Override
    public String getArtistName() {
        return mp3Artist.getArtistName();
    }

}
