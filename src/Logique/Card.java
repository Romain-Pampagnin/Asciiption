package Logique;

public class Card {
    private final String m_name;
    private final int m_damage;
    private int m_health;
    private final int m_blood;
    private final int m_bones;
    private final boolean m_flying;
    private final boolean m_sacrifiable;

    public Card(String n, int d, int h, int bl, int bo, boolean fly){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = true;
    }
    public Card(String n, int d, int h, int bl, int bo, boolean fly, boolean sacrifiable){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = sacrifiable;
    }

    public void takeDamage(int nbDamage){
        this.m_health -= nbDamage;
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
    public int getBones(){return m_bones; }
    public int getBlood(){return m_blood;}
    public String getName(){ return m_name;}
    public boolean getExpendable(){return m_sacrifiable;}
}