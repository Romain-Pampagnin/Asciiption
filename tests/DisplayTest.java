import Logique.*;
import Affichage.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class DisplayTest {
    ArrayList<Card> deck = new ArrayList<Card>();

    @org.junit.jupiter.api.Test
    public void test_hand(){
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());

        Player player1 = new Player("joueur", deck);

        player1.draw(1);
        assertEquals("Votre main:\n1. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0", Display.hand(player1));
    }

    @Test
    public void test_hand2(){
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());

        Player player1 = new Player("joueur", deck);

        player1.draw(2);
        assertEquals("Votre main:\n1. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0\n2. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0", Display.hand(player1));
    }

    @org.junit.jupiter.api.Test
    public void test_handLine(){
        deck.add(CardFactory.createEcureuil());
        Player player1 = new Player("joueur", deck);
        player1.draw(2);

        assertEquals("1. Ecureuil  PV: 1  Att: 0  Gouttes de sang: 0  Os: 0 ", Display.handLine(player1,0));
    }

}
