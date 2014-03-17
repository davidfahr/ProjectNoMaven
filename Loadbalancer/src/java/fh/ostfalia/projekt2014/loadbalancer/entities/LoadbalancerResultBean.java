/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer.entities;

import fh.ostfalia.projekt2014.loadbalancer.SetOfRequestsBean;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.SetOfRequests;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author KingDCB
 */
public class LoadbalancerResultBean implements LoadbalancerResult, Serializable{
     
    private static final long serialVersionUID = 1L;
    //enthält ServerId + Anzahl der Aufrufe auf diese ID
    private LinkedList<SetOfRequestsBean> setOfRequests;
    
    public LoadbalancerResultBean(LinkedList<SetOfRequestsBean> setOfRequests) {
        this.setOfRequests = setOfRequests;
    }
     @Override
     public LinkedList<SetOfRequests> getSetOfRequests() {
        return new LinkedList<SetOfRequests>(setOfRequests);
    }

    public void setSetOfRequests(LinkedList<SetOfRequestsBean> setOfRequests) {
        this.setOfRequests = setOfRequests;
    }
    


   
}
