import Logique.*;
import Affichage.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class DisplayTest {
    Deck deck = new Deck();

    @org.junit.jupiter.api.Test
    public void test_hand(){
        deck.addCard(CardFactory.createEcureuil());
        deck.addCard(CardFactory.createEcureuil());
        deck.addCard(CardFactory.createEcureuil());
        deck.addCard(CardFactory.createEcureuil());

        Joueur player1 = new Human("joueur", deck);

        player1.draw(1);
        assertEquals("Votre main:\n1. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0", Display.hand(player1));
    }

    @Test
    public void test_hand2(){
        deck.addCard(CardFactory.createEcureuil());
        deck.addCard(CardFactory.createEcureuil());
        deck.addCard(CardFactory.createEcureuil());
        deck.addCard(CardFactory.createEcureuil());

        Joueur player1 = new Human("joueur", deck);

        player1.draw(2);
        assertEquals("Votre main:\n1. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0\n2. Ecureuil PV: 1 Att: 0 Gouttes de sang: 0 Os: 0", Display.hand(player1));
    }

    @org.junit.jupiter.api.Test
    public void test_handLine(){
        deck.addCard(CardFactory.createEcureuil());
        Joueur player1 = new Human("joueur", deck);
        player1.draw(2);

        assertEquals(" 1. Ecureuil  PV: 1  Att: 0  Gouttes de sang: 0  Os: 0 ", Display.handLine(player1,0));
    }

}
