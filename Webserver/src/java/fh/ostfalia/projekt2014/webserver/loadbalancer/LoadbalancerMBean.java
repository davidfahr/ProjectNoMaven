package fh.ostfalia.projekt2014.webserver.loadbalancer;

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

/** LoadbalancerMBean
 * 
 *  Die Methoden dieser Klasse leiten die Anfragen des Benutzers an den Loadbalancer weiter. Dieser übernimmt dann die Verteilung der Anfragen
 *  an die beiden existierenden Musikdienste. 
 * 
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 
 * 
 * erbt von:  RemoteBean, damit Lookup durchgeführt werden kann.
 * Aufruf von:
 * Beispiele: 
 */
public class LoadbalancerMBean extends RemoteBean {
    
    //lokale Variable zum Speichern von Mp3-Dateien
    private Part part;
    //lokale Kommentartext-Variable 
    private String commentText;
    //lokale ID-Variable 
    private int id;
    //Variable für entferntes Objekt (Loadbalacner->LoadbalancerBean)
    private Loadbalancer loadbalancerRemoteBean;
    //lokale Variable für Kommentarliste zur Ausgabe auf der Oberfläche
    private List<Comment> commentList;
    
    
    FacesContext faces = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) faces.getExternalContext().getRequest();
    ServletContext ctx = request.getServletContext();

    

    /**
     * Konstruktor mit IP, Port und JNDI-Namen
     * Zweck: Festlegung der Konfigurationsparameter des entfernten Objektes
     */
    public LoadbalancerMBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Loadbalancer/LoadbalancerBean");
        System.out.println("Konfiguration");
    }

    /**
     * Anforderung und Setzen des entfernten Objektes in eine lokale Variable über die zuvor konfigurierte entfernte Bean (s. Konstruktor)
     */
    @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        loadbalancerRemoteBean = (Loadbalancer) super.getObject();
    }

    /**
     *
     * @return Rückgabe der entfernten Bean
     */
    public Loadbalancer getMusicserviceBean() {
        return loadbalancerRemoteBean;
    }


    /**
     *
     * @return Rückgabe der angeforderten MP3-Dateien (Implementierung s. Musikdienst->Musicservice.java)
     */
    public List<Mp3> getAllMp3s() {
        return loadbalancerRemoteBean.getAllMp3s();
    }

    /**
     * entfernte Anfrage auf  Mp3-Datei per Artist-ID
     * @param id Artist-ID
     * @return
     */
    public List<Mp3> getMp3ByArtist(int id) {
        return loadbalancerRemoteBean.getMp3ByArtist(id);
    }

    /**
     * entfernte Anfrage auf  Mp3-Dateiper Mp3-ID
     * @param mp3Id Mp3-ID
     * @return
     */
    public Mp3 getMp3(int mp3Id) {
        return loadbalancerRemoteBean.getMp3(mp3Id);
    }

    /**
     * entfernte Anfrage auf  Mp3-Datei per Artist-ID
     * @param mp3ArtistId
     * @return
     */
    public Mp3 getArtist(int mp3ArtistId) {
        System.out.println(loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId).getArtistName());
        System.out.println("NAME:   " + loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId));
        return loadbalancerRemoteBean.getMp3ArtistByArtistId(mp3ArtistId);

    }

    /**
     * Weiterleitung des Uploads an Loadbalancer (der den Upload dann auf den Musikdienst 1 oder den Musikdienst 2 verteilt)
     */
    public void upload() {
        //Definition des Pfades Anhand der part-Datei aus dem Dialog
        String pfad = ctx.getRealPath("/Uploads/" + part.getSubmittedFileName());

        if (!pfad.equals(ctx.getRealPath("/Uploads"))) {
            //entfernter Aufruf des Uploads (auf Loadbalancer)
            loadbalancerRemoteBean.upload(pfad);
            faces.addMessage(null, new FacesMessage("File was uploaded successfully!"));
        } else {
            faces.addMessage(null, new FacesMessage("Please select a File"));
        }
    }

    /**
     *
     * @return Rückgabe der Part-Datei (Für Mp3-File)
     */
    public Part getPart() {
        return part;
    }

    /**
     *
     * @param part Setzen der Part-Datei (Für Mp3-File)
     */
    public void setPart(Part part) {
        this.part = part;
    }

    /**
     * Download-Anfrage anhand des
     * @param filename Dateinamens
     * @param mp3Id und der mp3ID
     */
    public void downloadMp3File(String filename, int mp3Id) {
        loadbalancerRemoteBean.downloadMp3File(filename, mp3Id);
    }

    /**
     * Hole Artist-Comments anhand der Mp3ID
     * @param id
     * @return
     */
    public List<Comment> getAllArtistCommentsById(int id) {
        commentList = loadbalancerRemoteBean.getAllArtistCommentsById(id);
        return commentList;
    }

    /**
     *
     * @param id
     * @return
     */
    public List<Comment> getAllMp3CommentsById(int id) {
        commentList = loadbalancerRemoteBean.getAllMp3CommentsById(id);
        return commentList;
    }

    /**
     *
     * @param identifier
     * @return
     */
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

    /**
     *
     * @return
     */
    public String getIdParameter() {
        return loadbalancerRemoteBean.getIdParameter();
    }

    /**
     *
     * @return
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     *
     * @param commentText
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /**
     *
     * @return Aufruf der lokalen ID-Variable
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id Setzen der lokalen ID-Variable
     */
    public void setId(int id) {
        this.id = id;
    }
}
