import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Client_Handler  implements Runnable {

	private DataInputStream in;
	private DataOutputStream out;
	private Socket client;
	private ArrayList<Client_Handler> list;
	private JTextArea msgarea;
	
	
	
	/**
	 * @param s
	 * @param list
	 * @throws IOException
	 */
	public  Client_Handler(Socket s,ArrayList<Client_Handler> list,JTextArea msgarea ) throws IOException {
		this.client=s;
		this.list=list;
		out=new DataOutputStream(this.client.getOutputStream());
		in=new DataInputStream(this.client.getInputStream());
	
		this.msgarea=msgarea;
		
	}
	
	@Override
	public void run() {
			// TODO Auto-generated method stub
		
		try {
			if(Client.fin) {
				DataInputStream in = new DataInputStream(client.getInputStream()); 
		        String Idcliquer=in.readUTF();
		        msgarea.append("\n[--"+Idcliquer+"--]\n");
		      
			}
			 while (!Client.fin) {
				 
				
			        DataInputStream in = new DataInputStream(client.getInputStream()); 
			        String Idcliquer=in.readUTF();
			        msgarea.append("> la case ["+Idcliquer+" ]a ete cliquer\n");
			       // System.out.println(Idcliquer);
			        			sendToAll(Idcliquer);

			 }				
				}catch(Exception e){
			 e.getStackTrace();
			
		}finally{
			
			try {
				 client.close();
				    in.close();
				    out.close();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
		
}	
	

	private void sendToAll(String in) {
		// TODO Auto-generated method stub
		for (Client_Handler item : this.list) {
			
			
			
			try {
				if(!item.equals(this))
					item.out.writeUTF(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
		

}
