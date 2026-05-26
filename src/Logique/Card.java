package Logique;

public class Card {
    private final String m_name;
    private final int m_damage;
    private int m_health;
    private final int m_blood;
    private final int m_bones;
    private final boolean m_flying;
    private final boolean m_sacrifiable;

    private Card(String n, int d, int h, int bl, int bo, boolean fly){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = true;
    }
    private Card(String n, int d, int h, int bl, int bo, boolean fly, boolean sacrifiable){
        m_name = n;
        m_damage = d;
        m_health = h;
        m_blood = bl;
        m_bones = bo;
        m_flying = fly;
        m_sacrifiable = sacrifiable;
    }

    // Méthodes création Animaux
    static public Card createChat(){
        return new Card("Chat",0,1,1,0,false);
    }

    static public Card createGrizzly(){
        return new Card("Grizzly",4,6,3,0,false);
    }

    static public Card createCoyote(){
        return new Card("Coyote",2,1,0,4,false);
    }

    static public Card createMoineau(){
        return new Card("Moineau",1,2,1,0,true);
    }

    static public Card createCorbeau(){
        return new Card("Corbeau",2,3,2,0,true);
    }

    static public Card createEcureuil(){
        return new Card("Ecureuil",0,1,0,0,false);
    }

    static public Card createHermine(){
        return new Card("Hermine",1,3,1,0,false);
    }

    static public Card createLouveteau(){
        return new Card("Louveteau",1,1,1,0,false);
    }

    static public Card createLoup(){
        return new Card("Loup",3,2,2,0,false);
    }

    static public Card createPunaise(){
        return new Card("Punaise",1,2,0,2,false);
    }

    // Méthodes création Obstacles
    static public Card createRocher(){
        return new Card("Rocher",0,5,0,0,false,false);
    }

    static public Card createSapin(){
        return new Card("Sapin",0,3,0,0,false,false);
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
}