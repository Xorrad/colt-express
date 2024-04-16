package test;

import main.modeles.Direction;
import main.modeles.Modele;
import main.modeles.Toigon;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testTrain() {
        Modele m = new Modele();

        // Teste que la taille du train (chaque Toigons compte pour 1, donc un wagon et son toit compte pour 2) est bien égal à 2 fois tailleTrain
        assertEquals(2*m.getTailleTrain(), m.getTrain().length);

        // Teste que l'initialisation du train marche bien (indices, toits et voisins).
        for (int i = 0; i < 2*m.getTailleTrain(); i++){
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
            if(i < 2*m.getTailleTrain()-2) assertEquals(t.getVoisin(Direction.AVANT), m.getToigon(i + 2));
            else assertNull(t.getVoisin(Direction.AVANT));
        }
    }

}
