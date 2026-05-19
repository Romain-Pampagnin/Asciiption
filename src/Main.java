import Affichage.*;
import Logique.*;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        System.out.println("Another challenger... It has been ages.");
        ArrayList<Card> deck = new ArrayList<Card>();

        deck.add(Card.createEcureuil());
        deck.add(Card.createEcureuil());
        deck.add(Card.createEcureuil());
        deck.add(Card.createEcureuil());

        Player player1 = new Player("joueur", deck);
        player1.draw(4);

        System.out.println(Display.Hand(player1));
    }
}