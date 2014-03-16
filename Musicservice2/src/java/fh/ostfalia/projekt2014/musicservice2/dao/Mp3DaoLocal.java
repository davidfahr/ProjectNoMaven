/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice2.dao;



import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.util.List;
import javax.ejb.Local;

@Local
public interface Mp3DaoLocal {
    public List<Mp3> getAllMp3();
    public Mp3 getMp3(int id);
    public void upload(String part);
    public String getIdParameter();
    public byte[] getMp3File(int mp3Id);
    public void downloadMp3File(String filename, int mp3Id);
    public void update(Mp3 mp3);
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId);
    public List<Mp3> getMp3ByArtist(int mp3ArtistId);
}
