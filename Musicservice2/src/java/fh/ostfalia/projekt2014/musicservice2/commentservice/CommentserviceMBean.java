package fh.ostfalia.projekt2014.musicservice2.commentservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces.Commentservice;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Stateless
public class CommentserviceMBean extends RemoteBean implements CommentserviceBeanLocal {

    private Commentservice commentserviceBean;

    public CommentserviceMBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Commentservice/CommentserviceBean");
    }

    @PostConstruct
    public void initBean() {
        commentserviceBean = (Commentservice) super.getObject();
    }

    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentserviceBean.getAllArtistCommentsById(id);
    }

    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentserviceBean.getAllMp3CommentsById(id);
    }

    @Override
    public void addComment(String text, long id, String identfier) {
        commentserviceBean.addComment(text, id, identfier);
    }
}
