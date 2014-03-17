package fh.ostfalia.projekt2014.musicservice2.commentservice;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Local
public interface CommentserviceBeanLocal {
    public void addComment(String text, long id, String identfier);
    public List<Comment> getAllArtistCommentsById(int id);
    public List<Comment> getAllMp3CommentsById(int id);
}
