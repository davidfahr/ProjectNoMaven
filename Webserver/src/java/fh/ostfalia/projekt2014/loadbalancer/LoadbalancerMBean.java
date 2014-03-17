/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Mettbrötchen
 */
public class LoadbalancerMBean extends RemoteBean {

    private Part part;
    private String commentText;
    private int id;
    private Loadbalancer loadbalancerRemoteBean;
    private List<Comment> commentList;
    FacesContext faces = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) faces.getExternalContext().getRequest();
    ServletContext ctx = request.getServletContext();

    //Hole aktuelle Session, erzeuge aber keine neue Session, falls keine existiert (-> daher getSession(false)
    public LoadbalancerMBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Loadbalancer/LoadbalancerBean");
        System.out.println("ENDELookup");
    }

    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        loadbalancerRemoteBean = (Loadbalancer) super.getObject();
    }

    public Loadbalancer getMusicserviceBean() {
        return loadbalancerRemoteBean;
    }

    public void setMusicserviceBean(Loadbalancer musicserviceBean) {
        this.loadbalancerRemoteBean = musicserviceBean;
    }

    public List<Mp3> getAllMp3s() {
        return loadbalancerRemoteBean.getAllMp3s();
    }

    public List<Mp3> getMp3ByArtist(int id) {
        return loadbalancerRemoteBean.getMp3ByArtist(id);
    }

    public Mp3 getMp3(int mp3Id) {
        return loadbalancerRemoteBean.getMp3(mp3Id);
    }

    public Mp3 getArtist(int mp3ArtistId) {
        System.out.println(loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId).getArtistName());
        System.out.println("NAME:   " + loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId));
        return loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId);

    }

    public void upload() {

        String pfad = ctx.getRealPath("/Uploads/" + part.getSubmittedFileName());

        if (!pfad.equals(ctx.getRealPath("/Uploads"))) {
            loadbalancerRemoteBean.upload(pfad);
            faces.addMessage(null, new FacesMessage("File was uploaded successfully!"));
        } else {
            faces.addMessage(null, new FacesMessage("Please select a File"));
        }
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public void downloadMp3File(String filename, int mp3Id) {
        loadbalancerRemoteBean.downloadMp3File(filename, mp3Id);
    }

    public List<Comment> getAllArtistCommentsById(int id) {
        commentList = loadbalancerRemoteBean.getAllArtistCommentsById(id);
        return commentList;
    }

    public List<Comment> getAllMp3CommentsById(int id) {
        commentList = loadbalancerRemoteBean.getAllMp3CommentsById(id);
        return commentList;
    }

    public String addComment(String identifier) {
        String path = "";
        if (!commentText.equals("")) {
            loadbalancerRemoteBean.addComment(commentText, id, identifier);
        }

        if (identifier.equals("artist")) {
            path = "view_artist.xhtml?faces-redirect=trueid=" + id;
        } else if (identifier.equals("mp3")) {
            path = "view_mp3.xhtml?faces-redirect=trueid=" + id;       
        }

        return path;
    }

    public String getIdParameter() {
        return loadbalancerRemoteBean.getIdParameter();
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
