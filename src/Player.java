import java.util.ArrayList;
import java.util.Optional;

public class Player {
    private String m_name;
    private int m_score;
    private Board m_board;
    private Optional<Board> m_nextBoard;
    private ArrayList<Card> m_cemetery;
    private ArrayList<Card> m_deck;
    private ArrayList<Card> m_hand;

    public Player(String name,ArrayList<Card> deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Board();
        m_deck = deck;
        m_name= name;

    }
    public Player(ArrayList<Card> deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Board();
        m_deck = deck;
        m_name= "adversaire";
    }
}
