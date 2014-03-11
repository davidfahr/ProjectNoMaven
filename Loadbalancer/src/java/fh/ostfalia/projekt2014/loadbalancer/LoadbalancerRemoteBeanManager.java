/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fh.ostfalia.projekt2014.loadbalancer;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author KingDCB
 */
public class LoadbalancerRemoteBeanManager {
    
    private static LoadbalancerRemoteBeanManager instance = null;

    private LoadbalancerRemoteBeanManager(){
    }
    
    public static LoadbalancerRemoteBeanManager getInstance(){
        if(instance == null){
            instance = new LoadbalancerRemoteBeanManager();
        }
        return instance;
    }
    
    /**
     *  Übergabe der oben beschriebenen Paremeter
     *  Als Rückgabe wird das Stub-Objekt erwartet.
     * @param jndiName JNDI-Name des Objektes z.B. musicservice.fh.ostfalia.projek2014.musicservice
     * @param ip Hier wird die IP des Servers angegeben --> Bei Multi-Server-Systemen steht hier nicht mehr localhost sondern die entsprechende Server IP auf dem 
     * der der Dienst deployt ist!!!!
     * @param port Der Port auf dem Server.
     * @return Das Stub-Objekt.
     */
    public Object lookupRemoteBean(String jndiName, String ip, String port) {
        
        try {
            Properties props = new Properties();
            //Speicherung der 2 Paremter IP, Port in Property-Object "props"
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            
            props.setProperty("org.omg.CORBA.ORBInitialHost", ip);
            props.setProperty("org.omg.CORBA.ORBInitialPort", port);
            //Zuordnung von Properties, die den Server und Port bestimmen, zum InitialContext ic
            Context ic = new InitialContext(props);
            //Lookup auf JNDI-Pfad über den InitialContext (der jetzt Server + Port kennt) 
            return ic.lookup(jndiName);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
