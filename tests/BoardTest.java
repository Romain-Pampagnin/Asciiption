import Logique.Board;
import Logique.Card;
import Logique.CardFactory;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BoardTest {
    Board board1 = new Board();
    Board board2 = new Board();
    Card corbac = CardFactory.createCorbeau();

    @Test
    public void test_addToSlot(){
        assertTrue(board1.addToSlot(0, CardFactory.createCorbeau()));
    }
    @Test
    public void test_addToSlot2(){
        assertTrue(board1.addToSlot(0,CardFactory.createCorbeau()));
        assertFalse(board1.addToSlot(0,CardFactory.createCorbeau()));
    }
    @Test
    public void test_attack(){
        board1.addToSlot(0,CardFactory.createCorbeau());
        board2.addToSlot(0,CardFactory.createCoyote());
        assertEquals(2,board1.attack(board2));
    }
    @Test
    public void test_attack2(){
        board1.addToSlot(0,CardFactory.createCorbeau());
        board2.addToSlot(0,CardFactory.createCoyote());
        board1.addToSlot(1,CardFactory.createEcureuil());
        board2.addToSlot(2,CardFactory.createHermine());
        board1.addToSlot(3,CardFactory.createCoyote());
        board2.addToSlot(3,CardFactory.createGrizzly());
        assertEquals(2,board1.attack(board2));
    }
    @Test
    public void test_attack3(){
        board1.addToSlot(0,CardFactory.createCorbeau());
        board2.addToSlot(0,CardFactory.createCoyote());
        board1.addToSlot(1,CardFactory.createEcureuil());
        board2.addToSlot(2,CardFactory.createHermine());
        board1.addToSlot(3,CardFactory.createCoyote());
        board2.addToSlot(3,CardFactory.createGrizzly());
        assertEquals(1,board2.attack(board1));
    }

    @Test
    public void test_sacrifice(){
        board1.addToSlot(1, corbac);
        assertEquals(corbac,board1.sacrifice(1));
    }

    @Test
    public void test_sacrifice2(){
        board1.addToSlot(1,CardFactory.createCorbeau());
        board1.sacrifice(1);
        assertTrue(board1.addToSlot(1,CardFactory.createCorbeau()));
    }
}
