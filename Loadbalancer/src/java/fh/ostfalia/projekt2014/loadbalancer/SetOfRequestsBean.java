/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.SetOfRequests;
import java.io.Serializable;

/**
 *
 * @author Yannick
 */
public class SetOfRequestsBean implements Serializable, SetOfRequests{
    
    private int server;
    private int amoutOfRequests;

    public SetOfRequestsBean(int server, int amoutOfRequests) {
        this.server = server;
        this.amoutOfRequests = amoutOfRequests;
    }
    
     public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public int getAmoutOfRequests() {
        return amoutOfRequests;
    }

    public void setAmoutOfRequests(int amoutOfRequests) {
        this.amoutOfRequests = amoutOfRequests;
    }
    
}
