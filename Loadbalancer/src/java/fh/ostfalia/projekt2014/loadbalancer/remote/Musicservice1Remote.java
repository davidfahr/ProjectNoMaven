package fh.ostfalia.projekt2014.loadbalancer.remote;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Stateless Session Bean, die einen lookup
 * auf ihren Musicservice macht und ein Stub-Objekt dessen Bean zu erzeugen.
 * Leitet die Aufrufe der LoadbalancerBean an die entsprechende MusicserviceBean
 * weiter.
 * Aufruf: Webserver(LoadbalancerMBean)->LoadbalancerBean->Musicservice1Remote
 */
@Stateless
public class Musicservice1Remote extends RemoteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Musicservice musicservice1Bean;

    /**
     * Konstruktor, der einen lookup auf den Musicservice durchführt.
     */
    public Musicservice1Remote() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Musicservice/MusicserviceBean");
        System.out.println("ENDELookup --> Musikdienst 1");
    }

    /**
     * Initialisieren der entfernten MusicserviceBean.
     * Holt sich deren Stub-Objekt über das Remote-Interface Musicservice.
     */
    @PostConstruct
    public void initBean() {
        musicservice1Bean = (Musicservice) super.getObject();
    }

    /**
     * 
     * @return Gibt ein Musicservice Interface zurück dass in der MusicserviceBean
     * implementiert ist
     */
    public Musicservice getMusicservice1Bean() {
        return musicservice1Bean;
    }

    /**
     * Setzt die MusicserviceBean
     * @param musicservice1Bean 
     */
    public void setMusicservice1Bean(Musicservice musicservice1Bean) {
        this.musicservice1Bean = musicservice1Bean;
    }

    /**
     * Ruft whoAmI auf dem Musicservice auf. Dieses gibt einen System.out
     * zur überprüfung der Loadbalancer-Simulation aus.
     */
    public void whoAmI() {
        musicservice1Bean.whoAmI();
    }

    /**
     *
     * @return Gibt eine Liste mit allen Mp3s zurück
     */
    public List<Mp3> getAllMp3s() {
        return musicservice1Bean.getAllMp3s();
    }

    /**
     *
     * @param mp3Id Id der gewünschten Mp3
     * @return Gibt eine Mp3 zurück anhand ihrer Id
     */
    public Mp3 getMp3(int mp3Id) {
        return musicservice1Bean.getMp3(mp3Id);
    }

    /**
     *
     * @param mp3Id Id der gewünschten Mp3
     * @return Gibt eine Mp3-Datei zurück
     */
    public byte[] getMp3File(int mp3Id) {
        return musicservice1Bean.getMp3File(mp3Id);
    }

    /**
     *
     * @return Gibt Id der aufgerufenen Seite aus der URL wieder
     */
    public String getIdParameter() {
        return musicservice1Bean.getIdParameter();
    }

    /**
     *
     * @param path Übergibt den Pfad der Datei, die hochgeladen werden soll
     */
    public void upload(String path) {
        musicservice1Bean.upload(path);
    }

    /**
     *
     * @param filename Name der Datei
     * @param mp3Id Id der gewünschten Mp3
     */
    public void downloadMp3File(String filename, int mp3Id) {
        musicservice1Bean.downloadMp3File(filename, mp3Id);
    }

    /**
     *
     * @param id Id des gewünschten Artists
     * @return Gibt Liste mit allen Kommentare eines Artist
     */
    public List<Comment> getAllArtistCommentsById(int id) {
        return musicservice1Bean.getAllArtistCommentsById(id);
    }

    /**
     *
     * @param id Id der gewünschten Mp3
     * @return Gibt Liste mit allen Kommentare einer Mp3
     */
    public List<Comment> getAllMp3CommentsById(int id) {
        return musicservice1Bean.getAllMp3CommentsById(id);
    }

    /**
     *
     * @param text Text des Kommentares
     * @param id Id des gewünschten Artists oder Mp3
     * @param identfier Unterscheidet zwischen Mp3 und Artist Kommentar
     */
    public void addComment(String text, long id, String identfier) {
        musicservice1Bean.addComment(text, id, identfier);
    }

    /**
     *
     * @param mp3ArtistId Id des gewünschten Artists
     * @return Gibt einen Mp3Artist anhand der Artist Id wieder 
     */
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId) {
        return musicservice1Bean.getMp3ArtistByArtistId(mp3ArtistId);
    }

    /**
     *
     * @param mp3ArtistId Id des gewünschten Artists
     * @return Gibt eine Mp3 anhand der Artist Id wieder 
     */
    public List<Mp3> getMp3ByArtist(int mp3ArtistId) {
        return musicservice1Bean.getMp3ByArtist(mp3ArtistId);
    }

}
