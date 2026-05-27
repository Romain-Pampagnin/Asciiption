package Affichage;

import Logique.Card;
import Logique.CardFactory;
import Logique.Player;

public class TurnDialog {
    public static void ActionChoices(){
        System.out.println("C'est à vous de jouer :");
        System.out.println("    - attack : vos cartes attaquent et votre tour prend fin");
        System.out.println("    - sacrifice : vous sacrifiez une ou plusieurs cartes");
        System.out.println("    - play : jouez une carte depuis votre main");
        System.out.print("Saisissez l'action choisie et tapez Enter : ");
    }

    public static void Sacrifice(){
        System.out.print("Entrez le numéro de la carte que vous voulez sacrifier depuis votre jeu : ");
    }

    public static void AskConfirm(){
        System.out.println("Confirmez ?");
    }

    public static void SacrificeError(){
        System.out.println("Erreur : l'emplacement sélectionné ne contient pas une carte sacrifiable.");
    }

    public static void SacrificeOk(Card card){
        System.out.println("Vous sacrifiez : " + card.getName() + ", qui vous rapporte une goutte de sang.");
    }

    public static void Play(){
        System.out.println("Entrez le numéro de la carte que vous voulez jouer depuis votre main : ");
    }

    public static void emptyHand(){
        System.out.println("Votre main est vide, vous ne pouvez pas jouer de carte.");
    }

    public static void cantPlayCard(){
        System.out.println("Vous n'avez pas assez de ressources (os ou gouttes de sang ou emplacement libre) pour jouer cette carte. Essayez une autre action.");
    }
}
