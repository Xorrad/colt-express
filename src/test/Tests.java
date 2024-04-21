package test;

import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Toigon;
import main.modeles.entites.Entite;
import main.modeles.entites.Sheriff;
import main.modeles.entites.Tresor;
import main.vues.Assets;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testTrain() {
        Modele m = new Modele();

        // Teste que la taille du train (chaque Toigons compte pour 1, donc un wagon et son toit compte pour 2) est bien égal à 2 fois tailleTrain-1
        // car la locomotive n'a pas de toit.
        assertEquals(2*Modele.NB_WAGONS, m.getTrain().length);

        // Teste que l'initialisation du train marche bien (indices, toits et voisins).
        for (int i = 0; i < 2*Modele.NB_WAGONS; i++){
            Toigon t = m.getToigon(i);

            assertEquals(i%2 == 1, t.estToit());
            assertEquals(i, t.getNumero());

            // Teste voisin HAUT et BAS.
            if(t.estToit()) {
                assertNull(t.getVoisin(Direction.HAUT));
                assertEquals(t.getVoisin(Direction.BAS), m.getToigon(i-1));
            }
            else {
                assertEquals(t.getVoisin(Direction.HAUT), m.getToigon(i+1));
                assertNull(t.getVoisin(Direction.BAS));
            }

            // Teste voisins ARRIERE
            if(i > 1) assertEquals(t.getVoisin(Direction.ARRIERE), m.getToigon(i - 2));
            else assertNull(t.getVoisin(Direction.ARRIERE));

            // Teste voisins AVANT
            if(i < 2*Modele.NB_WAGONS-2) assertEquals(t.getVoisin(Direction.AVANT), m.getToigon(i + 2));
            else assertNull(t.getVoisin(Direction.AVANT));
        }

        assertEquals(m.getLocomotive(), m.getToigon(2*Modele.NB_WAGONS-2));
    }

    @Test
    public void testToigon() {
        Modele m = new Modele();
        Toigon toigon = m.getToigon(2);
        Tresor tresor = new Tresor(0, toigon, "Bijoux", 500, Assets.IMG_TRESORS_BIJOUX);

        assertFalse(toigon.contientEntite(tresor));
        toigon.ajouteEntite(tresor);
        assertTrue(toigon.contientEntite(tresor));
        toigon.retireEntite(tresor);
        assertFalse(toigon.contientEntite(tresor));

        toigon.retireEntites(Entite.Type.TRESOR);
        assertEquals(toigon.getEntites(Entite.Type.TRESOR).size(), 0);
        toigon.ajouteEntite(tresor);
        assertEquals(toigon.getEntites(Entite.Type.TRESOR).size(), 1);
        assertEquals(toigon.getRandomEntite(Entite.Type.TRESOR), tresor);

        // Les numéros, toits et voisins des toigons sont testés dans testTrain().
    }

    @Test
    public void testEntite() {
        Modele m = new Modele();
        Toigon toigon = m.getToigon(2);
        Sheriff sheriff = new Sheriff(17, toigon, "Shériff");
        toigon.ajouteEntite(sheriff);

        // Teste attributs du sheriff.
        assertEquals(sheriff.getNum(), 17);
        assertEquals(sheriff.getNom(), "Shériff");
        assertEquals(sheriff.getType(), Entite.Type.SHERIFF);

        // Teste déplacements depuis wagon milieu
        assertEquals(sheriff.getToigon(), toigon);
        assertTrue(sheriff.peutDeplacer(Direction.AVANT));
        assertTrue(sheriff.peutDeplacer(Direction.ARRIERE));
        assertTrue(sheriff.peutDeplacer(Direction.HAUT));
        assertFalse(sheriff.peutDeplacer(Direction.BAS));

        // Teste déplacements depuis toit milieu
        sheriff.deplace(Direction.HAUT);
        assertTrue(sheriff.peutDeplacer(Direction.BAS));
        assertFalse(sheriff.peutDeplacer(Direction.HAUT));

        // Teste si l'entité est retirée/ajoutée aux listes des toigons.
        assertEquals(sheriff.getToigon(), m.getToigon(3));
        assertFalse(toigon.contientEntite(sheriff));
        assertTrue(m.getToigon(3).contientEntite(sheriff));

        // Teste déplacement dernier wagon.
        sheriff.deplace(Direction.ARRIERE);
        assertFalse(sheriff.peutDeplacer(Direction.ARRIERE));

        // Teste déplacement locomotive.
        sheriff.getToigon().retireEntite(sheriff);
        sheriff.setToigon(m.getToigon(m.getLocomotive().getNumero()+1));
        sheriff.getToigon().ajouteEntite(sheriff);
        assertFalse(sheriff.peutDeplacer(Direction.AVANT));
        sheriff.deplace(Direction.BAS);
        assertFalse(sheriff.peutDeplacer(Direction.AVANT));
        sheriff.deplace(Direction.ARRIERE);
        assertTrue(sheriff.peutDeplacer(Direction.AVANT));
    }
}
