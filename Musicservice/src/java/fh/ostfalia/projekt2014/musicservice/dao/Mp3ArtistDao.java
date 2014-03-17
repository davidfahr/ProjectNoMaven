package fh.ostfalia.projekt2014.musicservice.dao;

import fh.ostfalia.projekt2014.musicservice.entities.Mp3Bean;
import fh.ostfalia.projekt2014.musicservice.entities.Mp3ArtistBean;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/** Das Mp3ArtistDao ist für die Verwaltung der Mp3ArtistBean durch den EnititManger zuständig.
 *  Siehe auch Abschnitt 6.5.2
 * 
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Webserver->Loadbalancer->MusicserviceBean->Mp3DaoLocal->Mp3DaoImpl
 */
@Stateless
public class Mp3ArtistDao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //Initialisierung des EntitManagers
    @PersistenceContext(unitName = "MusicservicePU")
    private EntityManager em;

    /**
     * Speichern einer Mp3ArtistBean in die Datenbank.
     * @param mp3Artist Bean der in die Datenbank gespeichert werden soll
     */
    public void persistMp3Artist(Mp3ArtistBean mp3Artist) {
        em.persist(mp3Artist);
    }

    /**
     * Löschung einer Mp3 aus der Datenbank. Aktuell nicht in Benutzung
     *
     * @param mp3ArtistId
     */
    public void deleteMp3Artist(int mp3ArtistId) {
        em.remove(getMp3ArtistBean(mp3ArtistId));
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Artist ab.
     * @param artistId id vom Artist
     * @return Mp3ArtistBean mit entsprechenden Daten
     */
    public Mp3ArtistBean getMp3ArtistBean(int artistId) {
        return em.find(Mp3ArtistBean.class, artistId);
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Artistname ab und gibt diesen
     * zurück
     *
     * @param mp3Id id des Datensatzes, welcher zurückkommen soll
     * @return String mit Namen des Artists
     */
    public String getMp3ArtistNameByMp3Id(int mp3Id) {
        return em.find(Mp3Bean.class, mp3Id).getMp3ArtistBean().getArtistName();
    }

    /**
     * Fragt die Datenbank nach einem bestimmten Artistnamen ab und gibt diesen
     * zurück
     *
     * @param mp3Artist des Datensatzes, welcher zurückkommen soll
     * @return String mit Namen des Artists
     */
    public String getMp3ArtistNameByArtistBean(Mp3ArtistBean mp3Artist) {
        return mp3Artist.getArtistName();
    }

    /**
     * Fragt die Datenbank nach allen Artists ab und gibt sie zurück
     *
     * @return List<Mp3ArtistBean> Liste mit Artists
     */
    public List<Mp3ArtistBean> getAllMp3Artists() {
        return em.createNamedQuery("Mp3Artist.getAll").getResultList();
    }

    private String passedParameter;

    /**
     * Gibt die id eines GET Requests aus der Url des Browsers wieder
     *
     * @return String id
     */
    public String getPassedParameter() {
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

    /**
     * Holt die Mp3ArtistBean eines bestimmten Namens 
     * Dazu wird die NamedQuery getMp3ArtistByName, welcher in der ArtistBean 
     * steht verwendet
     * @param name des Stings welcher abgefragt werden soll
     * @return Liste mit Mp3ArtistBeans
     */
    public List<Mp3ArtistBean> getMp3ArtistByName(String name) {
        Query query = em.createNamedQuery("getMp3ArtistByName");
        query.setParameter("name", name);
        List<Mp3ArtistBean> list = query.getResultList();

        return list;
    }
    
    /**
     * Es wird überprüft ob schon ein Artist mit übergebenen Namen existiert und
     * gegebenfalls returned
     * Wenn return null ist, existiert noch kein Artist mit dem Namen
     * Es wird also nur ein Artist zurückgegeben wenn er existiert
     * 
     * @param name des Strings welcher überprüft werden soll
     * @return Mp3ArtistBean
     */
    public Mp3ArtistBean checkArtist(String name) {
        Mp3ArtistBean artistBean = null;

        List<Mp3ArtistBean> list = getMp3ArtistByName(name);
        if (list.size() > 0) {
            artistBean = list.get(0);
        }
        return artistBean;
    }
    
}
