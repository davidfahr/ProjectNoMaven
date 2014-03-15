/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Mettbroetchen
 */
@Stateless
public class CommentserviceBean implements Commentservice, Serializable{
   
     private static final long serialVersionUID = 1L;
     
    @EJB
    CommentDao commentDao;
    
    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentDao.getAllArtistCommentsById(id);
    }

    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentDao.getAllMp3CommentsById(id);
    }

    @Override
    public void addComment(String text, long id, String identfier) {
        System.out.println("COMMENTSERVICDE");
        System.out.println(identfier);
        CommentBean c = new CommentBean();
        
        if(identfier.equals("mp3")){
           c.setcMp3Id(id);
        } else if(identfier.equals("artist")){
           c.setCmp3ArtistId(id);       
        }
        c.setcText(text);
        
        commentDao.addComment(c);
    }


    
}
