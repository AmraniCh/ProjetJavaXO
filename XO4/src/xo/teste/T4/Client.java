package xo.teste.T4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author elbag
 *
 */
public class Client extends JFrame implements Runnable{
	
	JTextField identite;
	JTextArea affichage;
	JPanel panneauPlateau;
	Case[][] plateau;
	Socket conne;
	DataInputStream in;
	DataOutputStream ou;
	Thread threadsortie;
	Color maMarque;
	boolean monTor;
	
	
	public Client() {
		plateau=new Case[3][3];
		identite=new JTextField(10);
		affichage=new JTextArea(10, 20);
		panneauPlateau.setLayout(new BorderLayout(3,3));
		remplir(panneauPlateau,plateau);
		
		
	}
	
	private void remplir(JPanel p,Case[][] c) {
		
	}
	public  Boolean MConnexion() throws UnknownHostException, IOException {

		conne =new Socket("127.0.0.1",Main.port);
//		System.out.println("entrez vore nom");
//		Scanner sc =new Scanner(System.in);
	//	String NomClien=sc.nextLine();sc.close();
		
		//on envoie la donnée au serveur 
		
			
			//ObjectOutputStream oout=new ObjectOutputStream(out);
				//oout.writeObject(this);
			
		//Reception des donné envoie par le serveur 
//		 DataInputStream in=new DataInputStream(client.getInputStream());
//		 Boolean reponseServeur=in.readBoolean();
		
//		DataInputStream in=new DataInputStream(conne.getInputStream());
//		String nomdeclient=in.readUTF();
//		System.out.println(nomdeclient);
//		 conne.close();
		 //return reponseServeur;
		 return true;
	}
	
	
  @Override
public void run() {
	// TODO Auto-generated method stub
	
	try {
		MConnexion();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	while(true) {
		try {
			DataOutputStream out=new DataOutputStream(conne.getOutputStream());
			out.writeUTF("wwwwww");
			//out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


}
