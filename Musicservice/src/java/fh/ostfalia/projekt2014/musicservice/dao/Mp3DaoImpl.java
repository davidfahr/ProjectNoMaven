package fh.ostfalia.projekt2014.musicservice.dao;

import fh.ostfalia.projekt2014.musicservice.entities.Mp3ArtistBean;
import fh.ostfalia.projekt2014.musicservice.entities.Mp3Bean;
import fh.ostfalia.projekt2014.musicservice.util.Id3Tag;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;

/**
 * Das Mp3DaoImpl ist für die Verwaltung der Mp3Bean durch den EnititManger
 * zuständig. Siehe auch Abschnitt 6.5.2
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Webserver->Loadbalancer->MusicserviceBean->Mp3DaoLocal->Mp3DaoImpl
 */
@Stateful
public class Mp3DaoImpl implements Mp3DaoLocal, Serializable {

    private static final long serialVersionUID = 1L;
    
    //Initialisierung Mp3DBSyncBean die für de Synchronisierung des anderen Musicservices zuständig ist 
    @EJB
    private Mp3DBSyncBean mp3Sync;

    //Initialisierung des Mp3ArtistDao um ArtistBeans zu erstellen
    @EJB
    private Mp3ArtistDao mp3ArtistDao;

    //Initialisierung des EntitManagers
    @PersistenceContext(unitName = "MusicservicePU")
    private EntityManager em;

    //Id3Tag Klasse für das auslesen der Mp3 Dateien
    private Id3Tag id3;

    // Methoden zum Extrahieren der Id aus der URI, um Mp3 zu identifizieren 
    private String passedParameter;

    /**
     * Speichern einer Mp3Bean in die Datenbank. Bevor das passiert wird die
     * Bean erst noch auf Redundanz in der Datenbank kontrolliert. Nähere
     * Erläuterung dazu auch im Abschnitt 6.6 Upload in der Dokumentation.
     *
     * @param mp3Bean Bean der in die Datenbank gespeichert werden soll
     */
    public void persistMp3(Mp3Bean mp3Bean) {
        if (checkMp3(mp3Bean) == false) {
            mp3Bean = checkArtist(mp3Bean);
            em.merge(mp3Bean);
        }

    }

    /**
     * Überprüft ob schon einen Mp3 Eintrag mit gleichen Titel in der DB
     * existiert. Dazu wird die NamedQuery getMp3ByName aus der Mp3Bean
     * verwendet Wenn keiner existiert ist der returnwert false Existiert einer
     * ist der returnwert true Siehe auch Abschnitt 6.6 Upload in der
     * Dokumentation.
     *
     * @param mp3Bean Bean die überprüft werden soll
     * @return boolean false -> Es existiert keine Mp3 in der DB true -> Es
     * existiert eine Mp3 in der DB
     */
    private boolean checkMp3(Mp3Bean mp3Bean) {
        boolean existAlready = false;

        Query query = em.createNamedQuery("getMp3ByName");
        query.setParameter("name", mp3Bean.getMp3Title());
        List<Mp3Bean> list = query.getResultList();

        if (list.size() > 0) {
            existAlready = true;
        }

        return existAlready;
    }

    /**
     * Überprüft ob es schon ein Mp3Artist mit gleichen Namen existiert. Wenn
     * ja, wird kein neuer erzeugt, sondern den schon bestehenden verwendet Dazu
     * wird im Mp3ArtistDao die Methode getMp3ArtistByName benutzt, um zu
     * überprüfen ob es schon den gleichen Artist gibt. Siehe auch Abschnitt 6.6
     * Upload in der Dokumentation.
     *
     * @param mp3Bean
     * @return
     */
    public Mp3Bean checkArtist(Mp3Bean mp3Bean) {
        /**
         * Es wird überprüft ob es schon einen Artist mit gleichen Namen gibt
         * Das Ergebnis wird in eine Mp3ArtistBean gespeichert
         */
        Mp3ArtistBean mp3Artist = mp3ArtistDao.checkArtist(mp3Bean.getArtistName());

        //Wenn es schon einen Artist gibt
        if (mp3Artist != null) {

            //Es gibt schon einen Artist, somit wird der schon bestehende verwendet
            mp3Bean.setMp3ArtistBean(mp3Artist);
        }
        return mp3Bean;
    }

    /**
     * Löschung einer Mp3 aus der Datenbank. Aktuell nicht in Benutzung
     *
     * @param mp3_id
     */
    public void deleteMp3(int mp3_id) {
        em.remove(getMp3(mp3_id));
    }

    /**
     * Fragt die Datenbank nach einer bestimmten Mp3 inkl. Artist ab und gibt
     * diese zurück
     *
     * @param mp3_id id des Datensatzes, welcher zurückkommen soll
     * @return Mp3 Entity mit den Daten aus der Datenbank
     */
    @Override
    public Mp3Bean getMp3(int mp3_id) {
        return em.find(Mp3Bean.class, mp3_id);
    }

    /**
     * Fragt die Datenbank nach allen Artists einer bestimmten artist_id ab
     *
     * @param mp3ArtistId id des Datensatzes, welcher zurückkommen soll
     * @return mp3ArtistId List<Mp3> Liste mit Artists
     */
    @Override
    public List<Mp3> getMp3ByArtist(int mp3ArtistId) {
        Query queryMp3List = em.createNamedQuery("getMp3ByMp3ArtistId");
        queryMp3List.setParameter("id", mp3ArtistId);
        List<Mp3Bean> mp3BeanList = queryMp3List.getResultList();
        return new LinkedList<Mp3>(mp3BeanList);
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Artist ab und gibt diesen
     * zurück
     *
     * @param mp3Id id des Datensatzes, welcher zurückkommen soll
     * @return Mp3 Entity mit den Daten aus der Datenbank, wobei hier nur
     * Informationen vom Artist enthalten sind
     */
    public int getMp3ArtistIdByMp3Id(int mp3Id) {
        Mp3 mp3Bean = em.find(Mp3.class, mp3Id);

        return mp3Bean.getArtistId();
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Artist ab und gibt diesen
     * zurück
     *
     * @param mp3ArtistId id des Datensatzes, welcher zurückkommen soll
     * @return Mp3 Entity mit den Daten aus der Datenbank, wobei hier nur
     * Informationen vom Artist enthalten sind
     */
    @Override
    public Mp3 getMp3ArtistByArtistId(int mp3ArtistId) {
        Mp3Bean mp3Bean = new Mp3Bean();
        mp3Bean.setMp3ArtistBean(mp3ArtistDao.getMp3ArtistBean(mp3ArtistId));
        return mp3Bean;
    }

    /**
     * Fragt den Titel einer bestimmten Mp3 Datei ab und gibt sie zurück
     *
     * @param mp3Id id welcher Name zurück geben werden soll
     * @return String mit dem Titel
     */
    public String getMp3Title(int mp3Id) {
        return em.find(Mp3.class, mp3Id).getMp3Title();
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Mp3File ab
     *
     * @param mp3Id id vom File welches zurück gegeben werden soll
     * @return byte[] Byte Array
     */
    @Override
    public byte[] getMp3File(int mp3Id) {
        return em.find(Mp3Bean.class, mp3Id).getMp3File();
    }

    /**
     * Fragt die Datenbank nach allen Mp3s inkls Artist ab und gibt sie zurück
     *
     * @return List<Mp3> Liste mit Mp3 Entitys
     */
    @Override
    public List<Mp3> getAllMp3() {
        Query queryMp3List = em.createQuery("SELECT e FROM Mp3 e");
        List<Mp3Bean> mp3BeanList = queryMp3List.getResultList();
        return new LinkedList<Mp3>(mp3BeanList);
    }

    /**
     * Gibt die id eines GET Requests aus der Url des Browsers wieder
     *
     * @return String id
     */
    @Override
    public String getIdParameter() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.passedParameter = (String) facesContext.getExternalContext().
                getRequestParameterMap().get("id");
        return this.passedParameter;
    }

    /**
     *
     * @param passedParameter
     */
    public void setPassedParameter(String passedParameter) {
        this.passedParameter = passedParameter;
    }

    private static final int DEFAULT_BUFFER_SIZE = 10240;

    /**
     * Methode welche die Download Ressource in Form eines Response wiedergibt.
     * Siehe auch 6.7 Download der Dokumentation
     *
     * @param name Name des Mp3Files
     * @param mp3Id Die Id der Mp3 Datei
     */
    @Override
    public void downloadMp3File(String name, int mp3Id) {
        try {
            /**
             * Das Mp3File wird aus der Datenbank geladen
             */
            byte[] mp3File = this.getMp3File(mp3Id);
            /**
             * An dem Namen wird zusätzlich mp3 als Datentp hinzugefügt
             */
            String filename = name + ".mp3";
            /**
             * Initialisierung eines FacesContext
             */
            FacesContext context = FacesContext.getCurrentInstance();
            /**
             * Initialisierung eines HttpRespons
             */
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            /**
             * Response bekommt notwendige Attribute
             */
            response.reset();
            response.setBufferSize(DEFAULT_BUFFER_SIZE);
            response.setContentType("audio/mpeg");
            response.setHeader("Content-Length", String.valueOf(mp3File.length));
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            /**
             * Hier wird das Mp3File hinzugefügt
             */
            response.getOutputStream().write(mp3File);

            /**
             * Response wird dem Context hinzugefügt
             */
            context.responseComplete();
        } catch (IOException ex) {
            Logger.getLogger(Mp3DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ausführen eines Uploads Diese Methode wird aus dem Webfrontend
     * aufgerufen. Es wird die Jid3lib Libary zum auslesen der Informationen
     * einer Mp3 Datei verwendet. Der Titel und der Artist werden ausgelesen und
     * in die Datenbank gespeichert. Der Parameter part enthält unter anderem
     * den Namen der Datei (der Name wird mit Hilfe der Methode getFileName
     * extrahiert) der für die id3Tag Libary gebraucht wird. Siehe auch 6.6
     * Upload der Dokumentation
     *
     * @param path Dateipfad der Datei
     */
    @Override
    public void upload(String path) {
        /**
         * Initialisierung der Id3Tag Klasse zum verwenden der jid3lib Libary
         */
        id3 = new Id3Tag();

        /**
         * Die Id3Tag Klasse braucht ein File zum auslesen. Dieses wird mit
         * Hilfe der Methode getFileName und dem Parameter part (welcher aus der
         * Komponente im Webfrontend mitgeliefert wird) erstellt
         */
        File file = new File(path);

        /**
         * Initialisierung der Mp3Bean
         */
        Mp3Bean mp3Bean;

        /**
         * Die Methode readMp3File aus der Id3Tag Klasse liest die notwendigen
         * Informationen aus der Datei aus returned diese in Form einer Mp3Bean
         */
        mp3Bean = id3.readMp3File(file);

        /**
         * Speicherung der Mp3Bean in Datenbank
         */
        this.persistMp3(mp3Bean);
        /*
         1. Benachrichtigung über Änderung --> notifyOtherMusicservice()
         2. Aufruf der anderen Upload-Methode (des anderen Musikdienstes)
         3. Anderer Musikdienst ruft persisMp3 auf
         */
        mp3Sync.update(mp3Bean);
    }

    /**
     * Ruft die update-Methode auf dem Musicservice auf, der die Daten in seiner
     * eigenen Daten auf den aktuellen Stand synchronisiert. Siehe auch 6.6
     * Upload der Dokumentation
     *
     * @param mp3 Mp3 die aktualisiert werdens soll.
     */
    @Override
    public void update(Mp3 mp3) {
        Mp3Bean mp3Bean = new Mp3Bean();

        mp3Bean.setMp3File(mp3.getMp3File());
        mp3Bean.setMp3Id(mp3.getMp3Id());
        mp3Bean.setMp3Title(mp3.getMp3Title());
        Mp3ArtistBean mp3Artist = new Mp3ArtistBean();
        mp3Artist.setArtistId(mp3.getArtistId());
        mp3Artist.setArtistName(mp3.getArtistName());
        mp3Bean.setMp3ArtistBean(mp3Artist);

        if (checkMp3(mp3Bean) == false) {
            mp3Bean = checkArtist(mp3Bean);
            em.merge(mp3Bean);
        }

    }
}
