package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import utils.DebordementEspaceJeuException;
import utils.EnvahisseurException;
import utils.HorsEspaceJeuException;
import utils.MissileException;

public class SpaceInvaders implements Jeu {
	private int longueur;
	private int hauteur;
	private Vaisseau vaisseau;
	private Missile missile;
	private Envahisseur envahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		this.positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
		
		Position positionEnvahisseur = new Position(this.longueur / 2, this.hauteur - 25);
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_LONGUEUR);
		this.positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < this.hauteur; y++) {
			for (int x = 0; x < this.longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x,y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;

		return marque;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() != null && this.envahisseur.occupeLaPosition(x, y);
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() != null && this.missile.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() != null && this.vaisseau.occupeLaPosition(x, y);
	}

	public Missile aUnMissile() {
		return this.missile;
	}

	public Missile recupererMissile() {
		return this.missile;
	}

	public Vaisseau aUnVaisseau() {
		return this.vaisseau;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}
	
	public Envahisseur aUnEnvahisseur() {
		return this.envahisseur;
	}
	
	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (this.vaisseau.abscisseLaPlusADroite() < (this.longueur - 1)) {
			this.vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(this.vaisseau.abscisseLaPlusADroite(), this.vaisseau.ordonneeLaPlusHaute())) {
				this.vaisseau.positionner(this.longueur - this.vaisseau.longueur(),
						this.vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (this.vaisseau.abscisseLaPlusAGauche() > 0) {
			this.vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
				this.vaisseau.positionner(0, this.vaisseau.ordonneeLaPlusHaute());
			}
		}

	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		leverDesExceptionsQuandUnSpriteEstHorsEspaceJeu("Vaisseau",dimension, position);
		this.vaisseau = new Vaisseau(dimension, position, vitesse);
	}
	
	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		leverDesExceptionsQuandUnSpriteEstHorsEspaceJeu("Envahisseur",dimension, position);
		if (position.y + dimension.hauteur >= this.vaisseau.ordonneeLaPlusHaute())
			throw new EnvahisseurException("La position de l'envahisseur a atteint l'ordonnée du vaisseau");
		
		this.envahisseur = new Envahisseur(dimension, position, vitesse);
	}

	@Override
	public void evoluerLeJeuSelonLaCommandeDeLUtilisateur(Commande commandeUser) {
		if (commandeUser.gauche) {
			this.deplacerVaisseauVersLaGauche();
		}
		if (commandeUser.droite) {
			this.deplacerVaisseauVersLaDroite();
		}
		if (commandeUser.tir && this.aUnMissile() == null) {
			this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		}
		if (this.aUnMissile() != null) {
			this.deplacerMissile();
		}
	}

	@Override
	public boolean estFini() {
		return false;
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
	}

	public void deplacerMissile() {
		if (!this.missile.occupeLaPosition(this.missile.origine.abscisse(), 0)) {
			this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		} else {
			this.missile = null;
		}
	}

	private void leverDesExceptionsQuandUnSpriteEstHorsEspaceJeu(String nomSprite, Dimension dimension, Position position) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de "+nomSprite+" est en dehors de l'espace jeu");

		int longueurSprite = dimension.longueur();
		int hauteurSprite = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurSprite - 1, y))
			throw new DebordementEspaceJeuException(
					nomSprite+" déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurSprite + 1))
			throw new DebordementEspaceJeuException(
					nomSprite+" déborde de l'espace jeu vers le bas à cause de sa hauteur");
	}

}
