/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces;

import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import javax.ejb.Remote;

/**
 *
 * @author Yannick
 */
@Remote
public interface Loadbalancer {
    
    
    public LoadbalancerResult startLoadbalancerSimulation();
    
    public LoadbalancerResult startLoadbalancerSimulationByTime(int time);
    
     
    public void stopLoadbalancerSimulation();

}
