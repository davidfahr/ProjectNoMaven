/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author Mettbrötchen
 */
public class LoadbalancerMBean extends RemoteManagedBean {

    private Part part;
    private String commentText;
    private long id;
    private Loadbalancer loadbalancerRemoteBean;
    private List<Comment> commentList;
    FacesContext faces = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) faces.getExternalContext().getRequest();
            
    ServletContext ctx = request.getServletContext();
            
 
  
    private LoadbalancerResult loadbalancerResult;


 

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


    public Mp3 getMp3(int mp3Id) {
       return loadbalancerRemoteBean.getMp3(mp3Id);
    }
    
    public Mp3 getArtist(int mp3ArtistId) {
        return loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId);
    }

    public void upload() {
        
            // String pfad = ctx.getRealPath(part.getSubmittedFileName());
           
            String pfad = ctx.getRealPath("/Uploads/" + part.getSubmittedFileName());
            
            System.out.println("PFAAAAD: " + pfad.toString());
            
            
            
            
            loadbalancerRemoteBean.upload(pfad);
       
      
    
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
        loadbalancerRemoteBean.addComment(commentText, id, identifier);
        if (identifier.equals("artist")){
        return "view_artist.xhtml?faces-redirect=trueid=" + id;
        }
        else if(identifier.equals("mp3")){
            return "view_mp3.xhtml?faces-redirect=trueid=" + id;
        }
        else{
            return null;
        }
        
    }
    
     public String getIdParameter(){
       return loadbalancerRemoteBean.getIdParameter();
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
