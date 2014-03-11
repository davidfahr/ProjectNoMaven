/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteManagedBean;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import javax.annotation.PostConstruct;


/**
 *
 * @author Yannick
 */
public class LoadbalancerMBean extends RemoteManagedBean{
    private Loadbalancer loadbalancerBean;
    private LoadbalancerResult loadbalancerResult;
    private int time;


   
    //Variable die zwischen manuellem starten und stoppen des LB oder per definiertem Zeitintervall unterscheidet 
    //möglich sind manuel oder timed
    private String mode;
    
    public LoadbalancerMBean() {
         super("localhost", "3700", "java:global/NewProjectNoMaven/Loadbalancer/LoadbalancerBean");
    }
   
    
     @PostConstruct
    public void initBean() {
        //Holen der entfernten LoadbalancerBean bzw. deren Stub-Objekt
       loadbalancerBean = (Loadbalancer) super.getObject();
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
        loadbalancerResult = loadbalancerBean.startLoadbalancerSimulationByTime(time);
    }
    
    public void startLoadbalancerSimulation(){
        loadbalancerResult = loadbalancerBean.startLoadbalancerSimulation();
        
    }
    
    public void stopLoadbalancerSimulation(){
        loadbalancerBean.stopLoadbalancerSimulation();
       
    }
     
     public void startLoadbalancerSimulationByTime(int seconds){
        
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

