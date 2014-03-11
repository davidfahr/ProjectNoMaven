/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces.Commentservice;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Mettbroetchen
 */
public class CommentserviceMBean extends RemoteManagedBean{
    public CommentserviceMBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Commentservice/CommentserviceBean");
    }
    
    private Commentservice commentserviceBean;
    
    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        commentserviceBean = (Commentservice) super.getObject();
    }
    
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentserviceBean.getAllArtistCommentsById(id);
    }
    
    
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentserviceBean.getAllMp3CommentsById(id);
    }
    
    public void addComment(String text, long id, String identfier) {
        System.out.println("MUSICSERVICE aber COMMENTSERVICE BEAN");
        commentserviceBean.addComment(text, id, identfier);
    }

}
