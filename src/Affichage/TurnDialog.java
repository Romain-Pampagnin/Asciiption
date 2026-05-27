package Affichage;

import Logique.Card;
import Logique.CardFactory;
import Logique.Player;

import java.util.Scanner;

public class TurnDialog {
    private static Scanner m_choix = new Scanner(System.in);
    public static String ActionChoices(){
        System.out.println("C'est à vous de jouer :");
        System.out.println("    - attack : vos cartes attaquent et votre tour prend fin");
        System.out.println("    - sacrifice : vous sacrifiez une ou plusieurs cartes");
        System.out.println("    - play : jouez une carte depuis votre main");
        System.out.print("Saisissez l'action choisie et tapez Enter : ");
        return m_choix.nextLine();
    }

    public static int Sacrifice(){
        System.out.print("Entrez le numéro de la carte que vous voulez sacrifier depuis votre jeu : ");
        return m_choix.nextInt();
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

    public static int PlayCard(){
        System.out.println("Entrez le numéro de la carte que vous voulez jouer depuis votre main : ");
        return m_choix.nextInt();
    }

    public static int PlaySlot(){
        System.out.println("Entrez le numéro de l'emplacement dans lequel vous voulez jouer votre carte : ");
        return m_choix.nextInt();
    }

    public static void emptyHand(){
        System.out.println("Votre main est vide, vous ne pouvez pas jouer de carte.");
    }

    public static void cantPlayCard(){
        System.out.println("Vous n'avez pas assez de ressources (os ou gouttes de sang ou emplacement libre) pour jouer cette carte. Essayez une autre action.");
    }
}
