import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void test_getHealth(){
        Card eccureuil = new Card("Ecureuil",0,1,0,0,false);
        assertEquals(1,eccureuil.getHealth());
    }
    @Test
    public void test_getDamage(){
        Card coyote = new Card("Coyote",2,1,0,4,false);
        assertEquals(2,coyote.getDamage());
    }
    @Test
    public void test_isFlying(){
        Card coyote = new Card("Coyote",2,1,0,4,false);
        assertFalse(coyote.isFlying());
    }
    @Test
    public void test_takeDamage(){
        Card hermine = new Card("Hermine",1,3,1,0,false);
        hermine.takeDamage(2);
        assertEquals(1,hermine.getHealth());
    }
}
