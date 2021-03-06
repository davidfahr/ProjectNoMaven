/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.loadbalancer.entities.LoadbalancerResultBean;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.Loadbalancer;
import fh.ostfalia.projekt2014.musicserviceremoteinterface.interfaces.Musicservice;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;

/**
 *
 * @author Yannick
 */
@Stateless
public class LoadbalancerBean implements Loadbalancer {

    private int requests;
    private Musicservice targetMusicService;
    private LoadbalancerResult lbR;
    private Musicservice1Remote m1;
    private Musicservice2Remote m2;
    private boolean status = true;
    int time;

    @Override
    public LoadbalancerResult startLoadbalancerSimulation() {
        //Starte das Loadbalancing und speichere das Ergebnis in asyncresult

        while (status == true) {
            lbR = runSimulation();
        }


        return lbR;
    }

    /* Startet die Loadbalancingsimulation
     * 
     */
    public LoadbalancerResult runSimulation() {

        System.out.println(status);
        //generiere einen Zufallswert für die Anzahl der Anfragen an den ersten Zielserver

        //Wähle den nächsten Server:
        Musicservice tempMusicservice;


        if (status) {
            tempMusicservice = m1.getMusicservice1Bean();
            executeRequests(tempMusicservice, (int) ((Math.random() * 20) + 1));
            status = false;

        } else {
            tempMusicservice = m2.getMusicservice2Bean();
            executeRequests(tempMusicservice, (int) ((Math.random() * 20) + 1));
            status = true;
        }




        System.out.println("Anzahl der Aufrufe: " + requests);

        LoadbalancerResult loadbalancerResult = new LoadbalancerResultBean(new ArrayList<SetOfRequests>());
        return loadbalancerResult;
    }

    public void executeRequests(Musicservice tempMusicservice, int requests) {
        for (int i = 0; i < requests; i++) {
            tempMusicservice.whoAmI();
        }

    }

    @Override
    public LoadbalancerResult startLoadbalancerSimulationByTime(int time) {
        //übergebene Zeit
        System.out.println("Zeit:" + time);
        
        for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(time); stop > System.nanoTime();) {
            
            //Differenz, bis Zeitintervall abgelaufen ist
            System.out.println("Differenz:");
            System.out.println(System.nanoTime() - stop);

            //Starte Simulation
            runSimulation();

        }
        System.out.println("Zeit abgelaufen");

        return null;
    }

    @Override
    public void stopLoadbalancerSimulation() {

        status = false;
        System.out.println("stoppe Simlation");
        System.out.println(status);

        //Versuche zu unterbrechen
    }
}
