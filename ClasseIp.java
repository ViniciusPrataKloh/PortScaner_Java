package portscanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Vinícius
 */
public class ClasseIp extends Thread{
    int inicioRange;
    int finalRange;
    ArrayList<Porta> portasAbertas = new ArrayList<Porta>();
    ArrayList<Porta> portasFechadas = new ArrayList<Porta>();
    ArrayList<String> ipsAtivos = new ArrayList<String>();
    private int numPorta;
    String host = null;

    JanelaScanner js;
    
    public ClasseIp(){
    }
    
    public ClasseIp(int inicioRange, int finalRange, String host){
        this.inicioRange = inicioRange;
	this.finalRange = finalRange;
        this.host = host;
        this.js = js;
    }
    
    
    @Override
    public void run(){
        
        String resultado;
            for (int cont = inicioRange; cont < finalRange; cont++){
                try{
                    Socket socket = new Socket(this.host, cont);
                    Porta porta = new Porta(cont, "Aberta");
                    portasAbertas.add(porta);
                    this.numPorta = cont;
                    resultado = "IP:     " + host + "    Porta: " + cont + "    Status: Aberta";
                    System.out.println(resultado);
                    this.compararPorta(cont);
                    this.js.setResultado(resultado);
                } catch (Exception e){
                    //System.out.println("\nPorta fechada: " + cont);
                }
            } 
    }
    
    public ArrayList<Porta> retornaPortas(){
        return this.portasAbertas;
    }
    
    public int retornaNumPorta(){
        return this.numPorta;
    }

    public int getInicioRange() {
        return inicioRange;
    }

    public void setInicioRange(int inicioRange) {
        this.inicioRange = inicioRange;
    }

    public int getFinalRange() {
        return finalRange;
    }

    public void setFinalRange(int finalRange) {
        this.finalRange = finalRange;
    }
    
    public void compararPorta(int cont){
        String caminho = "C:\\Users\\Vinícius\\Documents\\Meus Documentos\\Faculdade\\4º Período\\SD - Fábio\\PortScanner\\";
        String arquivo= caminho + "Portas.txt";  
        String linha="";
        String palavra = " " + cont + " ";       
        try {  
            int i = 0;  
            BufferedReader in = new BufferedReader(new FileReader(caminho + "Portas.txt"));
            while ((linha = in.readLine()) != null) {  
                i++;
                if (linha.contains(palavra)) {  
                    System.out.println(" - - - - " + linha + " - - - - ");  
                } 
            } 
            System.out.println("\n\n");
        } catch (Exception e) {  
            System.err.println("Erro na abertura do arquivo " + arquivo);  
            //return "Erro";  
        }  
        //return "Não achou";
    }
}