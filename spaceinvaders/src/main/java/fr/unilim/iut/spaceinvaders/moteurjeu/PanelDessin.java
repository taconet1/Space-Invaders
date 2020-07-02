package fr.unilim.iut.spaceinvaders.moteurjeu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelDessin extends JPanel {

	private DessinJeu dessin;
	private BufferedImage imageSuivanteAAfficher;
	private BufferedImage imageActuelle;
	private int longueurImage;
	private int hauteurImage;

	public PanelDessin(int longueurImage, int hauteurImage, DessinJeu dessin) {
		super();
		this.setPreferredSize(new Dimension(longueurImage, hauteurImage));
		this.longueurImage = longueurImage;
		this.hauteurImage = hauteurImage;
		this.dessin=dessin;

		this.imageSuivanteAAfficher = new BufferedImage(longueurImage, hauteurImage,
				BufferedImage.TYPE_INT_RGB);
		this.imageActuelle = new BufferedImage(longueurImage, hauteurImage,
				BufferedImage.TYPE_INT_RGB);
	}

	public void dessinerJeu() {
		this.dessin.dessinerImageJeu(this.imageSuivanteAAfficher);

		BufferedImage temp = this.imageActuelle;
		this.imageActuelle = this.imageSuivanteAAfficher;
		this.imageSuivanteAAfficher = temp;
		this.imageSuivanteAAfficher.getGraphics().fillRect(0, 0, this.longueurImage, this.hauteurImage);
		
		this.repaint();
	}

	// Dessiner image actuelle
	@Override
	public void paint(Graphics graphique) {
		super.paint(graphique);
		graphique.drawImage(this.imageActuelle, 0, 0, getWidth(), getHeight(), 0, 0, getWidth(), getHeight(), null);
	}

}
