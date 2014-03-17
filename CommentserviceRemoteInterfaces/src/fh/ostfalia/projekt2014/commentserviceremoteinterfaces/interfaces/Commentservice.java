package fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.util.List;
import javax.ejb.Remote;

/**
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Interface der CommentserviceBean für den
 * Remotezugriff
 */
@Remote
public interface Commentservice {

    /**
     * siehe Implementierung in CommentserviceBean
     */
    public List<Comment> getAllArtistCommentsById(int id);

    /**
     * siehe Implementierung in CommentserviceBean
     */
    public List<Comment> getAllMp3CommentsById(int id);

    /**
     * siehe Implementierung in CommentserviceBean
     */
    public void addComment(String text, long id, String identfier);
}
