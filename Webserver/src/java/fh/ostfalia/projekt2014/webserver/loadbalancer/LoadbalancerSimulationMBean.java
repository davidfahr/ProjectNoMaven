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
 * Siehe Abschnitt 3.3.1.2
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
        //Konfiguration
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
     * Holt dem Modus entsprechende Seite in den Browser
     * (Je nach Auswahl auf der LoadbalancerPages->index.xhtml)
     *  Manual nicht möglich, da nicht implementiert.
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
     * Entfernter Aufruf zum Starten der Loadbalancersimulation per Zeitintervall unter 
     * Nutzung des time-Parameters
     * @return
     */
    public String startLoadbalancerSimulationByTime(){
        
        loadbalancerResult = loadBalancerSimulation.startLoadbalancerSimulationByTime(time);
        
       
        
        /* Rueckgabe der Ergebnisseite mit Übersicht über die Anfrage bzw. deren Verteilung auf beide Musikdienste
        * in übersichtlicher Tabellenform 
        */
        return "timedResult";
    }

    /**
     *
     * @return Gibt das LoadbalancingResult nach der Simulation zurück an die Ergebnisseite (timedResult.xhtml)
     */
    public LoadbalancerResult getLoadbalancerResult() {
        return loadbalancerResult;
    }

    /**
     *
     * @return Liste von Anfragen an die beiden Musikdienste für die Tabelle
     * ein SetOfRequet enthält ID des Servers und Anzahl der Aufrufe
     */
    public LinkedList<SetOfRequests> getRequestList() {
        return loadbalancerResult.getSetOfRequests();
    }

    /**
     * Holt den Modus zur Weiterleitung auf die entsprechende Seite (in LoadbalancerPages->index.xhtml)
     * @return
     */
    public String getMode() {
        return mode;
    }

    /**
     * Setzt den Modus zur Weiterleitung auf die entsprechende Seite (in LoadbalancerPages->index.xhtml)
     * @param mode Modus 
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Holen der Zeit bei intervallgesteuerter Simulation 
     * @return Zeit in Sekunden
     */
    public int getTime() {
        return time;
    }

   /**
     * Setzen der Zeit bei intervallgesteuerter Simulation
     * @param time Zeit in Sekunden
     */
    public void setTime(int time) {
        this.time = time;
    }
    
}

