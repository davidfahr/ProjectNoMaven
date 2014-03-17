package fh.ostfalia.projekt2014.commentservice.dao;

import fh.ostfalia.projekt2014.commentservice.entities.CommentBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * CommentDao zur Implementierung der
 * Commentservice Methoden mehr Information siehe Systemdokumentation Kapitel 7
 * Aufruf von:
 * Webserver->Loadbalancer->Musicservice->CommentserviceBean->CommentserviceDao
 */
@Stateless
public class CommentDao {

    @PersistenceContext(unitName = "CommentservicePU")
    private EntityManager em;

    /**
     * Methode zum persistieren eines Kommentares
     *
     * @param comment Das Kommtentarobjekt, das in die Datenbank persistiert
     * werden soll
     */
    public void addComment(Comment comment) {
        em.persist(comment);
    }

    /**
     * Gibt ein Kommentar anhand seiner Id wieder
     *
     * @param cId Id des gewünschten Kommentares
     * @return Kommentar mit der angegebenen Id
     */
    private Comment getComment(int cId) {
        return em.find(CommentBean.class, cId);
    }

    /**
     * Gibt eine Liste von allen Kommentaren, die als Artist Id die übergebene
     * Id haben
     *
     * @param id Id des gewünschten Artist
     * @return Liste mit allen Artist Kommentaren zu einem Artist
     */
    public List<Comment> getAllArtistCommentsById(int id) {
        Query query = em.createNamedQuery("getAllArtistCommentsById");
        query.setParameter("id", id);
        List<CommentBean> comments = query.getResultList();
        return new LinkedList<Comment>(comments);
    }

    /**
     * Gibt eine Liste von allen Kommentaren, die als Mp3-Id die übergebene Id
     * haben
     *
     * @param id Id der gewünschten Mp3
     * @return Liste mit allen Kommentaren zu einer Mp3
     */
    public List<Comment> getAllMp3CommentsById(int id) {
        Query query = em.createNamedQuery("getAllMp3CommentsById");
        query.setParameter("id", id);
        List<CommentBean> comments = query.getResultList();
        return new LinkedList<Comment>(comments);
    }

    /**
     * Gibt ein Kommentar einer Mp3 zurück, die die übergebene Id hat
     *
     * @param mp3Id Id der gewünschten Mp3
     * @return ein Kommentar zu einer Mp3
     */
    public Comment getCommentByMp3Id(int mp3Id) {
        return em.find(CommentBean.class, mp3Id);
    }

    /**
     * Gibt ein Kommentar zurück, das anhand der mp3ArtistId in der Mp3 Table
     * der Datenbank gefunden werden kann
     *
     * @param mp3ArtistId Id des Artist
     * @return ein Kommentar zu einer Mp3 anhand der Mp3ArtistId
     */
    public CommentBean getCommentByMp3ArtistId(int mp3ArtistId) {
        return em.find(CommentBean.class, mp3ArtistId);
    }

    /**
     * Löscht ein Kommentar aus der Datenbank
     *
     * @param cId Id des Kommentares in der DB
     */
    public void deleteComment(int cId) {
        em.remove(getComment(cId));
    }

}
