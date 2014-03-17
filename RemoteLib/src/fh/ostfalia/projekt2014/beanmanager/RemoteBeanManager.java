package fh.ostfalia.projekt2014.beanmanager;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * RemoteBeanManager
 * @author M.Tönjes, D.Fahr, Y.Weißflog
 * Siehe Abschnitt 2.3.2
 * Aufruf von: Allen klassen, die RemoteBeans sind (=von RemotBean erben), d.h. einen lookup durchführen wollen
 * Beispiele: siehe RemoteBean-Kommentare (RemoteLib->beanmananger->RemoteBean)

 * RemoteBeanManager der es ermöglicht einen lookup auf beliebige Dienste durchzuführen.
 * Wird bei Programmstart (siehe statische Initialisierung!) instanziert (einmalig, da Singleton) und 
 * holt über die Lookup-Methode das entfernte Objekt.
 * Genauere Beschreibung befindet sich in Abschnit 2.3.2!
 * 
 * Dazu sind wieder 3 Parameter an die Methode lookupRemoteBean zu übergeben:
 * jndiName des Objektes, ip und der Port
 * 
 */
public class RemoteBeanManager {

    //statische Variable, zu Beginn null, aber bei Programmstart existent
    private static RemoteBeanManager instance = null;

    private RemoteBeanManager(){
    }
    
    /**
     * Aufruf zur Instanzierung der bereits angelgeten statischen Variable
     * Erläuterung (Siehe Singleton, OOP-Vorlesung)
     * Zweck: Einmaligkeit des RemoteBeanManagers unter allen RemoteBeans.
     * @return
     */
    public static RemoteBeanManager getInstance(){
        if(instance == null){
            instance = new RemoteBeanManager();
        }
        return instance;
    }
    
    /**
     *  Übergabe der oben beschriebenen Paremeter
     *  Als Rückgabe wird das Stub-Objekt erwartet.
     * Beispiel für Aufruf befindet sich ebenfalls in 2.3.1!
     * @param jndiName JNDI-Name des Objektes 
     * @param ip Hier wird die IP des Servers angegeben  
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
