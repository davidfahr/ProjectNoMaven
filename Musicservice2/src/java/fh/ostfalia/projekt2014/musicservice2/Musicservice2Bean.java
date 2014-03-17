package fh.ostfalia.projekt2014.musicservice2;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.musicservice2.commentservice.CommentserviceBeanLocal;
import fh.ostfalia.projekt2014.musicservice2.dao.Mp3DaoLocal;
import fh.ostfalia.projekt2014.musicservice2.entities.Mp3ArtistBean;
import fh.ostfalia.projekt2014.musicservice2remoteinterface.interfaces.Musicservice;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Kommentare liegen im Musicservice 1, da gleiche Struktur.
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Stateless
public class Musicservice2Bean implements Musicservice, Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private Mp3DaoLocal mp3Dao;
    @EJB
    private CommentserviceBeanLocal commentservice;

    private List<Mp3ArtistBean> lArtists;

    @Override
    public Mp3 getMp3(int mp3_id) {
        return mp3Dao.getMp3(mp3_id);
    }

    @Override
    public List<Mp3> getAllMp3s() {
        return mp3Dao.getAllMp3();
    }

    @Override
    public byte[] getMp3File(int mp3Id) {
        return mp3Dao.getMp3File(mp3Id);
    }

    @Override
    public void upload(String path) {
        mp3Dao.upload(path);
    }

    @Override
    public String getIdParameter() {
        return mp3Dao.getIdParameter();

    }

    @Override
    public void downloadMp3File(String filename, int mp3Id) {
        mp3Dao.downloadMp3File(filename, mp3Id);
    }

    @Override
    public void whoAmI() {
        System.out.println("Ich bin Musicservice 2");
    }

    @Override
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId) {
        return mp3Dao.getMp3ArtistByArtistId(mp3ArtistId);
    }

    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        return commentservice.getAllArtistCommentsById(id);
    }

    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        return commentservice.getAllMp3CommentsById(id);
    }

    @Override
    public void addComment(String text, long id, String identfier) {
        System.out.println("MUSICSERVICE");
        System.out.println(identfier);

        commentservice.addComment(text, id, identfier);
    }

    @Override
    public void update(Mp3 mp3Bean) {
        System.out.println("M2 update(mp3Bean)");
        mp3Dao.update(mp3Bean);
    }

    @Override
    public List<Mp3> getMp3ByArtist(int mp3ArtistId) {
        return mp3Dao.getMp3ByArtist(mp3ArtistId);
    }

}
