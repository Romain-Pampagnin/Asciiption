import Affichage.*;
import Logique.*;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        System.out.println("Another challenger... It has been ages.");
        ArrayList<Card> deck = new ArrayList<Card>();

        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createEcureuil());
        deck.add(CardFactory.createLoup());
        deck.add(CardFactory.createCoyote());

        Player player1 = new Player("joueur", deck);
        player1.draw(4);

        System.out.println(Display.Hand(player1));

        Board board1 = new Board();
        board1.addToSlot(0, CardFactory.createEcureuil());

        System.out.println(Display.Board(board1));
        System.out.println(Display.Player(player1));
    }
}