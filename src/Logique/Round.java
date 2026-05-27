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

    public void run(){
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
            Actions(m_player);

        }

    }

    public Player getPlayer(){
        return m_player;
    }
    public PlayerBot getBot(){
        return m_bot;
    }

    public int Actions(Player player){
        TurnDialog.ActionChoices();
        Scanner action = new Scanner(System.in);
        String choix = action.nextLine();
        while(choix != "attack") {
            switch (choix) {
                case "sacrifice":
                    m_playerBlood += sacrifice(player);
                    return 1;
                case "play":
                    play(player);

                default:
                    return 0;
            }
        }
    }

    public int sacrifice(Player player){
        TurnDialog.Sacrifice();
        int choix = 0;
        Scanner sacrifice = new Scanner(System.in);
        while (choix < 1 || choix > 5){
            choix = sacrifice.nextInt();
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
            TurnDialog.Play();
            int choix = -1;
            Scanner play = new Scanner(System.in);
            while (choix < 0 || choix > player.getHand().size()){
                choix = play.nextInt();
            }

            if(player.getHand().get(choix).getBlood() == 0 && player.getBoard().hasEmptySlot() && player.getBonesFromGrave() >= player.getHand().get(choix).getBones()){
                int slotchoice = -1;
                Scanner slot = new Scanner(System.in);
                while (slot < 0 || slot > player.getHand().size()){
                    slot = play.nextInt();
                }
                player.playCard(choix, slot);
            }
            else if(player.getHand().get(choix).getBlood() > 0 && player.getHand().get(choix).getBlood() <= m_playerBlood && player.getBonesFromGrave() >= player.getHand().get(choix).getBones()){
                player.playCard(choix);
            }
            else{
                TurnDialog.cantPlayCard();
            }
        }

    }
}
