package xo.teste.T4;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class Serveur {
	
	private byte Plateau;
	private Boolean Mouv;
	private JTextArea zonetxt;
	private ServerSocket serveur;
	private int idjoueur;
	//private ArrayList<Joueur> joueurs;
	private static  int compteur=0; 
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Serveur s =new Serveur();
			s.connexion(Main.port);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param port
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public  void connexion(int port) throws IOException, ClassNotFoundException {
//	     if(compteur<2) {
	    	 
	     
		ServerSocket ss=new ServerSocket(port);
		//accepter la connexion de client
		  Socket s=ss.accept();
		//affiche msg de connexion
		//zonetxt.append("connexion etablie "); 
		System.out.println("connexion etablie ");
		DataInputStream in=new DataInputStream(s.getInputStream());
		while(true) {
			String nomdeclient=in.readUTF();
			System.out.println(nomdeclient);
			
		}
		
		//envoie msg a joeur
		
//		DataOutputStream out=new DataOutputStream(s.getOutputStream());
//		out.writeBoolean(true);
//		s.close();ss.close();
//	     }else {
//	    	 //msg envoie au troisiem joueur conécté il doit attendre
//	    	 //que les deux joueur connécté qu'il se deconnecte 
//	     }

}







	
	
	
}


