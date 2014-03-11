/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentserviceremoteinterfaces.interfaces;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.util.List;
import javax.ejb.Remote;



/**
 *
 * @author Mettbroetchen
 */
@Remote
public interface Commentservice {
    public List<Comment> getAllArtistCommentsById(int id);
    public List<Comment> getAllMp3CommentsById(int id);
    public void addComment(String text, long id, String identfier);
}
