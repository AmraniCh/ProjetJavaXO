package xo.teste.T4;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author elbag
 *
 */
public class Main {
public static final int port=1977;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  //new Serveur();
  
			
			
			try {
				Serveur srv=new Serveur();
				//Socket s=new Socket("127.0.0.1",Main.port);
				Joueur t= new Joueur(srv,3,Color.black);
				Client c=new Client();
				Thread k=new Thread(t);
				k.start();
				t.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}

}
