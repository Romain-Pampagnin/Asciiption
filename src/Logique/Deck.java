package Logique;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Deck {
    private ArrayList<Card> m_cards;

    public Deck (){
        m_cards = new ArrayList<Card>();
    }
    public Deck (int roundNumber, boolean isBot){
        if(isBot) {
            m_cards = buildDeck(roundNumber, true);
        }
        else{
            m_cards = buildDeck(roundNumber, false);
        }
    }


    private ArrayList<Card> buildDeck(int roundNumber, boolean isBot){
        ArrayList<Card> deck = new ArrayList<Card>();
        switch(roundNumber){
            case 1 :
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createPunaise());
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createCorbeau());
                deck.add(CardFactory.createHermine());
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createPunaise());
                deck.add(CardFactory.createCorbeau());
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createEcureuil());
                deck.add(CardFactory.createGrizzly());
                deck.add(CardFactory.createPunaise());
                deck.add(CardFactory.createEcureuil());
            case 2:

        }

        if(!isBot) {
            shuffle(deck);
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
