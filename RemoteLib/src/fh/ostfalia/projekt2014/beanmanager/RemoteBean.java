package fh.ostfalia.projekt2014.beanmanager;

import fh.ostfalia.projekt2014.beanmanager.RemoteBeanManager;
import javax.annotation.PostConstruct;


/* abstrakte Klasse, die von jeder RemoteManagedBean pro Service geerbet wird,
 * damit die Services auf ihre Objekte zugreifen können
 * Z.B. implementiert die RemoteMp3DaoBean  die RemoteManagedBean, die RemoteArtistDaoBean implementiert ebenfalls die RemoteManagedBean.
 * Diese Klasse wird von jeder Remote
 */
public class RemoteBean { 
    private String ip = null;
    private String port = null;
    //Objektname
    private String name = null;
    private Object object;
    

    public RemoteBean(String ip, String port, String name){
        this.ip = ip;
        this.port = port;
        this.name = name;
    }
    
    public Object getObject() {
        return object;
    }
    /**
     *  initObject nutzt den RemoteBeanManager um angeforderte Objekte aufzurufen
     */
    @PostConstruct
    private void initObject(){
        this.object = RemoteBeanManager.getInstance().lookupRemoteBean(name, ip, port);
    }
}
