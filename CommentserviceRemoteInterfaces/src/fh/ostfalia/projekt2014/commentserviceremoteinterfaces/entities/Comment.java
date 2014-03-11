/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities;

/**
 *
 * @author Mettbroetchen
 */
public interface Comment {
    public Long getcId();
    public String getcText();
    public Long getcMp3Id();
    public Long getCmp3ArtistId();
}
