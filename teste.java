/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package portscanner;

import java.net.InterfaceAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinícius
 */
public class teste {
    public static void main(String args[]) throws UnknownHostException, SocketException{
        IpLocal ip = new IpLocal();
        ip.Ip();
        
        ArrayList<String> ips = new ArrayList<String>();
        ips = ip.getIps();
        for (int cont = 0; cont < ips.size(); cont++){
            System.out.println(ips.get(cont));
        }
    }
}
