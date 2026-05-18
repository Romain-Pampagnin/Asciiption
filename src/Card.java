import java.lang.reflect.Array;

public class Card {
    private final String m_name;
    private final int m_damage;
    private int m_health;
    private final int m_blood;
    private final int m_bones;
    private final boolean m_flying;

    public Card(String n, int d, int h, int bl, int bo, boolean fly){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
    }
    public int getHealth(){
        return m_health;
    }
    public int getDamage(){
        return m_damage;
    }
    public boolean isFlying(){
        return m_flying;
    }
}