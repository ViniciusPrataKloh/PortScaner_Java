package portscanner;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Vinícius
 */
public class PortScanner {

    public static void main(String[] args) throws UnknownHostException, SocketException, InterruptedException {
        
        ArrayList<String> ips = new ArrayList<String>();
	ArrayList<String> portas = new ArrayList<String>();
        
        TestePing tp = new TestePing();
        ips = tp.getIps();
       
        int portaInicial = 0;
        int portaFinal = 1024;
        
        
        for (int contIp = 0; contIp < ips.size(); contIp++){
            ArrayList<ClasseIp> threads = new ArrayList<>(); 
            for (int contPorta = portaInicial; contPorta < portaFinal; contPorta = contPorta + 2){
                ClasseIp ci = new ClasseIp(contPorta, (contPorta + 2), ips.get(contIp));
                ci.start();
                threads.add(ci);
            }
            
            for(ClasseIp t: threads) {
                t.join();
            }
        } 
        
        
    }
    
    public void setIniciar(boolean i){
        
    }
}
