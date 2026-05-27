package Logique;

import Affichage.Display;
import Affichage.TurnDialog;

import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    private Player m_player;
    private PlayerBot m_bot;
    private int m_roundNumber;
    private int m_tourNumber;
    private int m_playerBlood;
    private int m_botBLood;

    public Round(String playerName,int number){
        m_roundNumber=number;
        //a changer
        ArrayList<Card> deckPlayer = new ArrayList<Card>();
        ArrayList<Card> deckEnemy = new ArrayList<Card>();
        //fin à changer
        m_player = new Player(playerName,deckPlayer);
        m_bot= new PlayerBot(deckEnemy);
        m_playerBlood = 0;
        m_botBLood = 0;

    }

    public void run() throws Exception {
        this.m_player.draw(4);
        this.m_bot.draw(4);

        while(this.m_player.getScore() - this.m_bot.getScore() < 5 && this.m_player.getScore() - this.m_bot.getScore() > -5)
        {
            //début du tour
            m_playerBlood = 0;
            m_botBLood = 0;
            //actions du bot
            m_bot.draw(1);


            //actions du joueur
            m_player.draw(1);
            Display.Global(this);
            actions(m_player);

        }

    }

    public Player getPlayer(){
        return m_player;
    }
    public PlayerBot getBot(){
        return m_bot;
    }

    public int actions(Player player) throws Exception {
        TurnDialog.ActionChoices();
        String choix =  TurnDialog.ActionChoices();
        while(choix != "attack") {
            switch (choix) {
                case "sacrifice":
                    m_playerBlood += sacrifice(player);
                    return 1;
                case "play":
                    play(player);
                    return 1;
                default:
                    choix = TurnDialog.ActionChoices();
                    break;
            }
        }
        return 1;
    }

    public int sacrifice(Player player) throws Exception {
        TurnDialog.Sacrifice();
        int choix = 0;
        Scanner sacrifice = new Scanner(System.in);
        while (choix < 1 || choix > 5){
            choix = TurnDialog.Sacrifice();
        }

        if(player.getBoard().getSlots(choix).getCard().isEmpty()){
            TurnDialog.SacrificeError();
            return 0;
        }
        else{
            TurnDialog.SacrificeOk(player.getBoard().getSlots(choix).getCard().get());
            player.sacrifice(choix);
            return 1;
        }

    }

    public void play(Player player){
        if(player.getHand().isEmpty()){
            TurnDialog.emptyHand();
        }
        else{
            int choix = -1;
            while (choix < 0 || choix > player.getHand().size()){
                //potentiellement flush et affichier global (? mettre ca dans chaque action dans TurnDialog ?)
                choix = TurnDialog.PlayCard();
            }

            if(player.getCardBloodFromHand(choix) == 0 && player.getBoard().hasEmptySlot() && player.getBonesFromGrave() >= player.getCardBoneFromHand(choix)){
                int slot = -1;
                while (slot < 0 || slot > player.getHand().size()){
                    slot = TurnDialog.PlaySlot();
                }
                player.playCard(choix, slot);
            }
            else if(player.getCardBloodFromHand(choix) > 0 && player.getCardBloodFromHand(choix) <= m_playerBlood && player.getBonesFromGrave() >= player.getCardBoneFromHand(choix)){
                int slot = -1;
                while (slot < 0 || slot > player.getHand().size()){
                    slot = TurnDialog.PlaySlot();
                }
                player.playCard(choix, slot);
            }
            else{
                TurnDialog.cantPlayCard();
            }
        }

    }
}
