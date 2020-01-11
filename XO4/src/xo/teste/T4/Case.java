package xo.teste.T4;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Case extends JPanel{
	
	private Color marque;
	private int numCase;
	
	/**
	 * @param marque
	 * @param numCase
	 */
	public Case(Color marque,int numCase) {
		
		
		this.marque=marque;
		this.numCase=numCase;
		
		this.setBorder(BorderFactory.createLineBorder(marque,2));
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		repaint();
	}
	
public void setMarque(Color marque) {
		this.marque = marque;
	}

/**
 * @param numCase
 */
public Case(int numCase) {
		
		
		this.marque=Color.gray;
		this.numCase=numCase;
		
		this.setBorder(BorderFactory.createLineBorder(marque,2));
		
	}
	
	
	
	
}
