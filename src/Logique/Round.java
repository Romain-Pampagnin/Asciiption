package Logique;

import java.util.ArrayList;

public class Round {
    private Player m_player;
    private PlayerBot m_bot;
    private int m_roundNumber;
    public Round(String playerName,int number){
        m_roundNumber=number;
        //a changer
        ArrayList<Card> deckPlayer = new ArrayList<Card>();
        ArrayList<Card> deckEnemy = new ArrayList<Card>();
        //fin à changer
        m_player = new Player(playerName,deckPlayer);
        m_bot= new PlayerBot(deckEnemy);
    }

//    public void run(){
//        while(this.m_player.getScore() - this.m_players[1].getScore() < 5 && this.m_players[0].getScore() - this.m_players[1].getScore() > -5)
//        {
//            m_players[1].draw(1);
//            //m_players[1].
//        }
//
//    }

    public Player getPlayer(){
        return m_player;
    }
    public Player getBot(){
        return m_bot;
    }
}
