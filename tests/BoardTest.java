import Logique.Board;
import Logique.Card;
import Logique.CardFactory;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BoardTest {
    private Board board1 = new Board(4);
    private Board board2 = new Board(4);


    @Test
    public void test_getCards(){
        Optional<Card>[] attendu = new Optional[4];
        for(int i = 0; i < attendu.length; i ++){
            attendu[i] = Optional.empty();
        }
        assertEquals(attendu,board1.getCards());
    }

//    @Test
//    public void test_getCards2() throws Exception {
//        board1.addToBoard(0, CardFactory.createEcureuil());
//        board2.addToBoard(0, CardFactory.createEcureuil());
//        assertEquals(board1, board2);
//    }

    @Test
    public void test_addToBoard() throws Exception {
        board1.addToBoard(0, CardFactory.createCorbeau());
        assertNotEquals(board1, board2);
    }

}