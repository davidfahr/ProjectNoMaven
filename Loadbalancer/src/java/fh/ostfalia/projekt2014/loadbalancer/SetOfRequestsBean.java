package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.SetOfRequests;
import java.io.Serializable;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Speichert die Server-Id und die Anzahl der darauf ausgeführten Aufrufe
 */
public class SetOfRequestsBean implements Serializable, SetOfRequests{
    
    private int server;
    private int amoutOfRequests;

    /**
     * 
     * @param server Server Id: 1 = Musicservice1 2=Musicservice2
     * @param amoutOfRequests Anzahl der Aufrufe
     */
    public SetOfRequestsBean(int server, int amoutOfRequests) {
        this.server = server;
        this.amoutOfRequests = amoutOfRequests;
    }

    /**
     *
     * @return Gibt den anzusteuernden Server zurück
     */
    @Override
    public int getServer() {
        return server;
    }

    /**
     * Setzt den Server fest
     * @param server
     */
    public void setServer(int server) {
        this.server = server;
    }

    /**
     *
     * @return Gibt Anzahl der Aufrufe zurück
     */
    @Override
    public int getAmoutOfRequests() {
        return amoutOfRequests;
    }

    /**
     * Legt Anzahl der Aufrufe fest
     * @param amoutOfRequests
     */
    public void setAmoutOfRequests(int amoutOfRequests) {
        this.amoutOfRequests = amoutOfRequests;
    }
    
}
