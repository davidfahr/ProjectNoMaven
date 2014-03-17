package fh.ostfalia.projekt2014.musicservice2.dao;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.musicserviceentities.Mp3;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 */
@Stateless
public class Mp3DBSyncBean extends RemoteBean {

    private Musicservice musicservice;

    public Mp3DBSyncBean() {
        super("localhost", "3700", "java:global/NewProjectNoMaven/Musicservice/MusicserviceBean");
    }

    @PostConstruct
    public void initBean() {
        musicservice = (Musicservice) super.getObject();
    }

    public void update(Mp3 mp3Bean) {
        musicservice.update(mp3Bean);
    }
}
