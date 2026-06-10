package Affichage;

import Logique.*;

import java.util.Optional;

import static java.lang.String.valueOf;

public class Display {

    public static String hand(Joueur player){
        String sortie ="";
        sortie += "Votre main:";
        int cardNumber = 1;
        for(Card card : player.getHand()){
            sortie += "\n" + cardNumber + ". " + card.getName() + " PV: " + card.getHealth() + " Att: " + card.getDamage() + " Gouttes de sang: " + card.getBlood() + " Os: " + card.getBones();
            cardNumber ++;
        }
        return sortie;
    }

    public static String handLine(Joueur player, int cardIndex){
        return (padLeft(valueOf(cardIndex + 1),2)) + ". " + padRight(player.getHand().get(cardIndex).getName(),9) + " PV: " + padRight(String.valueOf(player.getHand().get(cardIndex).getHealth()),2) + " Att: " + padRight(String.valueOf(player.getHand().get(cardIndex).getDamage()),2) + " Gouttes de sang: " + padRight(String.valueOf(player.getHand().get(cardIndex).getBlood()),2) + " Os: " + padRight(String.valueOf(player.getHand().get(cardIndex).getBones()),2);
    }
    public static String player(Joueur player, Joueur player2){
        String sortie = "";
        String vide = "\n";
        String ligneCarteG = "|           |";
        String ligneCarteD = "|           |";
        String labels =    "\n  Cimetière              Votre main " + padRight("("+player.getName()+")",11) + "                               Pioche               Score";
        String hautBoxes = "\n*-----------*   *-------------------------------------------------------*   *-----------*        *-----------*";

        if(player.getTotalBlood() > 0){
            if(player.getTotalBlood() == 1) {
                sortie += "Vous possédez " + player.getTotalBlood() + " goutte de sang";
            }
            else{
                sortie += "Vous possédez " + player.getTotalBlood() + " gouttes de sang";
            }
        }
        else{
            sortie += vide;
        }

        sortie += labels;
        sortie += hautBoxes;

        int taillePlayer = 5;
        if(player.getHand().size() > 5){
            taillePlayer = player.getHand().size();
        }

        for(int i = 0; i <= taillePlayer; i ++){
            if(i == 0){
                ligneCarteD = "|           |      D ";
                if(player.getScore() - player2.getScore() <= -5)
                {
                    ligneCarteD += "|O---------x| Perdu !";
                }
                else if (player.getScore() - player2.getScore() > -5 && player.getScore() - player2.getScore() < 5) {
                    switch (player.getScore() - player2.getScore()) {
                        case -4:
                            ligneCarteD += "|xO--------x| V";
                            break;
                        case -3:
                            ligneCarteD += "|x-O-------x| V";
                            break;
                        case -2:
                            ligneCarteD += "|x--O------x| V";
                            break;
                        case -1:
                            ligneCarteD += "|x---O-----x| V";
                            break;
                        case 0:
                            ligneCarteD += "|x----O----x| V";
                            break;
                        case 1:
                            ligneCarteD += "|x-----O---x| V";
                            break;
                        case 2:
                            ligneCarteD += "|x------O--x| V";
                            break;
                        case 3:
                            ligneCarteD += "|x-------O-x| V";
                            break;
                        case 4:
                            ligneCarteD += "|x--------Ox| V";
                            break;
                    }
                }
                else if (player.getScore() - player2.getScore() >= 5){
                    ligneCarteD += "|x---------O| Gagné !";
                }
            }
            else if(i == 1){
                ligneCarteG = "|    " + padRight(String.valueOf(player.getBonesFromGrave()),3) + "    |";
                ligneCarteD = "|    " + padRight(String.valueOf(player.getDeck().getDeckSize()),3) + "    |        *-----------*";
            }
            else if(i == 2){
                ligneCarteG = "|    Os     |";
                ligneCarteD = "|  cartes   |";
            }
            else if(i > 2 && i <5){
                ligneCarteG = "|           |";
                ligneCarteD = "|           |";
            }
            else if (i == 5){
                ligneCarteG = "*-----------*";
                ligneCarteD = "*-----------*";
            }
            else if(i >5){
                ligneCarteG = "             ";
                ligneCarteD = "             ";
            }

            if(player.getHand().size() > i){
                sortie += "\n" + ligneCarteG + "   |" + handLine(player, i) + "|   " + ligneCarteD;
            }
            else if(player.getHand().size() == i) {
                sortie += "\n" + ligneCarteG + "   *-------------------------------------------------------*   " + ligneCarteD;
            }
            else{
                sortie += "\n" + ligneCarteG + "                                                               " + ligneCarteD;
            }
                //sortie += "\n" + ligneCarteG + "   |                                                      |   " + ligneCarteD;
                //sortie += "\n" + ligneCarteG + "   *------------------------------------------------------*   " + ligneCarteD;


        }
        return sortie;
    }

    public static String board(Board board){
        String sortie = "";
        String espaceGauche = "              ";
        String bordDeSlot = "*************";
        String bordDeCarte = "*-----------*";
        String ligneSlot = "*           *";
        String ligneCarte = "|           |";
        String separateurCarte = "|-----------|";

        String[] boardTab = {"","","","","","","",""};
        for(int i = 0; i < boardTab.length; i++){
            boardTab[i] += espaceGauche;
        }
        for(int i = 0; i < board.getCards().length; i ++) {
            if (board.getCards()[i].isEmpty()){
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
                String cardName = "|" + padRight(board.getCards()[i].get().getName(), 11) + "|";
                boardTab[1] += cardName + "   ";
                boardTab[2] += separateurCarte + "   ";
                String cardHP = "| Hp:" + padRight(valueOf(board.getCards()[i].get().getHealth()),7) + "|";
                boardTab[3] += cardHP + "   ";
                //attaque
                String cardDamage;
                if(board.getCards()[i].get().getDamage() == 0){
                   cardDamage = ligneCarte;
                }
                else{
                    cardDamage = "| Dmg:" + padRight(valueOf(board.getCards()[i].get().getDamage()),6) + "|";
                }
                boardTab[4] += cardDamage + "   ";
                //ability 1
                String cardAbility1;
                if(board.getCards()[i].get().getAbilities().isEmpty()){
                    cardAbility1 = ligneCarte;
                }
                else{
                    cardAbility1 = "| " + padRight(valueOf(board.getCards()[i].get().getAbilities().get(0)),10) + "|";
                }
                boardTab[5] += cardAbility1 + "   ";
                //ability 2
                String cardAbility2;
                if(board.getCards()[i].get().getAbilities().size() > 1){
                    cardAbility2 = "| " + padRight(valueOf(board.getCards()[i].get().getAbilities().get(1)),10) + "|";
                }
                else{
                    cardAbility2 = ligneCarte;
                }
                boardTab[6] += cardAbility2 + "   ";
                boardTab[7] += bordDeCarte + "   ";


            }
        }

        for(int i =0; i < 8; i++){
            sortie += boardTab[i]+"\n";
        }
        return sortie;
    }

    public static String nextBoard(Board board){
        String sortie = "";
        String espaceGauche = "              ";
        String bordDeSlot = "*************";
        String bordDeCarte = "*-----------*";
        String ligneSlot = "*           *";
        String ligneCarte = "|           |";
        String separateurCarte =  "|-----------|";
        String flecheDoubleSlot = "     ||      ";
        String flecheDoubleSlotTete = "     \\/      ";

        String[] boardTab = {"","","","","","","",""};
        for(int i = 0; i < boardTab.length; i++){
            boardTab[i] += espaceGauche;
        }

        for(int i = 0; i < board.getCards().length; i ++) {
            if (board.getCards()[i].isEmpty()){
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
                String cardName = "|" + padRight(board.getCards()[i].get().getName(), 11) + "|";
                boardTab[1] += cardName + "   ";
                boardTab[2] += separateurCarte + "   ";
                String cardHP = "| Hp:" + padRight(valueOf(board.getCards()[i].get().getHealth()),7) + "|";
                boardTab[3] += cardHP + "   ";
                //attaque
                String cardDamage;
                if(board.getCards()[i].get().getDamage() == 0){
                    cardDamage = ligneCarte;
                }
                else{
                    cardDamage = "| Dmg:" + padRight(valueOf(board.getCards()[i].get().getDamage()),6) + "|";
                }
                boardTab[4] += cardDamage + "   ";
                //ability 1
                String cardAbility1;
                if(board.getCards()[i].get().getAbilities().isEmpty()){
                    cardAbility1 = ligneCarte;
                }
                else{
                    cardAbility1 = "| " + padRight(valueOf(board.getCards()[i].get().getAbilities().get(0)),10) + "|";
                }
                boardTab[5] += cardAbility1 + "   ";
                //ability 2
                String cardAbility2;
                if(board.getCards()[i].get().getAbilities().size() > 1){
                    cardAbility2 = "| " + padRight(valueOf(board.getCards()[i].get().getAbilities().get(1)),10) + "|";
                }
                else{
                    cardAbility2 = ligneCarte;
                }
                boardTab[6] += cardAbility2 + "   ";
                boardTab[7] += bordDeCarte + "   ";;


            }
        }

        for(int i =0; i < 8; i++){
            sortie += boardTab[i]+"\n";
        }
        sortie += espaceGauche;
        for(int i = 0; i < 4; i++){
            sortie += flecheDoubleSlot + "   ";
        }
        sortie += "\n";
        sortie += espaceGauche;
        for(int i = 0; i < 4; i++){
            sortie += flecheDoubleSlotTete + "   ";
        }
        sortie += "\n";

        return sortie;
    }

    public static String intro(){
        return "Vous vous retrouvez dans une cabane, perdu.e en pleine forêt. Dans l'obscurité, attablé face à vous se dresse un adversaire aux yeux inquiétants qui vous défie à un étrange jeu de cartes...";
    }

    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    public static String global(Round round){
        String result ="";
        result += Display.nextBoard(round.getBot().getNextBoard());
        result += Display.board(round.getBot().getBoard());
        result += Display.board(round.getPlayer().getBoard());
        result += Display.player(round.getPlayer(), round.getBot());
        return result;
    }

    public static String globalDebug(Round round){
        String result ="";
        result += "Tour " + round.getTourNumber();
        result += Display.player(round.getBot(), round.getPlayer());
        result += "\n";
        result += Display.nextBoard(round.getBot().getNextBoard());
        result += Display.board(round.getBot().getBoard());
        result += Display.board(round.getPlayer().getBoard());
        result += Display.player(round.getPlayer(), round.getBot());
        return result;
    }


}
