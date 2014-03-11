/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer.entities;

import fh.ostfalia.projekt2014.loadbalancer.SetOfRequests;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import java.util.List;


/**
 *
 * @author KingDCB
 */
public class LoadbalancerResultBean implements LoadbalancerResult{
 
   //enthält ServerId + Anzahl der Aufrufe auf diese ID
    private List<SetOfRequests> requestList;

    public LoadbalancerResultBean(List<SetOfRequests> requestList) {
        this.requestList = requestList;
    }
   
 

      
}
