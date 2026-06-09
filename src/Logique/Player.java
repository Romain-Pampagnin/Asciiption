package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class Player {
    private String m_name;
    private int m_score;
    private Board m_board;
    private ArrayList<Card> m_cemetery;
    private Deck m_deck;
    private ArrayList<Card> m_hand;
    private int m_totalBlood;

    public Player(String name,Deck deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Board(4);
        m_deck = deck;
        m_name= name;
        m_totalBlood = 0;
    }
    public int draw(int nbCard){
        int drawnCards=0;
        for(int n = 0; n < nbCard; n ++){
            if (this.m_deck.getDeckSize() > 0){
                this.m_hand.add(this.m_deck.draw());
                drawnCards++;
            }
        }
        return drawnCards;
    }

    public int getScore(){
        return m_score;
    }

    public int getBonesFromGrave(){
        return this.m_cemetery.size();
    }

    public ArrayList<Card> getHand(){
        return this.m_hand;
    }

    public Deck getDeck(){
        return this.m_deck;
    }

    public Board getBoard(){
        return m_board;
    }

    public void sacrifice(int index) throws Exception {
        if(this.m_board.getCards()[index].isPresent()){
            if(!this.m_board.getCards()[index].get().getAbilities().contains("nombreuses vies")){
                m_cemetery.add(this.m_board.getCards()[index].get());
                this.m_board.removeFromBoard(index);
            }
        }
        else {
            throw new Exception("Il n'y a pas de carte à cet emplacement, impossible de sacrifier la carte.");
        }
    }

    public void playCard(int handIndex, int slotIndex) throws Exception {
        if(slotIndex < m_board.getCards().length && handIndex < m_hand.size()){
            //si la carte coute du sang, il faut diminuer la réserve de sang du joueur
            if(this.m_hand.get(handIndex).getBlood() > 0){
                this.decreaseTotalBlood(this.m_hand.get(handIndex).getBlood());
            }
            this.m_board.getCards()[slotIndex] = Optional.of(this.m_hand.get(handIndex));
            this.m_hand.remove(handIndex);
        }
        else if(slotIndex <= m_board.getCards().length){
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
        return m_board.hasEmptySlot();
    }

    public boolean isCardPlayable(Card card){
        return (card.getBlood() > 0 &&
                (card.getBones() <= this.getBonesFromGrave() && card.getBlood() <= this.m_totalBlood))
                || (card.getBlood() == 0 && (boardHasEmptySlot() && card.getBones() <= this.getBonesFromGrave()));
    }

    public void attack(Player other) throws Exception {
        this.m_score += this.m_board.attack(other.getBoard());
        other.bury();
    }

    private void bury() throws Exception {
        for(int i = 0; i<this.m_board.getCards().length; i++) {
            if(m_board.getCards()[i].isPresent() && m_board.getCards()[i].get().isDead()){
                m_cemetery.add(this.m_board.getCards()[i].get());
                this.m_board.removeFromBoard(i);
            }
        }
    }
}
