package fh.ostfalia.projekt2014.commentservice;

import fh.ostfalia.projekt2014.commentservice.dao.CommentDao;
import fh.ostfalia.projekt2014.commentservice.entities.CommentBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces.Commentservice;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * CommentServiceBean als Remotezugriff auf
 * den Commentservice mehr Information siehe Systemdokumentation Kapitel 7
 * Aufruf von: Webserver->Loadbalancer->Musicservice->CommentserviceBean
 */
@Stateless
public class CommentserviceBean implements Commentservice, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Holt die nötige EJB CommentDao, um dort die implementierten Methoden
     * aufrufen zu können
     */
    @EJB
    CommentDao commentDao;

    /**
     * Siehe Implementierung der Methode in Klasse CommentDao
     *
     * @param id Id des gewünschten Artist
     * @return Liste mit Artist Kommentaren zu einem Artist
     */
    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentDao.getAllArtistCommentsById(id);
    }

    /**
     * Siehe Implementierung der Methode in Klasse CommentDao
     *
     * @param id Id der gewünschten Mp3
     * @return Liste mit allen Kommentaren zu einer Mp3
     */
    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentDao.getAllMp3CommentsById(id);
    }

    /**
     * Ruft addComment im CommentDao auf, der ein neues Kommtar hinzufügt
     *
     * @param text Der Text des Kommentares
     * @param id die Id zu wem das Kommentar sein soll
     * @param identfier Unterscheidung ob Artist oder Mp3 Kommentar
     */
    @Override
    public void addComment(String text, long id, String identfier) {
        CommentBean c = new CommentBean();
        if (identfier.equals("mp3")) {
            c.setcMp3Id(id);
        } else if (identfier.equals("artist")) {
            c.setCmp3ArtistId(id);
        }
        c.setcText(text);
        commentDao.addComment(c);
    }
}
