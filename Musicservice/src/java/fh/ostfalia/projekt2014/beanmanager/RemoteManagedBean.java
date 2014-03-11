package fh.ostfalia.projekt2014.beanmanager;

import fh.ostfalia.projekt2014.beanmanager.RemoteBeanManager;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;


/* abstrakte Klasse, die von jeder RemoteManagedBean pro Service geerbet wird,
 * damit die Services auf ihre Objekte zugreifen können
 * Z.B. implementiert die RemoteMp3DaoBean  die RemoteManagedBean, die RemoteArtistDaoBean implementiert ebenfalls die RemoteManagedBean.
 * Diese Klasse wird von jeder Remote
 */
public abstract class RemoteManagedBean { 
    private String ip = null;
    private String port = null;
    //Objektname
    private String name = null;
    private Object object;
  

    public RemoteManagedBean(String ip, String port, String name){
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
        System.out.println("Lookup:");
        this.object = RemoteBeanManager.getInstance().lookupRemoteBean(name, ip, port);
        System.out.println(this.object);
    }
}
