import Logique.Board;
import Logique.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board1 = new Board();
    Board board2 = new Board();
    @Test
    public void test_addToSlot(){
        assertTrue(board1.addToSlot(0, Card.createCorbeau()));
    }
    @Test
    public void test_addToSlot2(){
        assertTrue(board1.addToSlot(0,Card.createCorbeau()));
        assertFalse(board1.addToSlot(0,Card.createCorbeau()));
    }
    @Test
    public void test_attack(){
        board1.addToSlot(0,Card.createCorbeau());
        board2.addToSlot(0,Card.createCoyote());
        assertEquals(2,board1.attack(board2));
    }
    @Test
    public void test_attack2(){
        board1.addToSlot(0,Card.createCorbeau());
        board2.addToSlot(0,Card.createCoyote());
        board1.addToSlot(1,Card.createEcureuil());
        board2.addToSlot(2,Card.createHermine());
        board1.addToSlot(3,Card.createCoyote());
        board2.addToSlot(3,Card.createGrizzly());
        assertEquals(2,board1.attack(board2));
    }
    @Test
    public void test_attack3(){
        board1.addToSlot(0,Card.createCorbeau());
        board2.addToSlot(0,Card.createCoyote());
        board1.addToSlot(1,Card.createEcureuil());
        board2.addToSlot(2,Card.createHermine());
        board1.addToSlot(3,Card.createCoyote());
        board2.addToSlot(3,Card.createGrizzly());
        assertEquals(1,board2.attack(board1));
    }
    @Test
    public void test_sacrifice(){
        assertFalse(board1.sacrifice(1));
    }
    @Test
    public void test_sacrifice2(){
        board1.addToSlot(1,Card.createLoup());
        assertTrue(board1.sacrifice(1));
    }
    @Test
    public void test_sacrifice3(){
        board1.addToSlot(1,Card.createCorbeau());
        board1.sacrifice(1);
        assertTrue(board1.addToSlot(1,Card.createCorbeau()));
    }
}
