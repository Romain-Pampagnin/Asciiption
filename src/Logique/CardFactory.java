package Logique;

import java.util.ArrayList;

public class CardFactory {
    static private ArrayList<String> m_nombreusesVies;
    private CardFactory(){}

    /**
     * Crée un chat, avec ses statistiques et la capacité "7 vies"
     * @return carte chat
     */
    static public Card createChat(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("7 vies");
        return new Card("Chat",0,1,1,0,true, abilities);
    }

    /**
     * Crée un grizzly, avec ses statistiques
     * @return carte grizzly
     */
    static public Card createGrizzly(){
        return new Card("Grizzly",4,6,3,0,true);
    }

    /**
     * Crée un coyote, avec ses statistiques
     * @return carte coyote
     */
    static public Card createCoyote(){
        return new Card("Coyote",2,1,0,4,true);
    }

    /**
     * Crée un moineau, avec ses statistiques et la capacité "vol"
     * @return carte moineau
     */
    static public Card createMoineau(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("vol");
        return new Card("Moineau",1,2,1,0, true, abilities);
    }

    /**
     * Crée un corbeau, avec ses statistiques et la capacité "vol"
     * @return carte corbeau
     */
    static public Card createCorbeau(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("vol");
        return new Card("Corbeau",2,3,2,0,true, abilities);
    }

    /**
     * Crée un écureuil, avec ses statistiques
     * @return carte écureuil
     */
    static public Card createEcureuil(){
        return new Card("Ecureuil",0,1,0,0,true);
    }

    /**
     * Crée une hermine, avec ses statistiques
     * @return carte hermine
     */
    static public Card createHermine(){
        return new Card("Hermine",1,3,1,0,true);
    }

    /**
     * Crée un louveteau, avec ses statistiques et la capacité "croissance"
     * @return carte louveteau
     */
    static public Card createLouveteau(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("croissance");
        return new Card("Louveteau",1,1,1,0,true, abilities);
    }

    /**
     * Crée un loup, avec ses statistiques
     * @return carte loup
     */
    static public Card createLoup(){
        return new Card("Loup",3,2,2,0,true);
    }

    /**
     * Crée une punaise, avec ses statistiques et la capacité "puant"
     * @return carte punaise
     */
    static public Card createPunaise(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("puant");
        return new Card("Punaise",1,2,0,2,true, abilities);
    }

    /**
     * Crée un élan, avec ses statistiques et la capacité "coureur"
     * @return carte élan
     */
    static public Card createElan(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("coureur");
        return new Card("Elan",2,4,2,0,true, abilities);
    }

    /**
     * Crée une vipère, avec ses statistiques et la capacité "venin"
     * @return carte vipère
     */
    static public Card createVipere(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("venin");
        return new Card("Vipère",1,1,2,0,true, abilities);
    }

    /**
     * Crée un porc-épic, avec ses statistiques et la capacité "épines"
     * @return carte porc-épic
     */
    static public Card createPorcEpic(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("épines");
        return new Card("Porc-épic",1,2,1,0,true, abilities);
    }

    /**
     * Crée un rocher, avec ses statistiques
     * @return carte rocher
     */
    static public Card createRocher(){
        return new Card("Rocher",0,5,0,0,false);
    }

    /**
     * Crée un sapin, avec ses statistiques
     * @return carte sapin
     */
    static public Card createSapin(){
        return new Card("Sapin",0,3,0,0,false);
    }
}
