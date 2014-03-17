package fh.ostfalia.projekt2014.musicservice.commentservice;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface welches von der Klasse CommentserviceRemote implementiert wird.
 * Bietet die Methode für die MusicserviceBean
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Local
public interface CommentserviceBeanLocal {

    /**
     * Fügt einen Comment zu Datenbank hinzu.
     *
     * @param text Text vom Comment
     * @param id Kann entweder id der Mp3 Datei sein oder id des Artists
     * @param identfier Gibt an zu welcher Tabelle der Comment gehört. Erlaubte
     * Parameter: "mp3" und "artist"
     */
    public void addComment(String text, long id, String identfier);

    /**
     * Fragt die Datenbank nach allen Comments eines bestimmten Artists ab.
     *
     * @param id id des Artists
     * @return List<Comment> Liste von Comments
     */
    public List<Comment> getAllArtistCommentsById(int id);

    /**
     * Fragt die Datenbank nach allen Comments einesr bestimmten Mp3 ab.
     *
     * @param id id der Mp3
     * @return List<Comment> Liste von Comments
     */
    public List<Comment> getAllMp3CommentsById(int id);
}
