package fr.unilim.iut.spaceinvaders.moteurjeu;


/**
 * Classe MoteurGraphique représente un moteur de jeu générique.
 * On lui passe un jeu et un afficheur et il permet d'éxécuter un jeu.
 */

public class MoteurGraphique {
	
	private Jeu jeu;

	private InterfaceGraphique graphicalUserInterface;

	private DessinJeu afficheur;

	public MoteurGraphique(Jeu jeuALancer, DessinJeu afficheurAUtiliser) {
		this.jeu = jeuALancer;
		this.afficheur = afficheurAUtiliser;
	}

	public void lancerJeu(int longueur, int hauteur) throws InterruptedException {
		
		this.graphicalUserInterface = new InterfaceGraphique(this.afficheur,longueur,hauteur);
		Controleur controle = this.graphicalUserInterface.getControleur();

		while (!this.jeu.estFini()) {
			Commande commandeUtilisateur = controle.getCommande();
			this.jeu.evoluerLeJeuSelonLaCommandeDeLUtilisateur(commandeUtilisateur);
			this.graphicalUserInterface.afficherJeu();
			// Met en attente
			Thread.sleep(100);
		}
	}

}
