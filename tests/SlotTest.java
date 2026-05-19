import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Optional;

public class SlotTest {
    Slot slot1 = new Slot();
    Slot slot2 = new Slot();
    Card coyote = Card.createCoyote();
    Card ecureuil = Card.createEcureuil();
    Card corbeau = Card.createCorbeau();
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
    public void test_removeCard(){
        assertFalse(slot1.removeCard());
    }
    @Test
    public void test_removeCard2(){
        slot1.addCard(coyote);
        assertTrue(slot1.removeCard());
    }
    @Test
    public void test_attack(){
        slot1.addCard(ecureuil);
        slot2.addCard(coyote);
        assertEquals(0,slot1.attack(slot2));
    }
    @Test
    public void test_attack2(){
        slot1.addCard(coyote);
        slot2.addCard(ecureuil);
        assertEquals(0,slot1.attack(slot2));
    }
    @Test
    public void test_attack3(){
        slot1.addCard(coyote);
        slot2.addCard(ecureuil);
        slot1.attack(slot2);
        assertEquals(Optional.empty(),slot2.getCard());
    }
    @Test
    public void test_attack4(){
        slot1.addCard(coyote);
        slot2.addCard(corbeau);
        assertEquals(2,slot2.attack(slot1));
    }
}
