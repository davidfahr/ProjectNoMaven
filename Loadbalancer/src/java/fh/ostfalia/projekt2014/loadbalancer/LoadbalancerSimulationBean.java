package fh.ostfalia.projekt2014.loadbalancer;

import fh.ostfalia.projekt2014.loadbalancer.entities.LoadbalancerResultBean;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice1Remote;
import fh.ostfalia.projekt2014.loadbalancer.remote.Musicservice2Remote;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.entities.LoadbalancerResult;
import fh.ostfalia.projekt2014.loadbalancerremoteinterfaces.interfaces.LoadbalancerSimulation;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author M.Tönjes, D.Fahr, Y.Weißflog 
 * LoadbalancerSimulationBean als Remotezugriff auf
 * die LoadbalancerSimulation 
 * mehr Information siehe Systemdokumentation Kapitel 5.4
 * Aufruf: Webserver(LoadbalancerSimulationMBean) -> LoadbalancerSimulationBean
 */
@Stateless
public class LoadbalancerSimulationBean implements LoadbalancerSimulation, Serializable {
    private static final long serialVersionUID = 1L;
    private LoadbalancerResult loadbalancerResult;

     /*
     * Hier werden die nötigen EJBs Musiservice1Remote und Musicservice2Remote geladen
     */
    @EJB
    private Musicservice1Remote m1;
    @EJB
    private Musicservice2Remote m2;
  
    /**
     * Boolean zur Bestimmung des angesprochenen Server true = Methoden werden
     * zu Musicservice 1 umgeleitet false = Methoden werden zu Musicservice 2
     * umgeleitet
     */
    private boolean switchServer = true;
    int time;

    public LoadbalancerSimulationBean() {
    }

 


    /**
     * Startet die Loadbalancingsimulation
     * nähere Beschreibung findet sich in der Systemdokumentation Kapitel 5.4.3
     * @return
     */
    public SetOfRequestsBean runSimulation() {      
        int zaehler = 0;
        //Wähle den nächsten Server:
        if (switchServer) {
            //generiere einen Zufallswert für die Anzahl der Anfragen an den ersten Zielserver
            for (int i = 0; i < (int) ((Math.random() * 20) + 1); i++) {
                m1.whoAmI();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoadbalancerSimulationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                zaehler++;
            }
            System.out.println("Aufrufe: " + zaehler);
            switchServer = false;
            //M1 und Anzahl der Aufrufe wird der Liste hinzugefuegt
            return new SetOfRequestsBean(1, zaehler);
        } else {

            for (int i = 0; i < (int) ((Math.random() * 20) + 1); i++) {
                m2.whoAmI();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LoadbalancerSimulationBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                zaehler++;
            }
            System.out.println("Aufrufe: " + zaehler);
            switchServer = true;
            //M2 und Anzahl der Aufrufe wird der Liste hinzugefuegt
            return new SetOfRequestsBean(2, zaehler);
        }
    }

    /**
     * Startet die LoadbalancerSimulation für eine bestimmtes Zeitintervall
     * nähere Beschreibung findet sich in der Systemdokumentation Kapitel 5.4.3
     * @param time Gibt die Zeit an, wie lange die Simulation in startLoadbalancerSimulationByTime
     * laufen soll
     * @return Gibt die Ergebnisse der Simulation zurück
     */
    @Override
    public LoadbalancerResult startLoadbalancerSimulationByTime(int time) {;
        LinkedList<SetOfRequestsBean> requestList = new LinkedList<SetOfRequestsBean>();
        for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(time); stop > System.nanoTime();) {
            //Differenz, bis Zeitintervall abgelaufen ist
            System.out.println("Differenz:");
            System.out.println(System.nanoTime() - stop);
            requestList.add(runSimulation());
        }
        System.out.println("Zeit abgelaufen");
        return new LoadbalancerResultBean(requestList);
    }

    /**
     * Methode zum manuellen Starten der LoadbalancerSimulation
     * Methode wurde in das Projekt implementiert, da es sich um eine asynchrone Methode 
     * handeln muss und der zeitliche Aufwand das Zeitbudget überschritten hatte.
     * @return
     */
    @Override
    public LoadbalancerResult startLoadbalancerSimulation() {
        while (switchServer == true) {
            //loadbalancerResult = runSimulation();
        }
        return loadbalancerResult;
    }
    
    /**
     * Stoppt die Simulation manuell
     * 
     */
    @Override
    public void stopLoadbalancerSimulation() {
        switchServer = false;
    }
}
