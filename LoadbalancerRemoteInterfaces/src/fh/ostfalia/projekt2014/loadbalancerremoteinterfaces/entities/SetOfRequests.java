package fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Interface für die Entität SetOfRequests
 */
public interface SetOfRequests {

    /**
     * Siehe Implementierung in SetOfRequestsBean
     * @return
     */
    public int getServer();

    /**
     * Siehe Implementierung in SetOfRequestsBean
     * @return
     */
    public int getAmoutOfRequests();
}