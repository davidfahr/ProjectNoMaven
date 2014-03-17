package fh.ostfalia.projekt2014.musicservice.dao;


import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.musicservice2remoteinterface.interfaces.Musicservice;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 * Mp3DBSyncBean regelt den Zugriff auf den anderen Musicservice
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Stateless
public class Mp3DBSyncBean extends RemoteBean{
    //Kommt von musicserviceremoteinterface und bildet die Verbindung dorthin
    private Musicservice musicservice;
    
    /**
     * Ruft den Konstruktor der RemoteBean auf und setzt notwendige Parameter
     * für den lookup.
     */
    public Mp3DBSyncBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Musicservice2/Musicservice2Bean");
    }
    
    /**
     * Initialisierung der CommentserviceBean. Hier wird der eigentlich lookup
     * ausgeführt.
     */
    @PostConstruct
    public void initBean() {
        musicservice = (Musicservice) super.getObject();
    }
    
    /**
     * Aktualisiert eigene Datenbank mit entsprechender Mp3Bean. Der Update wird
     * immer vom anderen Musicservice aufgerufen.
     *
     * @param mp3Bean Mp3 Entity welche aktualisiert werden soll
     */
    public void update(Mp3 mp3Bean){
        musicservice.update(mp3Bean);
    }
    
    
    
}
