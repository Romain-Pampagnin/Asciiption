import Logique.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {
    private Deck deck = new Deck();
    private Player joueur = new Player("",deck);
    private Player bot = new Player("adversaire",deck);
    @Test
    public void test_draw(){
        joueur.getDeck().addCard(CardFactory.createCoyote());
        assertEquals(1,joueur.draw(1));
    }
    @Test
    public void test_draw1(){
        assertEquals(0,joueur.draw(1));
    }
    @Test
    public void test_getScore(){
        assertEquals(0,joueur.getScore());
    }
    @Test
    public void test_getBonesFromGrave(){
        assertEquals(0,joueur.getBonesFromGrave());
    }
    @Test
    public void test_getHand(){
        assertEquals(new ArrayList<Card>(),joueur.getHand());
    }
    @Test
    public void test_getDeck(){
        assertEquals(new Deck(),joueur.getDeck());
    }
    //pour le test ci-dessous il va falloir recoder la méthode equals pour la classe Board (et donc faire une méthode toString surchargée)
//    @Test
//    public void test_getBoard(){
//        Board testBoard = new Board(4);
//        assertEquals(testBoard,joueur.getBoard());
//    }
    @Test
    public void test_boardHasEmptySlot(){
        assertTrue(joueur.boardHasEmptySlot());
    }
    @Test
    public void test_boardHasEmptySlot1(){
        for(int i = 0; i < 4; i++){
            joueur.getBoard().getCards()[i] = Optional.of(CardFactory.createChat());
        }
        assertFalse(joueur.boardHasEmptySlot());
    }
    @Test
    public void test_getTotalBlood(){assertEquals(0,joueur.getTotalBlood());}
    @Test
    public void test_incrementTotalBlood(){joueur.incrementTotalBlood(1);assertEquals(1,joueur.getTotalBlood());}
    @Test
    public void test_incrementTotalBlood2(){joueur.incrementTotalBlood(100);assertEquals(100,joueur.getTotalBlood());}
    @Test
    public void test_resetTotalBlood(){joueur.incrementTotalBlood(100);joueur.resetTotalBlood();assertEquals(0,joueur.getTotalBlood());}
    @Test
    public void test_decreaseTotalBlood(){joueur.incrementTotalBlood(100);joueur.decreaseTotalBlood(50);assertEquals(50,joueur.getTotalBlood());}
    @Test
    public void test_isCardPlayable(){assertTrue(joueur.isCardPlayable(CardFactory.createEcureuil()));}
    @Test
    public void test_isCardPlayable2(){assertFalse(joueur.isCardPlayable(CardFactory.createChat()));}
    @org.junit.jupiter.api.Test
    public void test_playCard(){ assertThrows(Exception.class,()->{joueur.playCard(0,0);});}
    @org.junit.jupiter.api.Test
    public void test_playCard1(){
        joueur.getDeck().addCard(CardFactory.createEcureuil());
        joueur.draw(1);
        assertDoesNotThrow(()->{joueur.playCard(0,0);});
    }
    @org.junit.jupiter.api.Test
    public void test_playCard2(){
        joueur.getDeck().addCard(CardFactory.createEcureuil());
        joueur.draw(1);
        try {
            joueur.playCard(0,0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(Exception.class,()->{joueur.playCard(0,0);});
    }
    @org.junit.jupiter.api.Test
    public void test_sacrifice() {
        assertThrows(Exception.class,()->{joueur.sacrifice(1);});
    }
    @org.junit.jupiter.api.Test
    public void test_sacrifice1() {
        joueur.getDeck().addCard(CardFactory.createEcureuil());
        joueur.draw(1);
        try {
            joueur.playCard(0,0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertDoesNotThrow(()->{joueur.sacrifice(0);});
    }
    @Test
    public void test_attack() throws Exception {
        joueur.getBoard().getCards()[0] = Optional.of(CardFactory.createPunaise());
        joueur.attack(bot);
        assertEquals(1,joueur.getScore());
    }
    @Test
    public void test_attack1() throws Exception {
        joueur.getBoard().getCards()[0] = Optional.of(CardFactory.createPunaise());
        bot.getBoard().getCards()[0] = Optional.of(CardFactory.createEcureuil());
        joueur.attack(bot);
        assertEquals(0,joueur.getScore());
    }
    @Test
    public void test_attack2() throws Exception {
        joueur.getBoard().getCards()[0] = Optional.of(CardFactory.createPunaise());
        bot.getBoard().getCards()[0] = Optional.of(CardFactory.createEcureuil());
        joueur.attack(bot);
        assertEquals(Optional.empty(),bot.getBoard().getCards()[0]);
    }
    @Test
    public void test_attack3() throws Exception {
        joueur.getBoard().getCards()[0] = Optional.of(CardFactory.createMoineau());
        bot.getBoard().getCards()[0] = Optional.of(CardFactory.createEcureuil());
        joueur.attack(bot);
        assertEquals(1,joueur.getScore());
    }
    @Test
    public void test_attack4() throws Exception {
        Optional<Card> ecureuil = Optional.of(CardFactory.createEcureuil());
        joueur.getBoard().getCards()[0] = Optional.of(CardFactory.createMoineau());
        bot.getBoard().getCards()[0] = ecureuil;
        joueur.attack(bot);
        assertEquals(ecureuil,bot.getBoard().getCards()[0]);
    }
}
