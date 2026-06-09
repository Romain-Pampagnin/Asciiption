package Logique;

import java.util.ArrayList;

public abstract class Joueur {
    private String m_name;
    private int m_score;
    private Board m_board;
    private ArrayList<Card> m_cemetery;
    private Deck m_deck;
    private ArrayList<Card> m_hand;
    private int m_totalBlood;

    public abstract int draw(int nbCard);
    public abstract void sacrifice();
    public abstract void playCard();
    public abstract boolean boardHasEmptySlot();
    public abstract boolean isCardPlayable();
    public abstract void attack();

    private void bury() throws Exception {
        for(int i = 0; i<this.m_board.getCards().length; i++) {
            if(m_board.getCards()[i].isPresent() && m_board.getCards()[i].get().isDead()){
                m_cemetery.add(this.m_board.getCards()[i].get());
                this.m_board.removeFromBoard(i);
            }
        }
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
