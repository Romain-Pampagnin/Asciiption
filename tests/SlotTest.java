import Logique.Card;
import Logique.CardFactory;
import Logique.Slot;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import static org.junit.Assert.*;

import java.util.Optional;

public class SlotTest {
    Slot slot1 = new Slot();
    Slot slot2 = new Slot();
    Card coyote = CardFactory.createCoyote();
    Card ecureuil = CardFactory.createEcureuil();
    Card corbeau = CardFactory.createCorbeau();
    @Test
    public void test_getCard(){
        assertEquals(Optional.empty(),slot1.getCard());
    }
    @Test
    public void test_getCard2(){
        slot1.addCard(coyote);
        assertEquals(Optional.of(coyote),slot1.getCard());
    }
    @Test
    public void test_addCard(){
        assertTrue(slot1.addCard(coyote));
    }
    @Test
    public void test_addCard2(){
        slot1.addCard(coyote);
        assertFalse(slot1.addCard(ecureuil));
    }
    @Test
    public void test_removeCard() throws Exception {
        assertEquals(Optional.empty(), slot1.removeCard());
    }
    @Test
    public void test_removeCard2() throws Exception {
        slot1.addCard(coyote);
        assertEquals(coyote, slot1.removeCard());
    }
    @Test
    public void test_attack() throws Exception {
        slot1.addCard(ecureuil);
        slot2.addCard(coyote);
        assertEquals(0,slot1.attack(slot2));
    }
    @Test
    public void test_attack2() throws Exception {
        slot1.addCard(coyote);
        slot2.addCard(ecureuil);
        assertEquals(0,slot1.attack(slot2));
    }
    @Test
    public void test_attack3() throws Exception {
        slot1.addCard(coyote);
        slot2.addCard(ecureuil);
        slot1.attack(slot2);
        assertEquals(Optional.empty(),slot2.getCard());
    }
    @Test
    public void test_attack4() throws Exception {
        slot1.addCard(coyote);
        slot2.addCard(corbeau);
        assertEquals(2,slot2.attack(slot1));
    }

//    @Test
//    public void test_removeCard3() throws Exception {
//        Exception error = new Exception("Le slot ne contient pas de carte, ou une carte non sacrifiable");
//        assertThrows("", error, slot1.removeCard());
//    }
}
