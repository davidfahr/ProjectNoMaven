/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public interface LoadbalancerSimulation {
    
    public LoadbalancerResult startLoadbalancerSimulation();
    
    public LoadbalancerResult startLoadbalancerSimulationByTime(int time);
    
     
    public void stopLoadbalancerSimulation();
}
