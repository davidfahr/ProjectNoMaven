/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities;

import java.util.LinkedList;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author KingDCB
 */
public interface LoadbalancerResult {
    public LinkedList<SetOfRequests> getSetOfRequests();
}
