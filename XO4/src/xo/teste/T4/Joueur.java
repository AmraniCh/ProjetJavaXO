package xo.teste.T4;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public  class Joueur extends Thread  {
	
	private Socket connexion;
	private DataInputStream readRep;
	private DataOutputStream writeRep;
	private Serveur serveur;
	private int Numero;
	private Color marque;
	private Boolean threadsuspend;
	private boolean terminer;
	
	/**
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public  Joueur(Serveur srv,int Numero,Color marque) {
		this.serveur=srv;
		
		this.Numero=Numero;
		this.marque=marque;
		try  {
			this.serveur.connexion(Main.port);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  Boolean MConnexion() throws Exception {
      
		//Socket client =new Socket("127.0.0.1",1099);
		this.connexion=new Socket("127.0.0.1",Main.port);
//		System.out.println("entrez vore nom");
//		Scanner sc =new Scanner(System.in);
	//	String NomClien=sc.nextLine();sc.close();
		
		//on envoie la donnée au serveur 
		
			//ObjectOutputStream oout=new ObjectOutputStream(out);
				//oout.writeObject(this);
		
			
//			DataInputStream in=new DataInputStream(connexion.getInputStream());
//			 String reponseServeur=in.readUTF();
//			 System.out.println(reponseServeur);
			
//			connexion.close();
		//Reception des donné envoie par le serveur 
//		 DataInputStream in=new DataInputStream(client.getInputStream());
//		 Boolean reponseServeur=in.readBoolean();
		
		 connexion.close();
		 //return reponseServeur;
		 return true;
	}
	
	
	
	@Override
	public String toString() {
		return  Numero+"/"+marque;
	}


@Override
public synchronized void start() {
	// TODO Auto-generated method stub
	super.start();
	try {
		MConnexion();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			DataOutputStream out=new DataOutputStream(connexion.getOutputStream());

			out.writeUTF("eeeeee");
		}catch(Exception e){
			e.printStackTrace();
		}
			
			
			while (true) {
				
				
			}
		
	}
	public Socket getConnexion() {
		return connexion;
	}
	public void setConnexion(Socket connexion) {
		this.connexion = connexion;
	}
	public DataInputStream getReadRep() {
		return readRep;
	}
	public void setReadRep(DataInputStream readRep) {
		this.readRep = readRep;
	}
	public DataOutputStream getWriteRep() {
		return writeRep;
	}
	public void setWriteRep(DataOutputStream writeRep) {
		this.writeRep = writeRep;
	}
	public Serveur getServeur() {
		return serveur;
	}
	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int id) {
		this.Numero = id;
	}
	public Color getMarque() {
		return marque;
	}
	public void setMarque(Color marque) {
		this.marque = marque;
	}
	public Boolean getThreadsuspend() {
		return threadsuspend;
	}
	public void setThreadsuspend(Boolean threadsuspend) {
		this.threadsuspend = threadsuspend;
	}
	public boolean isTerminer() {
		return terminer;
	}
	public void setTerminer(boolean terminer) {
		this.terminer = terminer;
	}
	

}

