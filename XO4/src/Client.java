import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


/**
 * @author elbag
 *
 */
public class Client extends JFrame implements Runnable,ActionListener{
		
	/**
	 * 
	 */
	public  int numberClicks=1;
	public static Boolean fin=false;
	public static List<String> playerCases = new ArrayList<String>();
	public static List<JButton> ListButtons=new ArrayList<JButton>();
       //Composant de la JFrame
	   public JPanel container = new JPanel();
	   public  JPanel gamePan = new JPanel();
	   public  JTextField txtPlayerName;
	   public JTextField txtMarque;
	   //pour l affichage
	   	String[] casesID = new String[25];
		//static List<JButton> cases = new ArrayList<JButton>(25);
		//pour le partage connexion via les sockets
		private static final long serialVersionUID = 1L;
		private Socket connexion;
		private DataInputStream recoie;
		private DataOutputStream envoie;
		//atttr client
		private String nom;
		private Image marque;
		private boolean turn=false;
		public String marqueString;	
	
	/**
	 * @param Name
	 * @param marque
	 * @param connexion
	 */
	public Client(String Name,String marqueChar,Socket connexion)  {
		initializecases();
		this.setLayout(new BorderLayout());
		this.marqueString=marqueChar;  
		this.txtMarque=new JTextField();
		this.txtPlayerName=new JTextField();
		this.txtMarque.setText(marqueChar);
		this.txtPlayerName.setText(Name);
		this.container.setLayout(new GridLayout(1, 2));
		container.add(txtPlayerName);container.add(txtMarque);
		this.add(container,BorderLayout.SOUTH);
		// GAME PAN
		initializeGamePan();
		this.add(gamePan, BorderLayout.CENTER);
		gamePan.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.connexion=connexion;
		this.nom=Name;
		try {
			if(marqueChar.equalsIgnoreCase("X"))
			{
				this.marque=ImageIO.read(getClass().getResource("_X_.png"));
				this.turn=true;
			}
				
			else {
				this.marque=ImageIO.read(getClass().getResource("_O_.png"));
				this.turn=false;
			}
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			recoie=new DataInputStream(connexion.getInputStream());
			envoie=new DataOutputStream(connexion.getOutputStream());
		}catch(Exception e) {
			
			e.getStackTrace();
		}
	
	}

	/**
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		
		
		Socket client=new Socket("127.0.0.1", 9085);
		
		BufferedReader keyBord=new BufferedReader(new InputStreamReader(System.in));
		//en lit la marque choisie par le jouer
		
		String nom=keyBord.readLine();
		String Marq=keyBord.readLine();
		
		
		
		//cree le Thread client
		
		Client myClient=new Client(nom,Marq,client);	
		Thread threadClient=new Thread(myClient);
		threadClient.start();
		keyBord.close();
		
		//pour la taille de la fentre
	
		myClient.setVisible(true);
		myClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myClient.setBounds(400, 100, 500, 500);
		myClient.setVisible(true);
		
		
	}
	private  void check() {
		// TODO Auto-generated method stub
	
		
		boolean result;
		//System.out.println("nbr "+numberClicks);
		if (numberClicks == 25) {
			// EndGame
			
			JOptionPane.showMessageDialog(this, " c'etait Chaud egalité  " );
			
			//restartgame();
			
			try {
				this.envoie.writeUTF("egalité fin de la partie");Client.fin=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (numberClicks >= 5) {
			if (this.isTurn()) {
				result = checkFirstDiagonal(playerCases);
				result = result ? result : checkSecondDiagonal(playerCases);
				result = result ? result : checkRows(playerCases);
				result = result ? result : checkColumns(playerCases);
			} else {
				result = checkFirstDiagonal(playerCases);
				result = result ? result : checkSecondDiagonal(playerCases);
				result = result ? result : checkRows(playerCases);
				result = result ? result : checkColumns(playerCases);
			}
			if (result) {
				
				JOptionPane.showMessageDialog(this, "We Got a Winner : " + this.getNom());
				//restartgame();
				try {
					this.envoie.writeUTF("perdu");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Client.fin=true;
				try {
					this.envoie.writeUTF("Fin de la partie ---> "+this.getNom()+"a gagner");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		numberClicks++;
	}

	private void synchroniserlaJframe(String idclickerparAdversere) throws IOException {
		// TODO Auto-generated method stub
		for(JButton var_btn:ListButtons) {
			if((var_btn.getClientProperty("ID").toString()).equalsIgnoreCase(idclickerparAdversere))
					{
				
				
				if(this.marqueString.equalsIgnoreCase("X"))
				{
					var_btn.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("_O_.png"))));
				}
					
				else if(this.marqueString.equalsIgnoreCase("O"))
				{
					
					var_btn.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("_X_.png"))));

				}
				
		}}
	}


	// Diagonals
	static List<String> firstDiag = Arrays.asList("1-1", "2-2", "3-3", "4-4", "5-5");
	static List<String> secondDiag = Arrays.asList("5-1", "4-2", "3-3", "2-4", "1-5");

	private boolean checkFirstDiagonal(List<String> listCases) {
		return listCases.containsAll(firstDiag);
	}

	private boolean checkSecondDiagonal(List<String> listCases) {
		return listCases.containsAll(secondDiag);
	}

	// Rows
	static List<String> row1 = Arrays.asList("1-1", "1-2", "1-3", "1-4", "1-5");
	static List<String> row2 = Arrays.asList("2-1", "2-2", "2-3", "2-4", "2-5");
	static List<String> row3 = Arrays.asList("3-1", "3-2", "3-3", "3-4", "3-5");
	static List<String> row4 = Arrays.asList("4-1", "4-2", "4-3", "4-4", "4-5");
	static List<String> row5 = Arrays.asList("5-1", "5-2", "5-3", "5-4", "5-5");

	private boolean checkRows(List<String> listCases) {
		if (listCases.containsAll(row1)) {
			return true;
		}
		if (listCases.containsAll(row2)) {
			return true;
		}
		if (listCases.containsAll(row3)) {
			return true;
		}
		if (listCases.containsAll(row4)) {
			return true;
		}
		if (listCases.containsAll(row5)) {
			return true;
		}
		return false;
	}

	// Columns
	static List<String> col1 = Arrays.asList("5-1", "4-1", "3-1", "2-1", "1-1");
	static List<String> col2 = Arrays.asList("5-2", "4-2", "3-2", "2-2", "1-2");
	static List<String> col3 = Arrays.asList("5-3", "4-3", "3-3", "2-3", "1-3");
	static List<String> col4 = Arrays.asList("5-4", "4-4", "3-4", "2-4", "1-4");
	static List<String> col5 = Arrays.asList("5-5", "4-5", "3-5", "2-5", "1-5");

	private boolean checkColumns(List<String> listCases) {
		if (listCases.containsAll(col1)) {
			return true;
		}
		if (listCases.containsAll(col2)) {
			return true;
		}
		if (listCases.containsAll(col3)) {
			return true;
		}
		if (listCases.containsAll(col4)) {
			return true;
		}
		if (listCases.containsAll(col5)) {
			return true;
		}
		return false;
	}


	private void initializecases() {
		int cp = 0;
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				casesID[cp] = i + "-" + j;
				cp++;
			}
		}
	}
	private void initializeGamePan() {
		 JButton btn;
		this.gamePan.setLayout(new GridLayout(5, 5));
		for (int i = 0; i < casesID.length; i++) {
			btn = new JButton();setdesign(btn);
			btn.putClientProperty("ID", casesID[i]);
			btn.putClientProperty("Player", null);
			btn.addActionListener(this);
			ListButtons.add(btn);
			this.gamePan.add(btn);
		}
	}
	private void setdesign(JButton btn) {
		// TODO Auto-generated method stub
		
		
		btn.setBackground(Color.WHITE);
		btn.setBorder(BorderFactory.createLineBorder(Color.gray,2));
		
	}

	private void envoielesdonnechanger(String IDclicker) throws IOException {
		// TODO Auto-generated method stub
	check();
		this.envoie.writeUTF(IDclicker);	
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
		
			try {
				String Idcliquer=recoie.readUTF();
				for(JButton btnvar:Client.ListButtons)
				{
					if((btnvar.getClientProperty("ID")+"").equals(Idcliquer))
					{
						btnvar.putClientProperty("Player", "-");
					}
					
				}
				this.setTurn(true);
				
				if(Idcliquer.equalsIgnoreCase("perdu"))
				{
					JOptionPane.showMessageDialog(this, "vous avez perdu");
					//restartgame();
				}
				check();
				try {
					synchroniserlaJframe(Idcliquer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Image getMarque() {
		return marque;
	}

	public void setMarque(Image marque) {
		this.marque = marque;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	@Override
	public String toString() {
		return nom+ "-" +this.getMarque()+"-"+turn+"-"+this.casesID;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton caseClicked = (JButton) e.getSource();
		//System.out.println(isTurn());
		try {
			if(isTurn()) 
			{   System.out.println(caseClicked.getClientProperty("Player"));
			      
				if(caseClicked.getClientProperty("Player")==null)
				{
					caseClicked.putClientProperty("Player",this.marqueString);
					//caseClicked.putClientProperty("Marque",);
					playerCases.add((String) caseClicked.getClientProperty("ID"));
					caseClicked.setIcon(new ImageIcon(this.getMarque()));
					
					envoielesdonnechanger(caseClicked.getClientProperty("ID")+"");
					
					this.setTurn(false);
			}else {
				JOptionPane.showMessageDialog(this, "invalide choix");
			}
				}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void restartgame() {
		// TODO Auto-generated method stub
		String [] options={"oui","non"};
		int n =
		JOptionPane.showOptionDialog(this,"voulez vous recommencer ?","recommencer",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
		if(n==1) 
		{
			this.dispose();
		}
		else {
			
			playerCases.clear();
			numberClicks=0;
			
			Iterator<JButton> iter=ListButtons.iterator();
			while(iter.hasNext())
			{
					JButton var=iter.next();
					var.putClientProperty("Player", null);
					var.setIcon(new ImageIcon());
			}
		}
	}

}
