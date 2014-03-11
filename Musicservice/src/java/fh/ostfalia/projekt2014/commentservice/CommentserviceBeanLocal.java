/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.commentservice;

import javax.ejb.Local;

/**
 *
 * @author Yannick
 */
@Local
public interface CommentserviceBeanLocal {
    public void addComment(String text, long id, String identfier); 
}
