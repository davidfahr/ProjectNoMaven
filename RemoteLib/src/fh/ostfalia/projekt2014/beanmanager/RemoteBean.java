package fh.ostfalia.projekt2014.beanmanager;

import javax.annotation.PostConstruct;

/** RemoteBean
 * 
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 2.3.1
 * 
 * Aufruf von: Alle Klassen, die einen Lookup durchführen.
 * Beispiele: Webserver -> Paket: Loadbalancer -> LoadbalancerMBean, LoadbalancerSimulationMBean
 * 
 * Klasse, die von jeder RemoteManagedBean pro Service geerbet wird,
 * damit die Services auf entfernte Objekte zugreifen können.
 * Z.B. erbt die LoadbalancerMBean  die RemoteBean.
 */
public class RemoteBean { 
    private String ip = null;
    private String port = null;
    //Objektname
    private String name = null;
    private Object object;

    /**
     *
     * @param ip gibt die IP des Zielservers an 
     * @param port gibt den Port des Zielservers an
     * @param name gibt den JNDI-Namen des Zielobjektes auf dem Zielserver an
     */
    public RemoteBean(String ip, String port, String name){
        this.ip = ip;
        this.port = port;
        this.name = name;
    }
    
    /**
     *
     * @return gibt das angeforderte Objekt zurück
     */
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
