import Affichage.*;
import Logique.*;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        board1.addToSlot(2,CardFactory.createRocher());

        System.out.println(Display.Board(board1));

        Round round1 = new Round("yolo", 1);
        System.out.println(Display.Global(round1));
    }
}