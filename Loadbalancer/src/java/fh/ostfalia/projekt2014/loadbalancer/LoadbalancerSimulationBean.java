/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.loadbalancer.entities.LoadbalancerResultBean;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.LoadbalancerSimulation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yannick
 */
@Stateless
public class LoadbalancerSimulationBean implements LoadbalancerSimulation, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int requests;
    private LoadbalancerResult loadbalancerResult;
    
    @EJB
    private Musicservice1Remote m1;
    @EJB
    private Musicservice2Remote m2;

    private boolean status = true;
    int time;
    
    public LoadbalancerSimulationBean() {
        //Zufallsmethode
        
        System.out.println("ENDELookup");
    }
    
      @PostConstruct
    public void initBean() {
        //Holen der entfernten Loginbean bzw. deren Stub-Objekt
        
    }
    
    @Override
    public LoadbalancerResult startLoadbalancerSimulation() {
        //Starte das Loadbalancing und speichere das Ergebnis in asyncresult

        while (status == true) {
            //loadbalancerResult = runSimulation();
        }


        return loadbalancerResult;
    }

    /* Startet die Loadbalancingsimulation
     * 
     */
    public SetOfRequestsBean runSimulation() {
        
        System.out.println(status);
        //generiere einen Zufallswert für die Anzahl der Anfragen an den ersten Zielserver
        int zaehler = 0;
        //Wähle den nächsten Server:
        if (status) {
            
            for(int i=0;i<(int) ((Math.random() * 20) + 1);i++){
                m1.whoAmI();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoadbalancerSimulationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                zaehler++;
               
            }
             System.out.println("Aufrufe: " + zaehler);
             status = false;
             //M1 und Anzahl der Aufrufe wird der Liste hinzugefuegt
            return new SetOfRequestsBean(1,zaehler);
        } else {
         
            for(int i=0;i<(int) ((Math.random() * 20) + 1);i++){
                m2.whoAmI();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoadbalancerSimulationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                zaehler++; 
            }
            //M1 und Anzahl der Aufrufe wird der Liste hinzugefuegt
            
            System.out.println("Aufrufe: " + zaehler);
            status = true;
            return new SetOfRequestsBean(2,zaehler);
        }
    }


    @Override
    public LoadbalancerResult startLoadbalancerSimulationByTime(int time) {
        //übergebene Zeit
        System.out.println("Zeit:" + time);
        LinkedList <SetOfRequestsBean> requestList = new LinkedList<SetOfRequestsBean>();
        for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(time); stop > System.nanoTime();) {
            
            //Differenz, bis Zeitintervall abgelaufen ist
            System.out.println("Differenz:");
            System.out.println(System.nanoTime() - stop);

            //Starte Simulation
            requestList.add(runSimulation());

        }
        System.out.println("Zeit abgelaufen");
        
        return new LoadbalancerResultBean(requestList);
    }

    @Override
    public void stopLoadbalancerSimulation() {

        status = false;
        System.out.println("stoppe Simlation");
        System.out.println(status);

        //Versuche zu unterbrechen
    }


}
