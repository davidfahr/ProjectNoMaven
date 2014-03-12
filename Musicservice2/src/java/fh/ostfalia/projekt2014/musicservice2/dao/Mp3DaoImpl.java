/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.musicservice2.dao;


import fh.ostfalia.projekt2014.musicservice2.entities.Mp3ArtistBean;
import fh.ostfalia.projekt2014.musicservice2.entities.Mp3Bean;
import fh.ostfalia.projekt2014.musicservice2.util.Id3Tag;
import fh.ostfalia.projekt2014.musicservice2remoteinterface.entities.Mp3;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
@Stateless
public class Mp3DaoImpl implements Mp3DaoLocal, Serializable {
    
    @EJB
    Mp3ArtistDao mp3ArtistDao;
    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "MusicservicePU2")
    private EntityManager em;
    private Id3Tag id3;

    public void addMp3List(ArrayList<Mp3> mp3BeanList) {
        for (int i = 0; i <= mp3BeanList.size(); i++) {
            Mp3 tempMp3Bean = mp3BeanList.get(i);
            em.persist(tempMp3Bean);
        }
    }

    /**
     * Speichern einer Mp3Bean in die Datenbank
     *
     * @param mp3Bean
     */
    public void persistMp3(Mp3Bean mp3Bean) {
        if (checkMp3(mp3Bean) == false) {
            mp3Bean = checkArtist(mp3Bean);
            em.persist(mp3Bean);
        }

    }
    
    /**
     * Überprüft ob schon einen Mp3 Eintrag mit gleichen Titel in der DB existiert
     * Dazu wird die NamedQuery getMp3ByName aus der Mp3Bean verwendet
     * Wenn keiner existiert ist der returnwert false
     * Existiert einer ist der returnwert true
     * @param mp3Bean
     * @return 
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
     * überprüfen ob es schon den gleichen Artist gibt
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

        /**
         * Wenn es schon einen Artist gibt
         */
        if (mp3Artist != null) {
            /**
             * Es gibt schon einen Artist, somit wird der schon bestehende
             * verwendet
             */
            mp3Bean.setMp3ArtistBean(mp3Artist);
        }
        return mp3Bean;
    }

    /**
     * Löschung einer Mp3 aus der Datenbank
     *
     * @param mp3_id
     */
    public void deleteMp3(int mp3_id) {
        em.remove(getMp3(mp3_id));
    }

    /**
     * Holt eine Mp3 aus der Datenbank
     *
     * @param mp3_id
     * @return Mp3Bean
     */
    @Override
    public Mp3Bean getMp3(int mp3_id) {
        return em.find(Mp3Bean.class, mp3_id);
    }

    public Mp3 getMp3ByArtist(int mp3ArtistId) {
        return em.find(Mp3.class, mp3ArtistId);
    }

    public int getMp3ArtistIdByMp3Id(int mp3Id) {
        Mp3 mp3Bean = em.find(Mp3.class, mp3Id);

        return mp3Bean.getArtistId();
    }

    public String getMp3Title(int mp3Id) {
        return em.find(Mp3.class, mp3Id).getMp3Title();
    }

    @Override
    public byte[] getMp3File(int mp3Id) {
        return em.find(Mp3Bean.class, mp3Id).getMp3File();
    }

    @Override
    public List<Mp3> getAllMp3() {
        Query queryMp3List = em.createQuery("SELECT e FROM Mp3 e");
        List<Mp3Bean> mp3BeanList = queryMp3List.getResultList();
        return new LinkedList<Mp3>(mp3BeanList);
    }

    // Methoden zum Extrahieren der Id aus der URI, um Mp3 zu identifizieren 
    private String passedParameter;

    @Override
    public String getIdParameter() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.passedParameter = (String) facesContext.getExternalContext().
                getRequestParameterMap().get("id");
        return this.passedParameter;
    }

    public void setPassedParameter(String passedParameter) {
        this.passedParameter = passedParameter;
    }

    public void addMp3(Mp3 mp3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final int DEFAULT_BUFFER_SIZE = 10240;

    /**
     * Methode welche die Download Ressource in Form eines Response wiedergibt
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
     * extrahiert) der für die id3Tag Libary gebraucht wird.
     *
     * @param part
     */
    @Override
    public void upload(String part) {
        /**
         * Initialisierung der Id3Tag Klasse zum verwenden der jid3lib Libary
         */
        id3 = new Id3Tag();

        /**
         * Die Id3Tag Klasse braucht ein File zum auslesen. Dieses wird mit
         * Hilfe der Methode getFileName und dem Parameter part (welcher aus der
         * Komponente im Webfrontend mitgeliefert wird) erstellt
         */
        File file = new File("C:\\Users\\Mettbroetchen\\Documents\\NetBeansProjects\\ProjectNoMaven\\Musicservice\\Uploads\\" + getFileName(part));

        /**
         * Initialisierung der Mp3Bean
         */
        Mp3Bean mp3Bean = new Mp3Bean();

        /**
         * Die Methode readMp3File aus der Id3Tag Klasse liest die notwendigen
         * Informationen aus der Datei aus returned diese in Form einer Mp3Bean
         */
        mp3Bean = id3.readMp3File(file);

        /**
         * Speicherung der Mp3Bean in Datenbank
         */
        this.persistMp3(mp3Bean);
    }

    /**
     * Wertet den String part aus, welcher aus dem Webfrontend mitgeschickt wird
     * Sinn ist es, den Namen der Datei herauszufinden, um diesen in der Upload
     * Methode zu verwenden
     *
     * @return
     */
    private String getFileName(String part) {
        int startPos;
        int lastPos;
        String fileName;
        /**
         * Sucht nach dem ersten "=" im String part Dies ergibt die
         * Startposition zum zuschneiden des Strings
         */
        startPos = part.indexOf("=") + 1;
        /**
         * Sucht nach dem ersten "," welches vorkommt. Dies ergibt die
         * Endposition zum zuschneiden des Strings
         */
        lastPos = part.indexOf(",");

        /**
         * Schneidet den String so zu, dass nur noch der Name der Datei bleibt
         */
        fileName = part.substring(startPos, lastPos);
        System.out.println("FILENAME: " + fileName);
        return fileName;
    }
}
