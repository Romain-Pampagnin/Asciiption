import Logique.Card;
import Logique.CardFactory;
import Logique.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Player joueur = new Player("",deck);
    @Test
    public void test_draw(){
        joueur.getDeck().add(CardFactory.createCoyote());
        assertEquals(1,joueur.draw(1));
    }
    @Test
    public void test_draw1(){
        assertEquals(0,joueur.draw(1));
    }
    @org.junit.jupiter.api.Test
    public void test_getScore(){
        assertEquals(0,joueur.getScore());
    }
    @org.junit.jupiter.api.Test
    public void test_getBonesFromGrave(){
        assertEquals(0,joueur.getBonesFromGrave());
    }
    @Test
    public void test_getHand(){
        assertEquals(new ArrayList<Card>(),joueur.getHand());
    }
    @org.junit.jupiter.api.Test
    public void test_getDeck(){
        assertEquals(new ArrayList<Card>(),joueur.getDeck());
    }
    @Test
    public void test_getBoard(){
        Optional[] board = new Optional[4];
        for(int i = 0; i < 4; i++){
            board[i] = Optional.empty();
        }
        assertEquals(board,joueur.getBoard());
    }
    @org.junit.jupiter.api.Test
    public void test_sacrifice() {
        assertThrows(Exception.class,()->{joueur.sacrifice(1);});
    }
    @org.junit.jupiter.api.Test
    public void test_sacrifice1() {
        joueur.getDeck().add(CardFactory.createEcureuil());
        try {
            joueur.play(0,0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()->{joueur.sacrifice(1);});
    }
}
