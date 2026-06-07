package Logique;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Deck {
    private ArrayList<Card> m_cards;

    public Deck (){
        m_cards = new ArrayList<Card>();
    }
    public Deck (int roundNumber){
        m_cards = buildDeck(roundNumber);
    }

    private ArrayList<Card> buildDeck(int roundNumber){
        ArrayList<Card> deck = new ArrayList<Card>();
        switch(roundNumber){
            case 1 :
                for(int i = 0; i < 8; i++)
                {
                    deck.add(CardFactory.createEcureuil());
                }
                for(int i = 0; i < 2; i++)
                {
                    deck.add(CardFactory.createCorbeau());
                }
                for(int i = 0; i < 3; i++)
                {
                    deck.add(CardFactory.createPunaise());
                }
                deck.add(CardFactory.createHermine());
                deck.add(CardFactory.createGrizzly());
                shuffle(deck);
            case 2:

        }
        return deck;
    }

    public void addCard(Card card){
        this.m_cards.add(card);
    }

    public Card draw(){
        Card buffer = this.m_cards.getFirst();
        this.m_cards.removeFirst();
        return buffer;
    }

    public int getDeckSize(){
        return this.m_cards.size();
    }

    public boolean isDeckEmpty(){
        return this.m_cards.isEmpty();
    }
}
