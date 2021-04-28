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
                System.out.println(hostLocal + "=" + doPing(hostLocal,timeOut));
                System.out.println("Porta: " + isPortAvailable(port));
                if (doPing(hostLocal, timeOut) != false){
                    this.ipsAtivos.add(host);
                }     
            }
    }
    
    public static boolean doPing(String host){
    return doPing(host,3000); // 3 segundos
}
/**
* Verifica se determinado host esta atingivel
*/
public static boolean doPing(String host,int timeOut){
    try {
        return InetAddress.getByName(host).isReachable(timeOut);
    } catch (Exception e) {
        return false;
    }
}

public static boolean isPortAvailable(int port){
    try {
        ServerSocket srv = new ServerSocket(port);
        srv.close();
        srv = null;
        return true;
    } catch (IOException e) {
        return false;
    }
}
/**
* Uma outra maneira de fazer o ping, dessa maneira é invocado o
* comando ping do sistemaOperacional e verificado na mensagem
* de retorno se houve faha.
*/
public static boolean runPing(String ipstr) {
    boolean retv = false;
    try {
        InputStream ins = Runtime.getRuntime().exec("ping -n 1 -w 2000 " + ipstr).getInputStream();
        Thread.sleep(3000);
        byte[] prsbuf = new byte[ins.available()];
        ins.read(prsbuf);
        String parsstr = new StringTokenizer(new String(prsbuf), "%")
        .nextToken().trim();

        if (!parsstr.endsWith("100"))
            retv = true;
        } catch (Exception e) {
            retv = false;
        }
        return retv;
    }
    
}
