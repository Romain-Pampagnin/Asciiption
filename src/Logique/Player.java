package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class Player {
    private String m_name;
    private int m_score;
    private Optional<Card>[] m_board;
    private ArrayList<Card> m_cemetery;
    private ArrayList<Card> m_deck;
    private ArrayList<Card> m_hand;
    private int m_totalBlood;

    public Player(String name,ArrayList<Card> deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Optional[4];
        m_deck = deck;
        m_name= name;
        m_totalBlood = 0;
        for(int i = 0; i < m_board.length; i++){
            m_board[i] = Optional.empty();
        }
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
        return this.m_cemetery.size();
    }

    public ArrayList<Card> getHand(){
        return this.m_hand;
    }

    public ArrayList<Card> getDeck(){
        return this.m_deck;
    }

    public Optional<Card>[] getBoard(){
        return m_board;
    }

    public void sacrifice(int index) throws Exception {
        if(this.m_board[index].isPresent()){
            m_cemetery.add(this.m_board[index].get());
            this.m_board[index] = Optional.empty();
        }
        else {
            throw new Exception("Il n'y a pas de carte à cet emplacement, impossible de sacrifier la carte.");
        }
    }

    public void playCard(int handIndex, int slotIndex) throws Exception {
        if(slotIndex < m_board.length && handIndex < m_hand.size()){
            //si la carte coute du sang, il faut diminuer la réserve de sang du joueur
            if(this.m_hand.get(handIndex).getBlood() > 0){
                this.decreaseTotalBlood(this.m_hand.get(handIndex).getBlood());
            }
            this.m_board[slotIndex] = Optional.of(this.m_hand.get(handIndex));
            this.m_hand.remove(handIndex);
        }
        else if(slotIndex <= m_board.length){
            throw new Exception("Vous essayez de poser une carte sur un emplacement hors des valeurs prévues.");
        }
        else if(handIndex >= m_hand.size()){
            throw new Exception("Votre main ne contient pas de carte à cet indice.");
        }
    }

    public int getTotalBlood(){
        return this.m_totalBlood;
    }

    public void incrementTotalBlood(int i){
        this.m_totalBlood += i;
    }

    public void resetTotalBlood(){
        this.m_totalBlood = 0;
    }

    public void decreaseTotalBlood(int i){
        this.m_totalBlood -= i;
    }

    public boolean boardHasEmptySlot(){
        for (int i = 0; i < m_board .length; i++){
            if(m_board[i].isEmpty()){
                return true;
            }
        }
        return false;
    }

    public boolean isCardPlayable(Card card){
        if(card.getBlood() > 0){
            if(card.getBones() <= this.getBonesFromGrave() && card.getBlood() <= this.m_totalBlood){
                return true;
            }
        }
        else {
            if(boardHasEmptySlot() & card.getBones() <= this.getBonesFromGrave()){
                return true;
            }
        }
        return false;
    }

    public void attack(Player other){
        for(int i=0; i<this.m_board.length;i++){
            if(this.m_board[i].isPresent()){
                if(other.m_board[i].isPresent() && !(this.m_board[i].get().isFlying())){
                    if(this.m_board[i].get().getDamage() >= other.m_board[i].get().getHealth()){
                        other.m_cemetery.add(other.m_board[i].get());
                        other.m_board[i] = Optional.empty();
                    }else{
                        this.m_board[i].get().attack(other.m_board[i]);
                    }
                }else{
                    m_score += this.m_board[i].get().getDamage();
                }
            }
        }
    }
}
