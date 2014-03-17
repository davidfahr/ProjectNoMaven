package fh.ostfalia.projekt2014.loadbalancer.remote;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicservice2remoteinterface.interfaces.Musicservice;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
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
 * 
 * Da es sich hier um fast identischen Code wie bei Musicservice1Remote handelt,
 * wurde hier auf explizite Kommentare verzichtet.
 * Siehe Musicservice1Remote
 */
@Stateless
public class Musicservice2Remote extends RemoteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Musicservice musicservice2Bean;

    public Musicservice2Remote() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Musicservice2/Musicservice2Bean");
        System.out.println("ENDELookup --> Musikdienst 2");
    }

    @PostConstruct
    public void initBean() {
        musicservice2Bean = (Musicservice) super.getObject();
    }

    public Musicservice getMusicservice1Bean() {
        return musicservice2Bean;
    }

    public void setMusicservice1Bean(Musicservice musicservice1Bean) {
        this.musicservice2Bean = musicservice1Bean;
    }

    public void whoAmI() {
        musicservice2Bean.whoAmI();
    }

    public List<Mp3> getAllMp3s() {
        return musicservice2Bean.getAllMp3s();
    }

    public Mp3 getMp3(int mp3Id) {
        return musicservice2Bean.getMp3(mp3Id);
    }

    public byte[] getMp3File(int mp3Id) {
        return musicservice2Bean.getMp3File(mp3Id);
    }

    public String getIdParameter() {
        return musicservice2Bean.getIdParameter();
    }

    public void upload(String part) {
        musicservice2Bean.upload(part);
    }

    public void downloadMp3File(String filename, int mp3Id) {
        musicservice2Bean.downloadMp3File(filename, mp3Id);
    }

    public List<Comment> getAllArtistCommentsById(int id) {
        return musicservice2Bean.getAllArtistCommentsById(id);
    }

    public List<Comment> getAllMp3CommentsById(int id) {
        return musicservice2Bean.getAllMp3CommentsById(id);
    }

    public void addComment(String text, long id, String identfier) {
        musicservice2Bean.addComment(text, id, identfier);
    }

    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId) {
        return musicservice2Bean.getMp3ArtistByArtistId(mp3ArtistId);
    }

    public List<Mp3> getMp3ByArtist(int mp3ArtistId) {
        return musicservice2Bean.getMp3ByArtist(mp3ArtistId);
    }

}
