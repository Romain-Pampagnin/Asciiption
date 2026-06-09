package Logique;

import Affichage.TurnDialog;

import java.util.ArrayList;
import java.util.Optional;

public class Bot extends Joueur{
    private Board m_nextBoard;

    public Bot(Deck deck){
        super(deck);
        m_nextBoard = new Board(4);
    }

    public void pushCards(){
        for(int i = 0; i < this.m_nextBoard.getCards().length; i++){
            if(this.m_nextBoard.getCards()[i].isPresent() && this.getBoard().getCards()[i].isEmpty()){
                this.getBoard().getCards()[i] = Optional.of(m_nextBoard.getCards()[i].get());
                m_nextBoard.getCards()[i] = Optional.empty();
            }
        }
    }

    public int getBestCard(ArrayList<Card> cardList){
        int bestCard = -1;
        int bestStats = -1;
        //obtenir la meilleure somme de statistiques
        for(int i = 0; i < cardList.size(); i++){
            if(sumStats(cardList.get(i)) > bestStats){
                bestStats = sumStats(cardList.get(i));
            }
            else{
                bestCard = i;
            }
        }
        return bestCard;
    }

    public int getWorstCard(ArrayList<Card> cardList){
        int worstCard = -1;
        int worstStats = 1000;
        //obtenir la pire somme de statistiques
        for(int i = 0; i < cardList.size(); i++){
            if(sumStats(cardList.get(i)) < worstStats){
                worstStats = sumStats(cardList.get(i));
            }
            else{
                worstCard = i;
            }
        }
        return worstCard;
    }

    public int sumStats(Card card){
        int sum = card.getDamage() + card.getHealth();
        if(card.isFlying()){
            sum += 5;
        }
        return sum;
    }

    public Board getNextBoard() {
        return m_nextBoard;
    }

    @Override
    public int draw(int nbCard) {
        int drawnCards=0;
        for(int n = 0; n < nbCard; n ++){
            if (this.m_deck.getDeckSize() > 0){
                this.m_hand.add(this.m_deck.draw());
                drawnCards++;
            }
        }
        return drawnCards;
    }

    @Override
    public void sacrifice(int index) throws Exception {
        if(this.m_board.getCards()[index].isPresent()){
            m_cemetery.add(this.m_board.getCards()[index].get());
            this.m_board.removeFromBoard(index);
        }
        else {
            throw new Exception("Il n'y a pas de carte à cet emplacement, impossible de sacrifier la carte.");
        }
    }

    @Override
    public void playCard(int handIndex, int slotIndex) throws Exception {
        if(slotIndex < m_board.getCards().length && handIndex < m_hand.size()){
            //si la carte coute du sang, il faut diminuer la réserve de sang du joueur
            if(this.m_hand.get(handIndex).getBlood() > 0){
                this.decreaseTotalBlood(this.m_hand.get(handIndex).getBlood());
            }
            this.m_nextBoard.getCards()[slotIndex] = Optional.of(this.m_hand.get(handIndex));
            this.m_hand.remove(handIndex);
        }
        else if(slotIndex <= m_board.getCards().length){
            throw new Exception("Vous essayez de poser une carte sur un emplacement hors des valeurs prévues.");
        }
        else if(handIndex >= m_hand.size()){
            throw new Exception("Votre main ne contient pas de carte à cet indice.");
        }
    }

    @Override
    public boolean boardHasEmptySlot() {
        return m_board.hasEmptySlot();
    }

    @Override
    public boolean isCardPlayable(Card card) {
        return (card.getBlood() > 0 &&
                (card.getBones() <= this.getBonesFromGrave() && card.getBlood() <= this.m_totalBlood))
                || (card.getBlood() == 0 && (boardHasEmptySlot() && card.getBones() <= this.getBonesFromGrave()));
    }

    @Override
    public void attack(Joueur other) throws Exception {
        this.m_score += this.m_board.attack(other.getBoard());
        other.bury();
    }
}
