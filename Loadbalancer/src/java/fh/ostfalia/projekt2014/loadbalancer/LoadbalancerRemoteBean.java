/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import javax.annotation.PostConstruct;

/**
 *
 * @author KingDCB
 */
public abstract class LoadbalancerRemoteBean {
    private String ip = null;
    private String port = null;
    //Objektname
    private String name = null;
    private Object object;
    

    public LoadbalancerRemoteBean(String ip, String port, String name){
        this.ip = ip;
        this.port = port;
        this.name = name;
    }
    
    public Object getObject() {
        return object;
    }
    /**
     *  initObject nutzt den RemoteBeanManager um angeforderte Objekte aufzurufen
     */
    @PostConstruct
    private void initObject(){
        this.object = LoadbalancerRemoteBeanManager.getInstance().lookupRemoteBean(name, ip, port);
    }
}
