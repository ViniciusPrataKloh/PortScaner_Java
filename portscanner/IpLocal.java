package portscanner;

import java.lang.reflect.Array;
import java.math.*;
import java.net.*;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinícius
 */
public class IpLocal {

    String host = "";
    String endereco = "";
    String mascara = "";
    String broadcast = "";
    String rede = "";
    InetAddress localHost;
    String broad;
    int qtdHosts = 1;
    int qtdZeros = 0;
    int mask = 0;
    private ArrayList<String> ips = new ArrayList<String>();

    public void Ip() throws UnknownHostException, SocketException {
        this.localHost = Inet4Address.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);

        host = localHost.getHostName();
        endereco = localHost.getHostAddress();
        
        // Compara o retorno de NetworkInterface e Intet4Address:
        for(InterfaceAddress init : ni.getInterfaceAddresses()){
            String ip = init.getAddress().toString().replace("/", "");
            if(ip.equals(endereco)){
                broad = init.getBroadcast().toString().replace("/", "");
                mask = init.getNetworkPrefixLength();
            }
        }
        broadcast  = broad.toString();
        
        this.verificaMascara();
        this.verificaBroadcast();

        this.verificaRede();
    }
    
    public void verificaMascara(){
        
        String maskBin = "";
        
        for (int cont = 0; cont <= 32; cont++){

            if (cont < this.mask){
                maskBin += "1";
            } else if (cont > this.mask){
                maskBin += "0";
                qtdZeros++;
            }
        }
       
        StringBuilder stringBuilder = new StringBuilder(maskBin);
        stringBuilder.insert(maskBin.length() - 8, '.');
        stringBuilder.insert(maskBin.length() - 16, '.');
        stringBuilder.insert(maskBin.length() - 24, '.');
        
        this.mascara = stringBuilder.toString();  
        //System.out.println("Mascara: " + this.mascara);
    }
    
    public void verificaBroadcast(){
        int cont;
        qtdHosts = 1;
        
        for (cont = 0; cont < this.qtdZeros; cont++){
            this.qtdHosts = this.qtdHosts * 2;
        }
        this.qtdHosts = this.qtdHosts - 1;
    }
    
    public String retornaIp(){        
        return this.endereco;
    }
    
    public String retornaNome(){
        return this.host;
    }
    
    public int retornaNumHosts(){
        for (int cont = 1; cont <= this.qtdZeros; cont++){
            this.qtdHosts = this.qtdHosts * 2;
        }
        return (this.qtdHosts - 1);
    }
    
    public void verificaRede(){
        String hosts = "";
        char stringAux = 0;
        int aux = 0;
        int diferenca;
        
        for (int cont = 0; cont < this.broadcast.length(); cont++){
            if ('.' == this.broadcast.charAt(cont)){
                aux++;
            }
            if (aux >= 3){
                stringAux = this.broadcast.charAt(cont);
                hosts += stringAux;
            }
        }

        int tmp = 0; 
        String parteMaior = "";
        String parteMenor = "";
        char octetoAux2 = 0;
        char octetoAux = 0;
        
        for (int cont = 0; cont < this.broadcast.length(); cont++){
            if (this.broadcast.charAt(cont) == '.'){
                tmp++;
            }
            if (tmp < 3){
                octetoAux = this.broadcast.charAt(cont);
                parteMaior += String.valueOf(octetoAux);
            }
            if (tmp >= 3){
                octetoAux2 = this.broadcast.charAt(cont);
                parteMenor += String.valueOf(octetoAux2);
            }
        }
        
        parteMaior += ".";
        parteMenor = parteMenor.replace(".", "");
        
        diferenca = Integer.parseInt(parteMenor) - qtdHosts;
        this.rede = parteMaior.concat(diferenca + "");
        //System.out.println("Rede: " + this.rede);
        
        for (int cont2 = 0; cont2 <= qtdHosts; cont2++){
            this.ips.add(parteMaior + cont2 + "");
            //System.out.println(ips.get(cont2));
        }
    }
    
    public ArrayList<String> getIps(){
        return this.ips;
    }
    
    public void imprimelista(){
        for (int cont = 0; cont < this.ips.size(); cont++){
            //System.out.println(ips.get(cont));
        }
    }
  
} 