/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.webserver.loadbalancer;

import fh.ostfalia.projekt2014.beanmanager.RemoteBean;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult; 
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.SetOfRequests;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.LoadbalancerSimulation;
import java.util.LinkedList;
import javax.annotation.PostConstruct;


/** LoadbalancerSimulationMBean
 * 
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 
 * 
 * erbt von:  RemoteBean, damit Lookup durchgeführt werden kann.
 * Aufruf von:
 * Beispiele: 
 */
public class LoadbalancerSimulationMBean extends RemoteBean{
    private LoadbalancerSimulation loadBalancerSimulation;
    private LoadbalancerResult loadbalancerResult;
    private LinkedList<SetOfRequests> requestList;
 

    private int time;


   
    //Variable die zwischen manuellem starten und stoppen des LB oder per definiertem Zeitintervall unterscheidet 
    //möglich sind manuel oder timed
    private String mode;
    
     /**
     * Konstruktor mit IP, Port und JNDI-Namen
     * Zweck: Festlegung der Konfigurationsparameter des entfernten Objektes
     */
    public LoadbalancerSimulationMBean() {
         super("localhost", "3700", "java:global/NewProjectNoMaven/Loadbalancer/LoadbalancerSimulationBean");
    }
    
    /**
     * Anforderung und Setzen des entfernten Objektes in eine lokale Variable über die zuvor konfigurierte entfernte Bean (s. Konstruktor)
     */
    @PostConstruct
    public void initBean() {
        //Holen der entfernten LoadbalancerBean bzw. deren Stub-Objekt
       loadBalancerSimulation = (LoadbalancerSimulation) super.getObject();
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public String startLoadbalancerSimulationByTime(){
        loadbalancerResult = loadBalancerSimulation.startLoadbalancerSimulationByTime(time);
        
        return "timedResult";
    }

    /**
     *
     * @return
     */
    public LoadbalancerResult getLoadbalancerResult() {
        return loadbalancerResult;
    }

    /**
     *
     * @return
     */
    public LinkedList<SetOfRequests> getRequestList() {
        return loadbalancerResult.getSetOfRequests();
    }

    /**
     *
     * @return
     */
    public String getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     *
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }
    
}

