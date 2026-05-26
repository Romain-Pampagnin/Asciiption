package Affichage;

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
}
