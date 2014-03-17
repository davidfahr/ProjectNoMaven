package fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Interface für LoadbalancerBean für den Remotezugriff
 */
@Remote
public interface Loadbalancer {

    /**
     * siehe Implementierung in LoadbalancerBean
     * @return
     */
    public List<Mp3> getAllMp3s();

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param mp3Id
     * @return
     */
    public Mp3 getMp3(int mp3Id);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param mp3Id
     * @return
     */
    public byte[] getMp3File(int mp3Id);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @return
     */
    public String getIdParameter();

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param path
     */
    public void upload(String path);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param filename
     * @param mp3Id
     */
    public void downloadMp3File(String filename, int mp3Id);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param id
     * @return
     */
    public List<Comment> getAllArtistCommentsById(int id);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param id
     * @return
     */
    public List<Comment> getAllMp3CommentsById(int id);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param text
     * @param id
     * @param identfier
     */
    public void addComment(String text, long id, String identfier);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param mp3ArtistId
     * @return
     */
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId);

    /**
     * siehe Implementierung in LoadbalancerBean
     * @param mp3ArtistId
     * @return
     */
    public List<Mp3> getMp3ByArtist(int mp3ArtistId);

}
