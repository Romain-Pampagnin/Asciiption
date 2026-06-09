package Logique;

import java.util.ArrayList;

public class CardFactory {
    static private ArrayList<String> m_nombreusesVies;
    private CardFactory(){}

    static public Card createChat(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("nombreuses vies");
        return new Card("Chat",0,1,1,0,false, abilities);
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
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("croissance");
        return new Card("Louveteau",1,1,1,0,false, abilities);
    }

    static public Card createLoup(){
        return new Card("Loup",3,2,2,0,false);
    }

    static public Card createPunaise(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("puant");
        return new Card("Punaise",1,2,0,2,false, abilities);
    }

    static public Card createElan(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("coureur");
        return new Card("Elan",2,4,2,0,false, abilities);
    }

    static public Card createVipere(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("contact mortel");
        return new Card("Vipère",1,1,2,0,false, abilities);
    }

    static public Card createPorcEpic(){
        ArrayList<String> abilities = new ArrayList<>();
        abilities.add("piques pointues");
        return new Card("Porc-épic",1,2,1,0,false, abilities);
    }

    static public Card createRocher(){
        return new Card("Rocher",0,5,0,0,false, false);
    }

    static public Card createSapin(){
        return new Card("Sapin",0,3,0,0,false, false);
    }
}
