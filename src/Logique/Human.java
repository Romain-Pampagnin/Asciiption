package Logique;

import java.util.ArrayList;

public class Human extends Joueur{

    public Human(String name, Deck deck){
        m_score = 0;
        m_cemetery = new ArrayList<Card>();
        m_hand = new ArrayList<Card>();
        m_board = new Board(4);
        m_deck = deck;
        m_name= name;
        m_totalBlood = 0;
    }

    @Override
    public int draw(int nbCard) {
        return 0;
    }

    @Override
    public void sacrifice() {

    }

    @Override
    public void playCard() {

    }

    @Override
    public boolean boardHasEmptySlot() {
        return false;
    }

    @Override
    public boolean isCardPlayable() {
        return false;
    }

    @Override
    public void attack() {

    }
}
