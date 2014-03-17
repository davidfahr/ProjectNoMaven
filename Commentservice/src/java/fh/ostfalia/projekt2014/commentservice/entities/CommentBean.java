package fh.ostfalia.projekt2014.commentservice.entities;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
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
 * CommentBean persistieren der nötigen Mp3
 * Tabellen und Attribute in der DB mehr Information siehe Systemdokumentation
 * Kapitel 7
 *
 */
@Entity(name = "Comment")
@NamedQueries({
    @NamedQuery(name = "getAllMp3CommentsById", query = "SELECT c FROM Comment c where c.cMp3Id=:id"),
    @NamedQuery(name = "getAllArtistCommentsById", query = "SELECT c FROM Comment c where c.cmp3ArtistId=:id")
})
public class CommentBean implements Serializable, Comment {

    private static final long serialVersionUID = 1L;
    /**
     * Erstellt neue Spalte in der Datenbank mit dem Primärschlüssel c_id der
     * Kommentare Wird automatische durch @GeneratedValue erzeugt und eingesetzt
     *
     */
    @Id
    @Column(name = "c_id")
    @GeneratedValue
    private Long cId;

    /**
     *
     * @return Gibt Kommentar Id zurück
     */
    @Override
    public Long getcId() {
        return cId;
    }

    /**
     * Setzt Kommentar Id
     *
     * @param cId
     */
    public void setId(Long cId) {
        this.cId = cId;
    }

    /**
     * Erstellt neue Spalte in der Datenbank in dem die Texte der Kommentare
     * gespeichert werden
     */
    @Column(name = "c_text")
    private String cText;

    /**
     *
     * @return Gibt Text des Kommentares zurück
     */
    @Override
    public String getcText() {
        return cText;
    }

    /**
     * Legt Kommentartext fest
     *
     * @param cText
     */
    public void setcText(String cText) {
        this.cText = cText;
    }

    /**
     * Erstellt neue Spalte in der Datenbank in dem die Id der Mp3 gespeichert
     * werden zu dem das Kommentar gehört Wenn Kommtar ein Artist-Kommentar ist,
     * ist dieses Attribut null
     */
    @Column(name = "c_mp3_id")
    private Long cMp3Id;

    /**
     *
     * @return Gibt Mp3-Id des Kommentares wieder
     */
    @Override
    public Long getcMp3Id() {
        return cMp3Id;
    }

    /**
     * Legt Mp3-Id fest
     *
     * @param cMp3Id
     */
    public void setcMp3Id(Long cMp3Id) {
        this.cMp3Id = cMp3Id;
    }

    /**
     * Erstellt neue Spalte in der Datenbank in dem die Id des Mp3 Artist
     * gespeichert werden zu dem das Kommentar gehört Wenn Kommtar ein
     * Mp3-Kommentar ist, ist dieses Attribut null
     */
    @Column(name = "c_mp3Artist_id")
    private Long cmp3ArtistId;

    /**
     *
     * @return Gibt Artist-Id des Kommentares wieder
     */
    @Override
    public Long getCmp3ArtistId() {
        return cmp3ArtistId;
    }

    /**
     * Legt Aritist-Id fest
     *
     * @param cmp3ArtistId
     */
    public void setCmp3ArtistId(Long cmp3ArtistId) {
        this.cmp3ArtistId = cmp3ArtistId;
    }
}
