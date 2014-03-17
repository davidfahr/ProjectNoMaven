package fh.ostfalia.projekt2014.musicservice;

import fh.ostfalia.projekt2014.musicservice.commentservice.CommentserviceBeanLocal;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicservice.dao.Mp3DaoLocal;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * MusicserviceBean ist die Schnittstelle nach außen und beinhaltet alle
 * Methoden die entfernt für anderen Komponenten bereitgestellt wird. Es wird
 * das musciserviceremoteinterface als Schnittstele implementiert. Alle hier
 * definierten Methoden sind nur Weiterleitungen an den entsprechenden Dienst!
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog Aufruf von:
 * Webserver->Loadbalancer->MusicserviceBean
 */
@Stateless
public class MusicserviceBean implements Musicservice, Serializable {

    private static final long serialVersionUID = 1L;
    //Initialisierung des Mp3Daos 
    @EJB
    private Mp3DaoLocal mp3Dao;
    //Initialisierung des CommentDaos
    @EJB
    private CommentserviceBeanLocal commentservice;

    /**
     * Die Methoden des folgenden Abschnitts beziehen sich auf Methoden des
     * Musicservices
     */
    /**
     * Fragt die Datenbank nach einer bestimmten Mp3 inkl. Artist ab und gibt
     * diese zurück
     *
     * @param mp3_id id des Datensatzes, welcher zurückkommen soll
     * @return Mp3 Entity mit den Daten aus der Datenbank
     */
    @Override
    public Mp3 getMp3(int mp3_id) {
        return mp3Dao.getMp3(mp3_id);
    }

    /**
     * Fragt die Datenbank nach allen Mp3s inkls Artist ab und gibt sie zurück
     *
     * @return List<Mp3> Liste mit Mp3 Entitys
     */
    @Override
    public List<Mp3> getAllMp3s() {
        return mp3Dao.getAllMp3();
    }

    /**
     * Fragt die Datenbank nach allen Artists einer bestimmten artist_id ab
     *
     * @param mp3ArtistId id des Datensatzes, welcher zurückkommen soll
     * @return mp3ArtistId List<Mp3> Liste mit Artists
     */
    @Override
    public List<Mp3> getMp3ByArtist(int mp3ArtistId) {
        return mp3Dao.getMp3ByArtist(mp3ArtistId);
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Artist ab und gibt diesen
     * zurück
     *
     * @param mp3ArtistId id des Datensatzes, welcher zurückkommen soll
     * @return Mp3 Entity mit den Daten aus der Datenbank, wobei hier nur
     * Informationen vom Artist enthalten sind
     */
    @Override
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId) {
        return mp3Dao.getMp3ArtistByArtistId(mp3ArtistId);
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Mp3File ab
     *
     * @param mp3Id id vom File welches zurück gegeben werden soll
     * @return byte[] Byte Array
     */
    @Override
    public byte[] getMp3File(int mp3Id) {
        return mp3Dao.getMp3File(mp3Id);
    }

    /**
     * Führt einen Upload aus. Somit wird ein ausgewähltes File inkl.
     * Informationen in die Datenbank gespeichert.
     *
     * @param path String mit Dateipfad zur Datei
     */
    @Override
    public void upload(String path) {
        mp3Dao.upload(path);
    }

    /**
     * Aktualisiert eigene Datenbank mit entsprechender Mp3Bean. Der Update wird
     * immer vom anderen Musicservice aufgerufen.
     *
     * @param mp3Bean Mp3 Entity welche aktualisiert werden soll
     */
    @Override
    public void update(Mp3 mp3Bean) {
        mp3Dao.update(mp3Bean);
    }

    /**
     * Gibt die id eines GET Requests aus der Url des Browsers wieder
     *
     * @return String id
     */
    @Override
    public String getIdParameter() {
        return mp3Dao.getIdParameter();
    }

    /**
     * Führt einen Download eines Mp3Files aus indem ein response erstellt wird
     *
     * @param filename Name, wie die Datei nach dem Download heißen soll
     * @param mp3Id id des Mp3Files welches gedownloaded werden soll
     */
    @Override
    public void downloadMp3File(String filename, int mp3Id) {
        mp3Dao.downloadMp3File(filename, mp3Id);
    }

    /**
     * Gibt an auf welchen Musicservice sich der Aufruf befindet. Ist für die
     * LoadbalancerSimulation notwendig.
     */
    @Override
    public void whoAmI() {
        System.out.println("Ich bin Musicservice 1");
    }

    /**
     * Die Methoden des folgenden Abschnitts beziehen sich auf Methoden des
     * Commentservices
     */
    /**
     * Fragt die Datenbank nach allen Comments eines bestimmten Artists ab.
     *
     * @param id id des Artists
     * @return List<Comment> Liste von Comments
     */
    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentservice.getAllArtistCommentsById(id);
    }

    /**
     * Fragt die Datenbank nach allen Comments einesr bestimmten Mp3 ab.
     *
     * @param id id der Mp3
     * @return List<Comment> Liste von Comments
     */
    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentservice.getAllMp3CommentsById(id);
    }

    /**
     * Fügt einen Comment zu Datenbank hinzu.
     *
     * @param text Text vom Comment
     * @param id Kann entweder id der Mp3 Datei sein oder id des Artists
     * @param identfier Gibt an zu welcher Tabelle der Comment gehört. Erlaubte
     * Parameter: "mp3" und "artist"
     */
    @Override
    public void addComment(String text, long id, String identfier) {
        commentservice.addComment(text, id, identfier);
    }

}
