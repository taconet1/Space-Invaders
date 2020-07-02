package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import utils.DebordementEspaceJeuException;
import utils.EnvahisseurException;
import utils.HorsEspaceJeuException;
import utils.MissileException;

public class SpaceInvadersTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		assertEquals("" + 
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEtUnNouveauEnvahisseurAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
	 	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 1);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(7, 2), 1);
		assertEquals("" +
				"...............\n" +
				".......EE......\n" +
				".......EE......\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				".......VVV.....\n" +
				".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(15, 9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(-1, 9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, 10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1, 1), new Position(14, -1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

	}

	@Test
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite_AvecVitesse() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"..........VVV..\n" +
				"..........VVV..\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvecDimensionImmobile_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(12, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"............VVV\n" +
				"............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(10, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaDroite();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"............VVV\n" +
				"............VVV\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvecDimensionAvance_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(7, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"....VVV........\n" +
				"....VVV........\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_VaisseauAvecDimensionImmobile_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(0, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"VVV............\n" +
				"VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(1, 9), 3);
		spaceinvaders.deplacerVaisseauVersLaGauche();
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"VVV............\n" +
				"VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9, 2), new Position(7, 9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 4), new Position(7, 1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}
	}
	
	@Test
	public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 2);
		assertEquals("" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				"...............\n" +
				".......MMM.....\n" +
				".......MMM.....\n" +
				".....VVVVVVV...\n" +
				".....VVVVVVV...\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(7,9),1);
	}
	 
	 @Test
	 public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {
		 
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 2);
		spaceinvaders.tirerUnMissile(new Dimension(3,2),2);

		spaceinvaders.deplacerMissile();
		   
	    assertEquals("" + 
	    "...............\n" + 
	    "...............\n" +
	    "...............\n" + 
	    "...............\n" + 
	    ".......MMM.....\n" + 
	    ".......MMM.....\n" + 
	    "...............\n" + 
	    "...............\n" + 
	    ".....VVVVVVV...\n" + 
	    ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	  }
	 
	 @Test
	  public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {
		 spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
		 spaceinvaders.tirerUnMissile(new Dimension(3,2),1);
		 for (int i = 1; i <= 6 ; i++) {
			 spaceinvaders.deplacerMissile();
		 }
		   
		 spaceinvaders.deplacerMissile();
		   
	     assertEquals("" +
	     "...............\n" + 
	     "...............\n" +
	     "...............\n" + 
	     "...............\n" +
	     "...............\n" +
	     "...............\n" + 
	     "...............\n" +
	     "...............\n" + 
	     ".....VVVVVVV...\n" + 
	     ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 
	 
	 @Test(expected = EnvahisseurException.class)
		public void test_EnvahisseurSeSuperposeSurVaisseau_UneExceptionEstLevee() throws Exception { 
		 	spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 2);
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(2, 2), new Position(7, 7), 1);
		}
	 
	
	 
	 
}