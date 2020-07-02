package fr.unilim.iut.spaceinvaders.moteurjeu;

import javax.swing.JFrame;

/**
 * Créer une interface graphique avec son contrôleur et son afficheur
 * @author Graou
 *
 */

public class InterfaceGraphique  {

	private PanelDessin panel;
	private Controleur controleur;
	
	public InterfaceGraphique(DessinJeu afficheurAUtiliser,int x,int y)
	{
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel = new PanelDessin(x, y,afficheurAUtiliser);
		jFrame.setContentPane(this.panel);
		
		// Ajout du contrôleur
		Controleur controleurGraphique = new Controleur();
		this.controleur = controleurGraphique;
		this.panel.addKeyListener(controleurGraphique);	
		
		// Récupération du focus
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.getContentPane().setFocusable(true);
		jFrame.getContentPane().requestFocus();
	}
	
	
	public Controleur getControleur() {
		return this.controleur;
	}

	public void afficherJeu() {
		this.panel.dessinerJeu();	
	}
	
}
