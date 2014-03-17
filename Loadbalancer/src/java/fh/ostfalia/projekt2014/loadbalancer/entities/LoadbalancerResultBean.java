package fh.ostfalia.projekt2014.loadbalancer.entities;

import fh.ostfalia.projekt2014.loadbalancer.SetOfRequestsBean;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.SetOfRequests;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Speichert die Ergebnisse der Simulation
 */
public class LoadbalancerResultBean implements LoadbalancerResult, Serializable {

    private static final long serialVersionUID = 1L;
    //enthält ServerId + Anzahl der Aufrufe auf diese ID
    private LinkedList<SetOfRequestsBean> setOfRequests;

    /**
     * Konstruktor der LoadbalancerResultBean
     * @param setOfRequests Liste mit den Ergebnissen der Simulation
     */
    public LoadbalancerResultBean(LinkedList<SetOfRequestsBean> setOfRequests) {
        this.setOfRequests = setOfRequests;
    }

    /**
     *
     * @return Gibt Liste mit den Ergebnissen der Simulation zurück
     */
    @Override
    public LinkedList<SetOfRequests> getSetOfRequests() {
        return new LinkedList<SetOfRequests>(setOfRequests);
    }

    /**
     * Setzt die Liste mit den Ergebnissen der Simulation
     * @param setOfRequests
     */
    public void setSetOfRequests(LinkedList<SetOfRequestsBean> setOfRequests) {
        this.setOfRequests = setOfRequests;
    }

}
