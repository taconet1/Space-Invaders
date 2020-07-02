package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu {
	private SpaceInvaders jeu;

	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.jeu = spaceInvaders;
	}

	@Override
	public void dessinerImageJeu(BufferedImage image) {
		if (this.jeu.aUnVaisseau() != null) {
			this.dessinerUnVaisseau(this.jeu.recupererVaisseau(), image);
		}
		if (this.jeu.aUnMissile() != null) {
			this.dessinerUnMissile(this.jeu.recupererMissile(), image);
		}
		if (this.jeu.aUnEnvahisseur() != null) {
			this.dessinerUnEnvahisseur(this.jeu.recupererEnvahisseur(), image);
		}
	}

	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();

		crayon.setColor(Color.gray);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(),
				vaisseau.hauteur());

	}
	
	private void dessinerUnMissile(Missile missile, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();

		crayon.setColor(Color.blue);
		crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(),
				missile.hauteur());
	}
	
	private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();

		crayon.setColor(Color.black);
		crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(),
				envahisseur.hauteur());
	}
}
