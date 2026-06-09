package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class Card {
    private final String m_name;
    private final int m_damage;
    private int m_health;
    private final int m_blood;
    private final int m_bones;
    private final boolean m_flying;
    private final boolean m_sacrifiable;
    private ArrayList<String> m_abilities;

    public Card(String n, int d, int h, int bl, int bo, boolean fly, ArrayList<String> abilities){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = true;
        m_abilities = abilities;
    }

    public Card(String n, int d, int h, int bl, int bo, boolean fly){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = true;
        m_abilities = new ArrayList<String>();
    }
    public Card(String n, int d, int h, int bl, int bo, boolean fly, boolean sacrifiable, ArrayList<String> abilities){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = sacrifiable;
    }

    public Card(String n, int d, int h, int bl, int bo, boolean fly, boolean sacrifiable){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = sacrifiable;
        m_abilities = new ArrayList<String>();
    }

    public Card(Card card){
        m_name = card.getName();
        m_damage = card.getDamage();
        m_health = card.m_health;
        m_blood = card.m_blood;
        m_bones = card.m_bones;
        m_flying = card.isFlying();
        m_sacrifiable = card.getExpendable();
        m_abilities = card.getAbilities();
    }

    public void takeDamage(int nbDamage){
        if(nbDamage > 0) {
            this.m_health -= nbDamage;
        }
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
        int damage = 0;
        int damageToAttacker = 0;
        int damageToDefender = 0;
        if(this.isFlying()) {
            damage = this.getDamage();
        }
        else{
            if(this.getAbilities().contains("contact mortel") && other.getExpendable()) {
                damageToDefender = other.getHealth();
            }
            if(other.getAbilities().contains("piques pointues")){
                damageToAttacker = 1;
            }
            if(!this.getAbilities().contains("contact mortel") && other.getAbilities().contains("puant")){
                damageToDefender = this.getDamage() -1;
            }
            if(!this.getAbilities().contains("contact mortel") && !other.getAbilities().contains("puant")){
                damageToDefender = this.getDamage();
            }
        }
        this.takeDamage(damageToAttacker);
        other.takeDamage(damageToDefender);
        return damage;
    }

    public boolean isDead(){
        return this.getHealth() <= 0;
    }

    public ArrayList<String> getAbilities(){
        return m_abilities;
    }

    public void addAbility(String ability){
        this.m_abilities.add(ability);
    }

    @Override
    public String toString(){
        return this.m_name + "(dmg=" + this.m_damage + ", hp=" + this.getHealth() + ", blood="
                + this.getBlood() + ", bones=" + this.getBones() + ", flying=" + this.isFlying() +")";
    }
}