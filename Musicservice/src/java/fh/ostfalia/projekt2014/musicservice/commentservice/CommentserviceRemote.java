/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.musicservice.commentservice;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces.Commentservice;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author Mettbroetchen
 */
@Stateless
public class CommentserviceRemote extends RemoteBean implements CommentserviceBeanLocal{
    
    private Commentservice commentserviceBean;
    
    public CommentserviceRemote() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Commentservice/CommentserviceBean");
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        
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
        System.out.println("MUSICSERVICE aber COMMENTSERVICE BEAN");
        commentserviceBean.addComment(text, id, identfier);
    }

}