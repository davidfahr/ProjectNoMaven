package fh.ostfalia.projekt2014.musicservice2remoteinterface.interfaces;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Remote
public interface Musicservice {

    public void whoAmI();

    public List<Mp3> getAllMp3s();

    public Mp3 getMp3(int mp3Id);

    public byte[] getMp3File(int mp3Id);

    public String getIdParameter();

    public void upload(String part);

    public void downloadMp3File(String filename, int mp3Id);

    public List<Comment> getAllArtistCommentsById(int id);

    public List<Comment> getAllMp3CommentsById(int id);

    public void addComment(String text, long id, String identfier);

    public void update(Mp3 mp3Bean);

    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId);
    
    public List<Mp3> getMp3ByArtist(int mp3ArtistId);
}
