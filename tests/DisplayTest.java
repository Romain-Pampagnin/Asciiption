import Logique.*;
import Affichage.*;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class DisplayTest {
    ArrayList<Card> deck = new ArrayList<Card>();

    @Test
    public void test_hand(){
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());

        Player player1 = new Player("joueur", deck);

        player1.draw(1);
        assertEquals("Votre main:\n1. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0", Display.Hand(player1));
    }

    @Test
    public void test_hand2(){
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());

        Player player1 = new Player("joueur", deck);

        player1.draw(2);
        assertEquals("Votre main:\n1. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0\n2. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0", Display.Hand(player1));
    }

    @Test
    public void test_HandLine(){
        deck.add(CardFactory.createEcureuil());
        Player player1 = new Player("joueur", deck);
        player1.draw(1);

        assertEquals("1. Ecureuil  PV: 1  Att: 0  Gouttes de sang: 0  Os: 0 ", Display.HandLine(player1,0));
    }

}
