import java.util.ArrayList;

public class Round {
    private Player[] m_players;
    private int m_roundNumber;
    public Round(String playerName,int number){
        m_roundNumber=number;
        //a changer
        ArrayList<Card> deckPlayer = new ArrayList<Card>();
        ArrayList<Card> deckEnemy = new ArrayList<Card>();
        m_players = new Player[2];
        m_players[0] = new Player(playerName,deckPlayer);
        m_players[1] = new Player(deckEnemy);
    }
}
