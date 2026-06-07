package Logique;

import java.util.Optional;

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

    public void attack(Optional<Card> other) {
        other.get().takeDamage(this.m_damage);
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

    public int attack(Card other){
        if(this.isFlying()) {
            return this.getDamage();
        }
        else{
            other.takeDamage(this.getDamage());
            return 0;
        }
    }

    public boolean isDead(){
        return this.getHealth() <= 0;
    }

    @Override
    public String toString(){
        return this.m_name + "(dmg=" + this.m_damage + ", hp=" + this.getHealth() + ", blood="
                + this.getBlood() + ", bones=" + this.getBones() + ", flying=" + this.isFlying() +")";
    }
}