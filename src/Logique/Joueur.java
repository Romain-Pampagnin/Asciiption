package Logique;

import java.util.ArrayList;

public abstract class Joueur {
    protected String m_name;
    protected int m_score;
    protected Board m_board;
    protected ArrayList<Card> m_cemetery;
    protected Deck m_deck;
    protected ArrayList<Card> m_hand;
    protected int m_totalBlood;

    public Joueur(String name,Deck deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Board(4);
        m_deck = deck;
        m_name= name;
        m_totalBlood = 0;
    }

    public Joueur(Deck deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Board(4);
        m_deck = deck;
        m_name= "Adversaire";
        m_totalBlood = 0;
    }

    public abstract int draw(int nbCard);
    public abstract void sacrifice(int index) throws Exception;
    public abstract void playCard(int handIndex, int slotIndex) throws Exception;
    public abstract boolean boardHasEmptySlot();
    public abstract boolean isCardPlayable(Card card);
    public abstract void attack(Joueur other) throws Exception;

    public void bury() throws Exception {
        for(int i = 0; i<this.m_board.getCards().length; i++) {
            if(m_board.getCards()[i].isPresent() && m_board.getCards()[i].get().isDead()){
                m_cemetery.add(this.m_board.getCards()[i].get());
                this.m_board.removeFromBoard(i);
            }
        }
    }

    public void updateCards(){
        m_board.updateCards();
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




}
