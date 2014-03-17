package fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces;

import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import javax.ejb.Remote;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * Interface für LoadbalancerSimulationBean für den Remotezugriff
 */
@Remote
public interface LoadbalancerSimulation {
    
    /**
     * siehe Implementierung in LoadbalancerSimulationBean
     * @return
     */
    public LoadbalancerResult startLoadbalancerSimulation();
    
    /**
     * siehe Implementierung in LoadbalancerSimulationBean
     * @param time
     * @return
     */
    public LoadbalancerResult startLoadbalancerSimulationByTime(int time);

    /**
     * siehe Implementierung in LoadbalancerSimulationBean
     */
    public void stopLoadbalancerSimulation();
}
