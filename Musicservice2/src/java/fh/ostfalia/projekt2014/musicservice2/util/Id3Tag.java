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
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
public class Id3Tag implements Serializable {

    private static final long serialVersionUID = 1L;
    private MP3File mp3file;
    private File uploadDir;
    private Mp3Bean mp3;
    Mp3ArtistBean mp3Artist;

    /**
     * Standartkonstruktor
     */
    public Id3Tag() {
        mp3 = new Mp3Bean();
        mp3Artist = new Mp3ArtistBean();
    }

    /**
     * Wird nicht benutzt
     *
     * @param customUploadPath
     */
    public Id3Tag(String customUploadPath) {
        mp3 = new Mp3Bean();
        mp3Artist = new Mp3ArtistBean();
        uploadDir = new File(customUploadPath);
    }

    /**
     * Benutzt die jid3lib und liest die Artist aus einer mp3 Datei aus.
     *
     * @param file File welches ausgelesen werden soll
     * @return String mit dem Namen des Artists
     * @throws IOException
     */
    private String readArtist(File file) throws IOException {
        try {
            mp3file = new MP3File(file);

        } catch (TagException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3file.getID3v1Tag().getArtist();
    }

    /**
     * Benutzt die jid3lib und liest die Titel aus einer mp3 Datei aus.
     *
     * @param file File welches ausgelesen werden soll
     * @return String mit dem Namen des Titels
     * @throws IOException
     */
    private String readTitle(File file) throws IOException {
        try {
            mp3file = new MP3File(file);
        } catch (TagException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3file.getID3v1Tag().getSongTitle();
    }

    /**
     * Liest geforderter Informationen aus einem File aus speichert und
     * speichert diese in einer Bean
     *
     * @param file File welches ausgelesen werden soll
     * @return Mp3Bean gefüllt mit Daten
     */
    public Mp3Bean readMp3File(File file) {
        try {
            //Setzt den Artist Namen
            mp3Artist.setArtistName(this.readArtist(file));
            //Setzt den Titel
            mp3.setMp3Title(this.readTitle(file));
            //Fügt der Mp3Bean die ArtistBean hinzu
            mp3.setMp3ArtistBean(mp3Artist);
            ////Setzt das File
            mp3.setMp3ByteCodeFromFile(file);
        } catch (IOException ex) {
            Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3;

    }

    /**
     * Eigentlich zum Initialisieren von Mp3 Files beim Deployen. Ist aber nicht
     * in Benutzung.
     *
     * @param f
     * @param readFiles
     * @return
     */
    public ArrayList<Mp3Bean> initFiles(File f, int readFiles) {

        ArrayList<Mp3Bean> list = new ArrayList();
        File[] fileList = f.listFiles();

        for (int i = 0; i <= readFiles - 1; i++) {
            File file = fileList[i].getAbsoluteFile();
            if (file.isFile()) {
                try {
                    Mp3ArtistBean mp3artist = new Mp3ArtistBean();
                    mp3artist.setArtistName(this.readArtist(file));
                    FileInputStream input = new FileInputStream(file);
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = input.read(bytes)) != -1) {
                        input.read(bytes, 0, read);
                    }

                    mp3.setMp3File(bytes);
                    mp3.setMp3Title(this.readTitle(file));
                    mp3Artist.setArtistName(this.readArtist(file));
                    mp3.setMp3ArtistBean(mp3Artist);
                    list.add(mp3);
                } catch (IOException ex) {
                    Logger.getLogger(Id3Tag.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
}
