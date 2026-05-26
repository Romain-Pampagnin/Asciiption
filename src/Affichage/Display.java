package Affichage;

import Logique.*;

import static java.lang.String.valueOf;

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

    public static String Deck(Player deck){
        String sortie = "";
    }

    public static String Board(Board board){
        String sortie = "";
        String bordDeSlot = "*************";
        String bordDeCarte = "*-----------*";
        String ligneSlot = "*           *";
        String ligneCarte = "|           |";
        String separateurCarte = "|-----------|";

        String[] boardTab = {"","","","","","","",""};

        for(int i = 0; i < 4; i ++) {
            if (board.getSlots(i).getCard().isEmpty()){
                boardTab[0] += bordDeSlot + "   ";
                boardTab[1] += ligneSlot + "   ";
                boardTab[2] += ligneSlot + "   ";
                boardTab[3] += "*     " + (i + 1) + "     *   ";
                boardTab[4] += ligneSlot + "   ";
                boardTab[5] += ligneSlot + "   ";
                boardTab[6] += ligneSlot + "   ";
                boardTab[7] += bordDeSlot + "   ";

            }
            else{
                boardTab[0] += bordDeCarte + "   ";
                String cardName = "|" + padRight(board.getSlots(i).getCard().get().getName(), 11) + "|";
                boardTab[1] += cardName + "   ";
                boardTab[2] += separateurCarte + "   ";
                String cardHP = "| Hp:" + padRight(valueOf(board.getSlots(i).getCard().get().getHealth()),7) + "|";
                boardTab[3] += cardHP + "   ";
                //attaque
                String cardDamage;
                if(board.getSlots(i).getCard().get().getDamage() == 0){
                   cardDamage = ligneCarte;
                }
                else{
                    cardDamage = "| Dmg:" + padRight(valueOf(board.getSlots(i).getCard().get().getDamage()),6) + "|";
                }
                boardTab[4] += cardDamage + "   ";
                //os
                String cardBones;
                if(board.getSlots(i).getCard().get().getBones() == 0){
                    cardBones = ligneCarte;
                }
                else{
                    cardBones = "| Os:" + padRight(valueOf(board.getSlots(i).getCard().get().getBones()),7) + "|";
                }
                boardTab[5] += cardBones + "   ";
                //volant
                String cardFlying;
                if(board.getSlots(i).getCard().get().isFlying()){
                    cardFlying = "| Volant    |";
                }
                else{
                    cardFlying = ligneCarte;
                }
                boardTab[6] += cardFlying + "   ";
                boardTab[7] += bordDeCarte+ "   ";


            }
        }

        for(int i =0; i < 8; i++){
            sortie += boardTab[i]+"\n";
        }
        return sortie;
    }

    public static String Intro(){
        return "Vous vous retrouvez dans une cabane, perdu.e en pleine forêt. Dans l'obscurité, attablé face à vous se dresse un adversaire aux yeux inquiétants qui vous défie à un étrange jeu de cartes...";
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }
}
