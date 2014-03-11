/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

/**
 *
 * @author Yannick
 */
public class SetOfRequests {
    private int serverId;
    private int amoutOfRequests;

    public SetOfRequests(int serverId, int amoutOfRequests) {
        this.serverId = serverId;
        this.amoutOfRequests = amoutOfRequests;
    }
    
    
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getAmoutOfRequests() {
        return amoutOfRequests;
    }

    public void setAmoutOfRequests(int amoutOfRequests) {
        this.amoutOfRequests = amoutOfRequests;
    }
    
}
