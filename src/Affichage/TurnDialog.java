package Affichage;

import Logique.Card;
import Logique.CardFactory;
import Logique.Player;
import Logique.Round;

import java.util.Scanner;

public class TurnDialog {
    private static Scanner m_choix = new Scanner(System.in);
    private static Round m_round;

    public static void setRound(Round round){
        m_round = round;
    }

    private static void sleep(int millisec){
        try{
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            System.out.println("erreur de sleep");
        }
    }

    public static String ActionChoices(){
        Display.Global(m_round);
        System.out.println("C'est à vous de jouer :");
        System.out.println("    - attack : vos cartes attaquent et votre tour prend fin");
        System.out.println("    - sacrifice : vous sacrifiez une ou plusieurs cartes");
        System.out.println("    - play : jouez une carte depuis votre main");
        System.out.print("Saisissez l'action choisie et tapez Enter : ");
        return m_choix.nextLine();
    }

    public static int Sacrifice(){
        Display.Global(m_round);
        System.out.print("Entrez le numéro de la carte que vous voulez sacrifier depuis votre jeu : ");
        return m_choix.nextInt();
    }

    public static void AskConfirm(){
        Display.Global(m_round);
        System.out.println("Confirmez ?");
    }

    public static void SacrificeError(){
        Display.Global(m_round);
        System.out.println("Erreur : l'emplacement sélectionné ne contient pas une carte sacrifiable.");
        sleep(3000);
    }

    public static void SacrificeOk(Card card){
        Display.Global(m_round);
        System.out.println("Vous sacrifiez : " + card.getName() + ", qui vous rapporte une goutte de sang.");
    }

    public static int PlayCard(){
        Display.Global(m_round);
        System.out.println("Entrez le numéro de la carte que vous voulez jouer depuis votre main : ");
        return m_choix.nextInt();
    }

    public static int PlaySlot(){
        Display.Global(m_round);
        System.out.println("Entrez le numéro de l'emplacement dans lequel vous voulez jouer votre carte : ");
        return m_choix.nextInt();
    }

    public static void emptyHand(){
        Display.Global(m_round);
        System.out.println("Votre main est vide, vous ne pouvez pas jouer de carte.");
        sleep(3000);
    }

    public static void occupiedSlot(){
        Display.Global(m_round);
        System.out.println("L'emplacement sélectionné est déjà occupé.");
        sleep(3000);
    }

    public static void nonPlayableCard(){
        Display.Global(m_round);
        System.out.println("Vous n'avez pas les ressources nécessaire (os, gouttes de sang ou emplacement libre) pour jouer cette carte. Essayez une autre action.");
        sleep(3000);
    }
}
