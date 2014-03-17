package fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities;

import java.util.LinkedList;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Interface für die Entität LoadbalancerResult
 */
public interface LoadbalancerResult {

    /**
     * Siehe Implementierung in LoadbalancerResultBean
     * @return 
     */
    public LinkedList<SetOfRequests> getSetOfRequests();
}
