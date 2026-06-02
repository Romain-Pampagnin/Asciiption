package Logique;

import Affichage.Display;
import Affichage.TurnDialog;

import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Collections.shuffle;

public class Round {
    private Player m_player;
    private PlayerBot m_bot;
    private int m_roundNumber;
    private int m_tourNumber;

    public Round(String playerName,int number){
        m_roundNumber = number;
        //a changer
        ArrayList<Card> deckPlayer = buildDeck(number);
        ArrayList<Card> deckEnemy = buildDeck(number);
        //fin à changer
        m_player = new Player(playerName,deckPlayer);
        m_bot= new PlayerBot(deckEnemy);
        Affichage.TurnDialog.setRound(this);
    }

    private ArrayList<Card> buildDeck(int roundNumber){
        ArrayList<Card> deck = new ArrayList<Card>();
        switch (roundNumber){
            case 1 :
                for(int i = 0; i < 8; i++)
                {
                    deck.add(CardFactory.createEcureuil());
                }
                for(int i = 0; i < 2; i++)
                {
                    deck.add(CardFactory.createCorbeau());
                }
                for(int i = 0; i < 3; i++)
                {
                    deck.add(CardFactory.createPunaise());
                }
                deck.add(CardFactory.createHermine());
                deck.add(CardFactory.createGrizzly());
                shuffle(deck);
        }
        return deck;
    }

    public void run() throws Exception {
        this.m_player.draw(4);
        this.m_bot.draw(4);
        TurnDialog.setRound(this);

        while(this.m_player.getScore() - this.m_bot.getScore() < 5 && this.m_player.getScore() - this.m_bot.getScore() > -5)
        {
            //début du tour
            m_player.resetTotalBlood();
            m_bot.resetTotalBlood();
            //actions du bot
            m_bot.draw(1);


            //actions du joueur
            m_player.draw(1);
            actions(m_player);



        }
        System.out.println("debug fin du premier round");
    }

    public Player getPlayer(){
        return m_player;
    }
    public PlayerBot getBot(){
        return m_bot;
    }

    public void actions(Player player) throws Exception {
        //TurnDialog.actionChoices();
        String choix = "";
        while(!choix.equals("attack")) {
            choix =  TurnDialog.actionChoices();
            switch (choix) {
                case "sacrifice":
                    m_player.incrementTotalBlood(sacrifice(player));
                    break;
                case "play":
                    play(player);
                    break;
                case "attack":
                    break;
                default:
                    System.out.println("Saisie incorecte : "+choix);
                    break;
            }
        }
    }

    public int sacrifice(Player player) throws Exception {
        TurnDialog.sacrifice();
        int choix = -1;
        while (choix < 1 || choix > 4){
            choix = TurnDialog.sacrifice();
        }
        choix --; //pour adapter le choix saisi aux indices des listes, tableaux
        if(player.getBoard()[choix].isEmpty()){
            TurnDialog.sacrificeError();
            return 0;
        }
        else{
            if(player.getBoard()[choix].get().getExpendable()){
                TurnDialog.sacrificeOk(player.getBoard()[choix].get());
                player.sacrifice(choix);
                return 1;
            }
            else{
                TurnDialog.sacrificeError();
                return 0;
            }
        }
    }

    public void play(Player player) throws Exception {
        if(player.getHand().isEmpty()){
            TurnDialog.emptyHand();
        }
        else{
            int choix = -1;
            while (choix < 0 || choix > player.getHand().size()){
                choix = TurnDialog.playCard();
            }
            choix --; //pour adapter le choix saisi aux indices des listes, tableaux

            if(player.isCardPlayable(m_player.getHand().get(choix))){
                int slot = -1;
                while ((slot < 1 || slot > 4 )){
                    slot = TurnDialog.playSlot(m_player.getHand().get(choix));
                }
                slot --; //pour adapter le choix saisi aux indices des listes, tableaux
                if(player.getBoard()[slot].isEmpty()){
                    player.play(choix, slot);
                }
                else{
                    TurnDialog.occupiedSlot();
                }
            }
            else {
                TurnDialog.nonPlayableCard();
            }
        }
    }


}
