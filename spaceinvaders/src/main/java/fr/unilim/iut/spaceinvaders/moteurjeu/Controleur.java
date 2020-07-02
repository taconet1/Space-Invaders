package fr.unilim.iut.spaceinvaders.moteurjeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe qui représente un contrôleur en lien avec un KeyListener
 * 
 * @author vthomas
 * 
 */

public class Controleur implements KeyListener {

	private Commande commandeEnCours;
	private Commande commandeARetourner;

	public Controleur() {
		this.commandeEnCours = new Commande();
		this.commandeARetourner = new Commande();
	}


	public Commande getCommande() {
		Commande aRetourner = this.commandeARetourner;
		this.commandeARetourner = new Commande(this.commandeEnCours);
		return (aRetourner);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			this.commandeEnCours.gauche = true;
			this.commandeARetourner.gauche = true;
			break;
		case KeyEvent.VK_RIGHT:
			this.commandeEnCours.droite = true;
			this.commandeARetourner.droite = true;
			break;
		case KeyEvent.VK_UP:
			this.commandeEnCours.haut = true;
			this.commandeARetourner.haut = true;
			break;
		case KeyEvent.VK_DOWN:
			this.commandeEnCours.bas = true;
			this.commandeARetourner.bas = true;
			break;
		case KeyEvent.VK_SPACE:
			this.commandeEnCours.tir = true;
			this.commandeARetourner.tir = true;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			this.commandeEnCours.gauche = false;
			break;
		case KeyEvent.VK_RIGHT:
			this.commandeEnCours.droite = false;
			break;
		case KeyEvent.VK_UP:
			this.commandeEnCours.haut = false;
			break;
		case KeyEvent.VK_DOWN:
			this.commandeEnCours.bas = false;
			break;
		case KeyEvent.VK_SPACE:
			this.commandeEnCours.tir = false;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Ne fait rien
	}

}
