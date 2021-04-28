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
        
	// Variáveis para contagem das portas:
        
        JanelaScanner js = new JanelaScanner();
        //js.setVisible(true);
        /*
        boolean iniciar = false;
        while(iniciar == false){
        }*/
        
        ArrayList<String> ips = new ArrayList<String>();
	ArrayList<String> portas = new ArrayList<String>();
        
        TestePing tp = new TestePing();
        ips = tp.getIps();
        
        for (int cont = 0; cont < ips.size(); cont++){
            System.out.println("#################");
            System.out.println(ips.get(cont));
            System.out.println("#################");
        }
       
        int portaInicial = 0;
        int portaFinal = 1024;
        
        
        for (int contIp = 0; contIp < ips.size(); contIp++){
            ArrayList<ClasseIp> threads = new ArrayList<>(); 
            for (int contPorta = portaInicial; contPorta < portaFinal; contPorta = contPorta + 4){
                ClasseIp ci = new ClasseIp(contPorta, (contPorta + 4), ips.get(contIp));
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
