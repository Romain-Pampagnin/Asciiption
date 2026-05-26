package Logique;

public class CardFactory {
    private CardFactory(){}

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
}
