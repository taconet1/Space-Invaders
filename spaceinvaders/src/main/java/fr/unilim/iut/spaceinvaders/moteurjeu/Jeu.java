package fr.unilim.iut.spaceinvaders.moteurjeu;

/**
 * @author Graou
 */

public interface Jeu {
	public void evoluerLeJeuSelonLaCommandeDeLUtilisateur(Commande commandeUtilisateur);
	public boolean estFini();
}
