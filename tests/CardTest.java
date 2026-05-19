import Logique.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void test_getHealth(){
        Card ecureuil = Card.createEcureuil();
        assertEquals(1,ecureuil.getHealth());
    }
    @Test
    public void test_getDamage(){
        Card coyote = Card.createCoyote();
        assertEquals(2,coyote.getDamage());
    }
    @Test
    public void test_isFlying(){
        Card coyote = Card.createCoyote();
        assertFalse(coyote.isFlying());
    }
    @Test
    public void test_takeDamage(){
        Card hermine = Card.createHermine();
        hermine.takeDamage(2);
        assertEquals(1,hermine.getHealth());
    }
}
