/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice2.util;

import fh.ostfalia.projekt2014.musicservice2.entities.Mp3Bean;
import fh.ostfalia.projekt2014.musicservice2.entities.Mp3ArtistBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;




/**
 *
 * @author Mettbrötchen
 */
public class Id3Tag implements Serializable{
    private static final long serialVersionUID = 1L;
    private MP3File mp3file;
    private final File uploadDir;
    private Mp3Bean mp3;
    Mp3ArtistBean mp3Artist;

    public Id3Tag() {
        mp3 = new Mp3Bean();
        mp3Artist = new Mp3ArtistBean();
        uploadDir = new File("C:\\Users\\Yannick\\Documents\\GitHub\\ProjectNoMaven\\Musicservice2\\Uploads\\");
    }

    public Id3Tag(String customUploadPath) {
        mp3 = new Mp3Bean();
        mp3Artist = new Mp3ArtistBean();
        uploadDir = new File(customUploadPath);
    }

    private String readArtist(File file) throws IOException {
        try {
            mp3file = new MP3File(file);
            
        } catch (TagException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3file.getID3v1Tag().getArtist();
    }

    private String readTitle(File file) throws IOException {
        try {
            mp3file = new MP3File(file);
        } catch (TagException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3file.getID3v1Tag().getSongTitle();
    }
    
    public Mp3Bean readMp3File(File file){
        try {
            

            mp3Artist.setArtistName(this.readArtist(file));
            mp3.setMp3Title(this.readTitle(file));
            mp3.setMp3ArtistBean(mp3Artist);
            mp3.setMp3ByteCodeFromFile(file);

        } catch (IOException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return mp3;
        
    }

    public ArrayList<Mp3Bean> initFiles(int readFiles) {

        ArrayList<Mp3Bean> list = new ArrayList();
        File[] fileList = uploadDir.listFiles();

        for (int i = 0; i <= readFiles - 1; i++) {
            File file = fileList[i].getAbsoluteFile();
            if (file.isFile()) {
                try {
                    Mp3Bean mp3 = new Mp3Bean();
                    Mp3ArtistBean mp3artist = new Mp3ArtistBean();

                    mp3artist.setArtistName(this.readArtist(file));
                    
                    FileInputStream input = new FileInputStream(file);
                    //OutputStream output = new FileOutputStream(file);
                    int read = 0;
                    byte[] bytes = new byte[1024];
                   
                    while ((read = input.read(bytes)) != -1) {
                        input.read(bytes, 0, read);
                    }

                    mp3.setMp3File(bytes);
                            
                    //mp3.setMp3_artist(mp3artist);
                    mp3.setMp3Title(this.readTitle(file));
                    list.add(mp3);
                } catch (IOException ex) {
                    Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
}
