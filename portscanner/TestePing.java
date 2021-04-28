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
        
        int cont2;
        System.out.println("\nIPs Ativos:");
        for (cont2 = 0; cont2 < this.ipsAtivos.size(); cont2++){
            System.out.println("\t" + this.ipsAtivos.get(cont2));
        }
        System.out.println("\nQuantidade de IPs ativos: " + cont2 + "\n");
        
        return this.ipsAtivos;
    } 
}

