package Affichage;

import Logique.*;

public class Display {

    public static String Hand(Player player){
        String sortie ="";
        sortie += "Votre main:";
        int cardNumber = 1;
        for(Card card : player.getHand()){
            sortie += "\n" + cardNumber + ". " + card.getName() + " PV: " + card.getHealth() + " Att: " + card.getDamage() + " Gouttes de sang: " + card.getBlood() + " Os: " + card.getBones();
            cardNumber ++;
        }
        return sortie;
    }
}
