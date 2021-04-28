package portscanner;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TestePing{
    
    ArrayList<String> ips = new ArrayList<String>();
    ArrayList<String> ipsAtivos = new ArrayList<String>();
    String host = null;
    
    public ArrayList<String> getIps() throws UnknownHostException, SocketException, InterruptedException {
 
        IpLocal ip = new IpLocal();
        ip.Ip();
         
        this.ips = ip.getIps();
        
        ArrayList<Ping> arrayPing = new ArrayList<>();
        for (int cont = 0; cont < this.ips.size(); cont++){
            Ping p = new Ping(this.ips.get(cont), ipsAtivos);
            p.start();
            arrayPing.add(p);
        }
        
        for(Ping pAux : arrayPing){
            pAux.join();
        }
        
        for (int cont2 = 0; cont2 < this.ipsAtivos.size(); cont2++){
            System.out.println("%%%%%%%%  " + this.ipsAtivos.get(cont2));
        }
        
        return this.ipsAtivos;
    } 
    

/**
* Cria uma comunicacao com a porta desejada, se a porta estiver
* disponivel returna true, caso contrário uma exception ira ocorrer
* e retornara false
*/

}

