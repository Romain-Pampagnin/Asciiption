import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board1 = new Board();
    Board board2 = new Board();
    @Test
    public void test_addToSlot(){
        assertTrue(board1.addToSlot(0,Card.createCorbeau()));
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
        assertEquals(0,board2.attack(board1));
    }
    @Test
    public void test_attack3(){
        board1.addToSlot(0,Card.createCorbeau());
        board2.addToSlot(0,Card.createCoyote());
        assertEquals(0,board2.attack(board1));
    }
    @Test
    public void test_attack4(){
        board1.addToSlot(0,Card.createCorbeau());
        board2.addToSlot(0,Card.createCoyote());
        assertEquals(0,board2.attack(board1));
    }
    @Test
    public void test_sacrifice(){}
}
