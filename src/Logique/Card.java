package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class Card {
    private final String m_name;
    private final int m_damage;
    private int m_health;
    private final int m_blood;
    private final int m_bones;
    private final boolean m_sacrifiable;
    private ArrayList<String> m_abilities;

    public Card(String n, int d, int h, int bl, int bo, ArrayList<String> abilities){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_sacrifiable = true;
        m_abilities = abilities;
    }

    public Card(String n, int d, int h, int bl, int bo){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_sacrifiable = true;
        m_abilities = new ArrayList<String>();
    }
    public Card(String n, int d, int h, int bl, int bo, boolean sacrifiable, ArrayList<String> abilities){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_sacrifiable = sacrifiable;
        m_abilities = abilities;
    }

    public Card(String n, int d, int h, int bl, int bo, boolean sacrifiable){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_sacrifiable = sacrifiable;
        m_abilities = new ArrayList<String>();
    }

    public Card(Card card){
        m_name = card.getName();
        m_damage = card.getDamage();
        m_health = card.m_health;
        m_blood = card.m_blood;
        m_bones = card.m_bones;
        m_sacrifiable = card.getExpendable();
        m_abilities = card.getAbilities();
    }

    /**
     * Inflige des dégats à la carte courante
     * @param nbDamage entier, dégats à infliger à la carte courante
     */
    public void takeDamage(int nbDamage){
        if(nbDamage > 0) {
            this.m_health -= nbDamage;
        }
    }

    /**
     * La carte courante attaque une carte adverse passée en parametre
     * @param other carte que la carte courante attaque
     */
    public void attack(Optional<Card> other) {
        other.get().takeDamage(this.m_damage);
    }

    /**
     * Récupère les points de vie de la carte courante
     * @return entier, points de vie de la carte courante
     */
    public int getHealth(){
        return m_health;
    }
    /**
     * Récupère les dégats de la carte courante
     * @return entier, attaque de la carte courante
     */
    public int getDamage(){
        return m_damage;
    }

    /**
     * Vérifie si la carte courante a la capacité "vol"
     * @return vrai si la carte courante a la capacité "vol", faux sinon
     */
    public boolean isFlying(){
        return m_abilities.contains("vol");
    }

    /**
     * Récupère le cout en Os de la carte courante
     * @return entier, cout en Os de la carte courante
     */
    public int getBones(){return m_bones; }

    /**
     * Récupère le cout en gouttes de sang de la carte courante
     * @return entier, cout en gouttes de sang de la carte courante
     */
    public int getBlood(){return m_blood;}

    /**
     * Récupère le nom de la carte courante
     * @return chaine de caractère, nom de la carte courante
     */
    public String getName(){ return m_name;}

    /**
     * Vérifie si la carte courante est un animal ou un obstacle (un obstacle n'est pas sacrifiable, un animal l'est)
     * @return vrai si la carte courante est sacrifiable, faux sinon
     */
    public boolean getExpendable(){return m_sacrifiable;}

    /**
     * Gère l'attaque d'une carte passée en paramètre à la carte courante, tient compte des capccités suivantes : vol, venin, puant, épines
     * @param other carte adverse, que la carte courante attqque
     * @return entier, dégats infligés au joueur adverse si les dégats de la carte courante ne sont pas bloqués par la carte adverse
     */
    public int attack(Card other){
        int damage = 0;
        int damageToAttacker = 0;
        int damageToDefender = 0;
        if(this.getAbilities().contains("vol")) {
            damage = this.getDamage();
        }
        else{
            if(this.getAbilities().contains("venin") && other.getExpendable()) {
                damageToDefender = other.getHealth();
            }
            if(other.getAbilities().contains("épines")){
                damageToAttacker = 1;
            }
            if(!this.getAbilities().contains("venin") && other.getAbilities().contains("puant")){
                damageToDefender = this.getDamage() -1;
            }
            if(!this.getAbilities().contains("venin") && !other.getAbilities().contains("puant")){
                damageToDefender = this.getDamage();
            }
        }
        this.takeDamage(damageToAttacker);
        other.takeDamage(damageToDefender);
        return damage;
    }

    /**
     * Vérifie si une carte est vivante ou morte, si ses PV atteingnent 0
     * @return vrai si les PV de la carte <= 0, faux sinon
     */
    public boolean isDead(){
        return this.getHealth() <= 0;
    }

    /**
     * Obtient la liste des capacités de la carte courante
     * @return liste de chaines de caractères
     */
    public ArrayList<String> getAbilities(){
        return m_abilities;
    }

    /**
     * Ajoute une capacité à la carte courante
     * @param ability chaine de caractère, ajoutée à la liste des capacités de la carte courante
     */
    public void addAbility(String ability){
        this.m_abilities.add(ability);
    }

    /**
     * Résume la carte courante sous forme d'une chaine de caractères
     * @return chaine de caractère : nom, statistiques et capacités de la carte
     */
    @Override
    public String toString(){
        return this.m_name + "(dmg=" + this.m_damage + ", hp=" + this.getHealth() + ", blood="
                + this.getBlood() + ", bones=" + this.getBones() + ", abilities=" + this.getAbilities().toString() +")";
    }
}