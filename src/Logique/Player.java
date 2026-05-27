package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class Player {
    private String m_name;
    private int m_score;
    private Board m_board;
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
    public int draw(int nbCard){
        int drawnCards=0;
        for(int n = 0; n < nbCard; n ++){
            if (!this.m_deck.isEmpty()){
                this.m_hand.add(this.m_deck.getFirst());
                this.m_deck.removeFirst();
                drawnCards++;
            }
        }
        return drawnCards;
    }
//    public EnumErrorCodes.drawCode isPlayable(int nbSlot, int nbHand){
//        if(m_hand.get(nbHand).getBlood() >= 1){
//            if(m_board.getTotalBlood() >= m_hand.get(nbHand).getBlood()){
//                if(m_hand.get(nbHand).getBones() <= getBonesFromGrave()) {
//                    return EnumErrorCodes.drawCode.CAN_PLAY;
//                }
//                else{
//                    return EnumErrorCodes.drawCode.NOT_ENOUGH_BONES;
//                }
//            }
//            else{
//                return EnumErrorCodes.drawCode.NOT_ENOUGH_BLOOD;
//            }
//        }
//        else{
//
//        }
//        return
//    }

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

    public ArrayList<Card> getHand(){
        return this.m_hand;
    }

    public ArrayList<Card> getDeck(){
        return this.m_deck;
    }

    public Board getBoard(){
        return m_board;
    }

    public Card sacrifice(int index){
        Card toBury = this.m_board.sacrifice(index);
        m_cemetery.add(toBury);
        return toBury;
    }

    public void playCard(int handIndex, int slotIndex){

    }
}
