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

    /**
     * Crée un deck, dont la composition sera différente en fonction du round passé en parametre. Le deck sera mélangé s'il est destiné à un Human,
     * et ne sera pas mélangé s'il est destiné à un Bot
     * @param roundNumber entier, numéro du round pour lequel le deck est construit
     * @param isBot booléen, true si le deck est à destination d'un Bot, false s'il est destiné à un Human
     * @return liste de cartes
     */
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

    /**
     * Ajoute une carte au deck courant
     * @param card carte passée en paramètre
     */
    public void addCard(Card card){
        this.m_cards.add(card);
    }

    /**
     * Pioche une carte du deck courant et la supprime du deck avant de la retourner
     * @return carte piochée
     */
    public Card draw(){
        Card buffer = this.m_cards.getFirst();
        this.m_cards.removeFirst();
        return buffer;
    }

    /**
     * Retourne la taille du deck courant
     * @return entier, nombre de cartes présentes dans le deck (liste de cartes)
     */
    public int getDeckSize(){
        return this.m_cards.size();
    }

    /**
     * Teste si le deck courant est vide
     * @return booléen, vrai si le deck est vide, faux sinon
     */
    public boolean isDeckEmpty(){
        return this.m_cards.isEmpty();
    }
}
