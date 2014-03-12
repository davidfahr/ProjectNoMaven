/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.LoadbalancerSimulation;
import javax.annotation.PostConstruct;


/**
 *
 * @author Yannick
 */
public class LoadbalancerSimulationMBean extends RemoteManagedBean{
    private LoadbalancerSimulation loadBalancerSimulation;
    private LoadbalancerResult loadbalancerResult;
    private int time;


   
    //Variable die zwischen manuellem starten und stoppen des LB oder per definiertem Zeitintervall unterscheidet 
    //möglich sind manuel oder timed
    private String mode;
    
    public LoadbalancerSimulationMBean() {
         super("localhost", "3700", "java:global/NewProjectNoMaven/Loadbalancer/LoadbalancerSimulationBean");
    }
   
    
     @PostConstruct
    public void initBean() {
        //Holen der entfernten LoadbalancerBean bzw. deren Stub-Objekt
       loadBalancerSimulation = (LoadbalancerSimulation) super.getObject();
    }
    public String getModePage(){
        System.out.println(mode);
        if(mode.equals("manual")){
            
            return "manual";
        }
        else if(mode.equals("timed")){
            return "timed";
        }
        else{
            System.out.println("wrong Page!");
            return null;
        }

    }
    

     
     public void startLoadbalancerSimulationByTime(){
        loadBalancerSimulation.startLoadbalancerSimulationByTime(time);
    }
    
      public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
        public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
}

