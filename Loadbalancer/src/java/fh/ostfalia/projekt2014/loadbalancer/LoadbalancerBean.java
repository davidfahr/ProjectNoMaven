package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.commentserviceremoteinterfaces.entities.Comment;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * LoadbalancerBean als Remotezugriff auf den Loadbalancer 
 * mehr Information siehe Systemdokumentation Kapitel 5
 * Aufruf: Webserver(LoadbalancerMBean) -> LoadbalancerBean
 */
@Stateless
public class LoadbalancerBean implements Serializable, Loadbalancer {

    private static final long serialVersionUID = 1L;
    /*
     * Hier werden die nötigen EJBs Musiservice1Remote und Musicservice2Remote geladen
     */
    @EJB
    private Musicservice1Remote m1;
    @EJB
    private Musicservice2Remote m2;

    /**
     * Boolean zur Bestimmung des angesprochenen Server true = Methoden werden
     * zu Musicservice 1 umgeleitet false = Methoden werden zu Musicservice 2
     * umgeleitet
     */
    private boolean switchServer = true;

    /*
     * maxRequests wird hier per Zufallsgenerator erzeugt
     * maxRequests kann nach diesem Generator zwischen 1 und 20 liegen
     * Wenn maxRequests erreicht ist, wird die nächste Anfrage auf den anderen Server weitergeletitet
     * Dies wird durch die Methode assignMusicservice() geschaltet.
     * 
     */
    private int maxRequests = (int) ((Math.random() * 20) + 1);
    /**
     * Speichert die Nummer des aktuellen Aufrufes
     */
    private int requests;

    /**
     * Algorythmus zum Zuweisen des Musikdienstes für weitere Information siehe
     * Systemdokumentation 5.4.2
     */
    private void assignMusicservice() {
        System.out.println("aktueller Request: " + requests);
        System.out.println("Zufallszahl: " + maxRequests);
        if (requests > maxRequests) {
            /**
             * Neue Zufallsnummer der maximalen Aufrufe generieren
             */
            maxRequests = (int) ((Math.random() * 20) + 1);
            requests = 1;
            /**
             * Prüfen ob schon ein lookup gemacht wurde Wenn nicht, dann lookup
             * ansonsten tue nichts
             */
            if (switchServer == true) {
                switchServer = false;
            } else {
                switchServer = true;
            }
        } else {
            requests++;
        }
    }

   /**
    * Folgende Methoden sind alle auf dem Webserver verfügbaren Aufrufe, die
    * an entweder Musicservice 1 oder Musicservice 2 weitergeleitet werden
    * je nachdem, was in assignMusicserver als Ergebnis entschieden wurde
    */
    
    /**
     *
     * @return Gibt eine Liste mit allen Mp3s zurück
     */
    @Override
    public List<Mp3> getAllMp3s() {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getAllMp3s();
        } else {
            return m2.getAllMp3s();
        }
    }

    /**
     *
     * @param mp3Id Id der gewünschten Mp3
     * @return Gibt eine Mp3 zurück anhand ihrer Id
     */
    @Override
    public Mp3 getMp3(int mp3Id) {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getMp3(mp3Id);
        } else {
            return m2.getMp3(mp3Id);
        }
    }

    /**
     *
     * @param mp3Id Id der gewünschten Mp3
     * @return Gibt eine Mp3-Datei zurück
     */
    @Override
    public byte[] getMp3File(int mp3Id) {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getMp3File(mp3Id);
        } else {
            return m2.getMp3File(mp3Id);
        }
    }

    /**
     *
     * @return Gibt Id der aufgerufenen Seite aus der URL wieder
     */
    @Override
    public String getIdParameter() {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getIdParameter();
        } else {
            return m2.getIdParameter();
        }
    }

    /**
     *
     * @param path Übergibt den Pfad der Datei, die hochgeladen werden soll
     */
    @Override
    public void upload(String path) {
        assignMusicservice();
        if (switchServer == true) {
            m1.upload(path);
        } else {
            m2.upload(path);
        }
    }

    /**
     *
     * @param filename Name der Datei
     * @param mp3Id Id der gewünschten Mp3
     */
    @Override
    public void downloadMp3File(String filename, int mp3Id) {
        assignMusicservice();
        if (switchServer == true) {
            m1.downloadMp3File(filename, mp3Id);
        } else {
            m2.downloadMp3File(filename, mp3Id);
        }
    }

    /**
     *
     * @param id Id des gewünschten Artists
     * @return Gibt Liste mit allen Kommentare eines Artist
     */
    @Override
    public List<Comment> getAllArtistCommentsById(int id) {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getAllArtistCommentsById(id);
        } else {
            return m2.getAllArtistCommentsById(id);
        }
    }

    /**
     *
     * @param id Id der gewünschten Mp3
     * @return Gibt Liste mit allen Kommentare einer Mp3
     */
    @Override
    public List<Comment> getAllMp3CommentsById(int id) {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getAllMp3CommentsById(id);
        } else {
            return m1.getAllMp3CommentsById(id);
        }
    }

    /**
     *
     * @param text Text des Kommentares
     * @param id Id des gewünschten Artists oder Mp3
     * @param identfier Unterscheidet zwischen Mp3 und Artist Kommentar
     */
    @Override
    public void addComment(String text, long id, String identfier) {
        assignMusicservice();
        if (switchServer == true) {
            m1.addComment(text, id, identfier);
        } else {
            m1.addComment(text, id, identfier);
        }
    }

    /**
     *
     * @param mp3ArtistId Id des gewünschten Artists
     * @return Gibt einen Mp3Artist anhand der Artist Id wieder 
     */
    @Override
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId) {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getMp3ArtistByArtistId(mp3ArtistId);
        } else {
            return m2.getMp3ArtistByArtistId(mp3ArtistId);
        }
    }

    /**
     *
     * @param mp3ArtistId Id des gewünschten Artists
     * @return Gibt eine Mp3 anhand der Artist Id wieder 
     */
    @Override
    public List<Mp3> getMp3ByArtist(int mp3ArtistId) {
        assignMusicservice();
        if (switchServer == true) {
            return m1.getMp3ByArtist(mp3ArtistId);
        } else {
            return m2.getMp3ByArtist(mp3ArtistId);
        }
    }
}
