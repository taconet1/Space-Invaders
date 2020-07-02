package fr.unilim.iut.spaceinvaders.moteurjeu;

import java.awt.image.BufferedImage;

/**
 * Une interface représentant la manière de dessiner sur un JPanel
 * @author vthomas
 */

public interface DessinJeu {
	public abstract void dessinerImageJeu(BufferedImage imageSurLaquelleDessiner);

}
