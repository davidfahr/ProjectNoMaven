/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.commentservice.dao;

import fh.ostfalia.projekt2014.commentservice.entities.CommentBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author KingDCB
 */
@Stateless
public class CommentDao {

    @PersistenceContext(unitName ="CommentservicePU")
    private EntityManager em;

    public void addComment(Comment comment) {
             em.persist(comment);       
    }
    
    private Comment getComment(int cId) {
        return em.find(CommentBean.class, cId);
    }

    public List<Comment> getAllArtistCommentsById(int id){
        return null;
    }
    
    public List<Comment> getAllMp3CommentsById(int id){
        return null;
    }
    
    public Comment getCommentByMp3Id(int mp3Id) {
        return em.find(CommentBean.class, mp3Id);
    }

    public CommentBean getCommentByMp3ArtistId(int mp3ArtistId) {
        return em.find(CommentBean.class, mp3ArtistId);
    }

    public void deleteComment(int cId) {
        em.remove(getComment(cId));
    }

}
