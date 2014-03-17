package fh.ostfalia.projekt2014.musicservice.commentservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces.Commentservice;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 * CommentserviceRemote regelt den Zugriff auf den Commentservice.
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Stateless
public class CommentserviceRemote extends RemoteBean implements CommentserviceBeanLocal {
    //Kommt von commentserviceremoteinterface und bildet die Verbindung dorthin
    private Commentservice commentserviceBean;

    /**
     * Ruft den Konstruktor der RemoteBean auf und setzt notwendige Parameter
     * für den lookup.
     */
    public CommentserviceRemote() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Commentservice/CommentserviceBean");
    }

    /**
     * Initialisierung der CommentserviceBean. Hier wird der eigentlich lookup
     * ausgeführt.
     */
    @PostConstruct
    public void initBean() {
        commentserviceBean = (Commentservice) super.getObject();
    }

    /**
     * Fragt die Datenbank nach allen Comments eines bestimmten Artists ab.
     *
     * @param id id des Artists
     * @return List<Comment> Liste von Comments
     */
    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentserviceBean.getAllArtistCommentsById(id);
    }

    /**
     * Fragt die Datenbank nach allen Comments einesr bestimmten Mp3 ab.
     *
     * @param id id der Mp3
     * @return List<Comment> Liste von Comments
     */
    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentserviceBean.getAllMp3CommentsById(id);
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
        System.out.println("MUSICSERVICE aber COMMENTSERVICE BEAN");
        commentserviceBean.addComment(text, id, identfier);
    }

}
