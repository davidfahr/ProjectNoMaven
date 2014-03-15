/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.commentservice.entities;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author KingDCB
 */
@Entity(name = "Comment")
@NamedQueries({
    @NamedQuery(name = "getAllMp3CommentsById", query = "SELECT c FROM Comment c where c.cMp3Id=:id"),
        @NamedQuery(name = "getAllArtistCommentsById", query = "SELECT c FROM Comment c where c.cmp3ArtistId=:id")
})
public class CommentBean implements Serializable, Comment{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "c_id")
    @GeneratedValue
    private Long cId;
    

    @Override
    public Long getcId() {
        return cId;
    }

    public void setId(Long cId) {
        this.cId = cId;
    }
    
    @Column(name = "c_text")
    private String cText;

    @Override
    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }
    
    @Column (name = "c_mp3_id")
    private Long cMp3Id;

    @Override
    public Long getcMp3Id() {
        return cMp3Id;
    }

    public void setcMp3Id(Long cMp3Id) {
        this.cMp3Id = cMp3Id;
    }
  
    
    @Column (name = "c_mp3Artist_id")
    private Long cmp3ArtistId;

    @Override
    public Long getCmp3ArtistId() {
        return cmp3ArtistId;
    }

    public void setCmp3ArtistId(Long cmp3ArtistId) {
        this.cmp3ArtistId = cmp3ArtistId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cId != null ? cId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommentBean)) {
            return false;
        }
        CommentBean other = (CommentBean) object;
        if ((this.cId == null && other.cId != null) || (this.cId != null && !this.cId.equals(other.cId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fh.ostfalia.projekt2014.commentservice.entities.Comment[ id=" + cId + " ]";
    }
}