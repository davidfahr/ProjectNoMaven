/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.entities.Mp3;



import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

/**
 *
 * @author Mettbrötchen
 */
public class LoadbalancerMBean extends RemoteManagedBean {

    private String part;
    private String commentText;
    private long id;
    private Loadbalancer loadbalancerRemoteBean;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void upload() {
        loadbalancerRemoteBean.upload(part);
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void downloadMp3File(String filename, int mp3Id) {
        loadbalancerRemoteBean.downloadMp3File(filename, mp3Id);
    }

    public List<Comment> getAllArtistCommentsById(int id) {
        return loadbalancerRemoteBean.getAllArtistCommentsById(id);
    }

    public List<Comment> getAllMp3CommentsById(int id) {
        List<Comment> commentList = loadbalancerRemoteBean.getAllMp3CommentsById(id);
        System.out.println("WEBSERVERRRRRRRRR SAGT:");
        System.out.println(commentList.get(0).getcText());
        return commentList;
    }

    public void addComment(String identfier) {
        System.out.println("WEBSERVICE");
        //musicserviceBeanWebserver.addComment(commentText, id, identfier);
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
    
    public void whoAmI(){
        loadbalancerRemoteBean.whoAmI();
    }

}
