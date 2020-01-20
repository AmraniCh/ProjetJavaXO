import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Serveur {
	
	
	
	private static ArrayList<Client_Handler> ClientsHandler=new ArrayList<Client_Handler>();

	private static  ExecutorService pool=Executors.newFixedThreadPool(2);
	public static int nbconnection=1;
    public static   JFrame f;
    public static JTextArea msgArea;
    public static JScrollPane scrollPan;
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
             ServerSocket serveur=new ServerSocket(9085);
            // if(nbconnection<2)
              f=new JFrame();
             msgArea=new JTextArea(20,50);
         	 scrollPan=new JScrollPane(msgArea);
         	
         	f.setLayout(new FlowLayout());
         	f.add(scrollPan);
         	f.pack();
         	f.setVisible(true);
         	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         	//if(nbconnection<=2)
         	//{
         	
             while(nbconnection<=2)
             {
            	 msgArea.append("> en attente d un client\n");
            	 System.out.println("> en attente d un client\n");
            	 Socket client=serveur.accept();
            	 System.out.println("> client connecte "+nbconnection+"........\n");
            	 msgArea.append("> client "+nbconnection+" connecte  ........\n");
            	 Client_Handler Client_Thread=new Client_Handler(client,ClientsHandler,msgArea);
            	 
            	 ClientsHandler.add(Client_Thread);
            	 pool.execute(Client_Thread);
            	 nbconnection++;
            	 
             }
             
         	
         	//}

             
	}

}
