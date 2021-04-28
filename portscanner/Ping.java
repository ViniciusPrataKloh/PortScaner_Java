package portscanner;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Vinícius
 */
public class Ping extends Thread{
    
    String host;
    ArrayList<String> ipsAtivos = new ArrayList<String>();
    
    public Ping(String ip, ArrayList<String> todosIps){
        this.host = ip;
        this.ipsAtivos = todosIps;
        
    }
   
    @Override
    public void run(){  
                    
        String hostLocal = this.host;
        int timeOut = 3000;
        int port = 8080;
        
            for (int cont2 = 0; cont2 < 1; cont2++){
                if (doPing(hostLocal, timeOut) != false){
                    this.ipsAtivos.add(host);
                }     
            }
    }
    
    public static boolean doPing(String host){
        return doPing(host,3000);
    }

    public static boolean doPing(String host,int timeOut){
        try {
            return InetAddress.getByName(host).isReachable(timeOut);
        } catch (Exception e) {
            return false;
        }
    }   
}
