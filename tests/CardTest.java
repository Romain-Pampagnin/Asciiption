import Logique.Card;
import Logique.CardFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void test_getHealth(){
        Card ecureuil = CardFactory.createEcureuil();
        assertEquals(1,ecureuil.getHealth());
    }

    @Test
    public void test_getHealth2(){
        Card carte = CardFactory.createRocher();
        assertEquals(5,carte.getHealth());
    }

    @Test
    public void test_getDamage(){
        Card coyote = CardFactory.createCoyote();
        assertEquals(2,coyote.getDamage());
    }

    @Test
    public void test_getDamage2(){
        Card carte = CardFactory.createRocher();
        assertEquals(0,carte.getDamage());
    }

    @org.junit.jupiter.api.Test
    public void test_isFlying(){
        Card coyote = CardFactory.createCoyote();
        assertFalse(coyote.getAbilities().contains("vol"));
    }
    @org.junit.jupiter.api.Test
    public void test_takeDamage(){
        Card hermine = CardFactory.createHermine();
        hermine.takeDamage(2);
        assertEquals(1,hermine.getHealth());
    }

    @Test
    public void test_takeDamage2(){
        Card carte = CardFactory.createRocher();
        carte.takeDamage(2);
        assertEquals(3,carte.getHealth());
    }

    @Test
    public void test_getExpendable(){
        Card obstacle = CardFactory.createRocher();
        assertFalse(obstacle.getExpendable());
    }

    @Test
    public void test_getExpendable2(){
        Card animal = CardFactory.createLoup();
        assertTrue(animal.getExpendable());
    }

    @Test
    public void test_getName(){
        Card coyote = CardFactory.createCoyote();
        assertEquals("Coyote",coyote.getName());
    }

    @Test
    public void test_getBones(){
        Card carte = CardFactory.createChat();
        assertEquals(0, carte.getBones());
    }

    @Test
    public void test_getBones2(){
        Card carte = CardFactory.createSapin();
        assertEquals(0, carte.getBones());
    }

    @org.junit.jupiter.api.Test
    public void test_getBlood(){
        Card carte = CardFactory.createChat();
        assertEquals(1, carte.getBlood());
    }

    @Test
    public void test_getBlood2(){
        Card carte = CardFactory.createSapin();
        assertEquals(0, carte.getBlood());
    }

    @Test
    public void test_attack() {
        Card carte1 = CardFactory.createPunaise();
        Card carte2 = CardFactory.createHermine();
        carte2.attack(carte1);
        assertEquals(2, carte1.getHealth());
    }

    @Test
    public void test_attack2() {
        Card carte1 = CardFactory.createSapin();
        Card carte2 = CardFactory.createHermine();
        carte2.attack(carte1);
        assertEquals(2, carte1.getHealth());
    }

    @Test
    public void test_attack3() {
        Card carte1 = CardFactory.createVipere();
        Card carte2 = CardFactory.createHermine();
        carte1.attack(carte2);
        assertEquals(0, carte2.getHealth());
    }

    @Test
    public void test_attack4() {
        Card carte1 = CardFactory.createVipere();
        Card carte2 = CardFactory.createPorcEpic();
        carte1.attack(carte2);
        assertEquals(0, carte1.getHealth());
    }

    @Test
    public void test_attack5() {
        Card carte1 = CardFactory.createVipere();
        Card carte2 = CardFactory.createPorcEpic();
        carte1.attack(carte2);
        assertEquals(0, carte2.getHealth());
    }

    @Test
    public void test_attack6() {
        Card carte1 = CardFactory.createVipere();
        Card carte2 = CardFactory.createPunaise();
        carte1.attack(carte2);
        assertEquals(0, carte2.getHealth());
    }

    @Test
    public void test_attack7() {
        Card carte1 = CardFactory.createEcureuil();
        Card carte2 = CardFactory.createPunaise();
        carte1.attack(carte2);
        assertEquals(2, carte2.getHealth());
    }

    @Test
    public void test_attack8() {
        Card carte1 = CardFactory.createCoyote();
        Card carte2 = CardFactory.createPunaise();
        carte1.attack(carte2);
        assertEquals(1, carte2.getHealth());
    }

    @Test
    public void test_toString(){
        Card carte1 = CardFactory.createVipere();
        assertEquals("Vipère(dmg=1, hp=1, blood=2, bones=0, abilities=[venin])",carte1.toString());
    }

    @Test
    public void test_toString2(){
        Card carte1 = CardFactory.createVipere();
        carte1.addAbility("vol");
        assertEquals("Vipère(dmg=1, hp=1, blood=2, bones=0, abilities=[venin, vol])",carte1.toString());
    }

}
