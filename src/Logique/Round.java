package Logique;

import Affichage.Display;
import Affichage.TurnDialog;
import Turn.PlayerTurn;

import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    private Player m_player;
    private PlayerBot m_bot;
    private int m_roundNumber;
    private int m_tourNumber;

    public Round(String playerName,int number){
        m_roundNumber=number;
        //a changer
        ArrayList<Card> deckPlayer = new ArrayList<Card>();
        ArrayList<Card> deckEnemy = new ArrayList<Card>();
        //fin à changer
        m_player = new Player(playerName,deckPlayer);
        m_bot= new PlayerBot(deckEnemy);
    }

    public void run(){
        this.m_player.draw(4);
        this.m_bot.draw(4);
        while(this.m_player.getScore() - this.m_bot.getScore() < 5 && this.m_player.getScore() - this.m_bot.getScore() > -5)
        {
            m_bot.draw(1);
            //actions du bot

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
        int totalGouttes = 0;
        TurnDialog.ActionChoices();
        Scanner action = new Scanner(System.in);
        String choix = action.nextLine();
        switch (choix){
            case "sacrifice" :
                totalGouttes += Sacrifice(player);
                return 1;

            default :
                return 0;
        }
    }

    public int Sacrifice(Player player){
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
            return 1;
        }

    }
}
