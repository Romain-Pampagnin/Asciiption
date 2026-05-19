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
        this("adversaire", deck);
    }
    public int draw(int nbCard){
        int drawnCards=0;
        for(int n=0;n<nbCard;n++){
            if (!this.m_deck.isEmpty()){
                this.m_hand.add(this.m_deck.getFirst());
                this.m_deck.removeFirst();
                drawnCards++;
            }
        }
        return drawnCards;
    }
    public boolean playCard(int nbSlot,int nbHand){
        if (nbSlot<=3 && nbSlot>=0){
            if(m_board.addToSlot(nbSlot,m_hand.get(nbHand))){
                m_hand.remove(nbHand);
                return true;
            }
        }
        return false;
    }

    public int getScore(){
        return m_score;
    }

    public int getBonesFromGrave(){
        int totalBones = 0;
        for(Card card : m_cemetery){
            totalBones += card.getBones();
        }
        return totalBones;
    }
}
