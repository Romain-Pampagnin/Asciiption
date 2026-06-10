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

    /**
     * Transfère les cartes depuis le nextBoard du Bot à son Board "de front", la méthode est appelée en début de tour du Bot
     */
    public void pushCards(){
        for(int i = 0; i < this.m_nextBoard.getCards().length; i++){
            if(this.m_nextBoard.getCards()[i].isPresent() && this.getBoard().getCards()[i].isEmpty()){
                this.getBoard().getCards()[i] = Optional.of(m_nextBoard.getCards()[i].get());
                m_nextBoard.getCards()[i] = Optional.empty();
            }
        }
    }

    /**
     * Sélectionne la meilleure carte parmi une liste de cartes
     * @param cardList liste de carte à examiner
     * @return indice de la meilleure carte de la liste passée en parametre
     */
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

    /**
     * Sélectionne la pire carte parmi une liste de cartes
     * @param cardList liste de carte à examiner
     * @return indice de la pire carte de la liste passée en parametre
     */
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

    /**
     * Méthode calcul arbitraire de la valeur d'une carte en fonction de ses statistiques et capacités
     * @param card carte dont on calcule la valeur
     * @return entier, valeur de la carte passée en parametre
     */
    public int sumStats(Card card){
        int sum = card.getDamage() + card.getHealth();
        if(card.getAbilities().contains("vol")){
            sum += 5;
        }
        return sum;
    }

    /**
     * Obtient le nextBoard du Bot
     * @return nextBoard du Bot
     */
    public Board getNextBoard() {
        return m_nextBoard;
    }

    /**
     * Pioche des cartes dans la pioche du Bot
     * @param nbCard entier, nombre de cartes à piocher
     * @return entier, nombre de cartes effectivement piochées
     */
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

    /**
     * Sacrifie la carte présente sur le Board du Bot, à l'indice passé en parametre, tient compte de la capacité "7 vies"
     * @param index entier, indice de la carte à sacrifier
     * @throws Exception retourne une exception si l'emplacement spécifié est vide
     */
    public void sacrifice(int index) throws Exception {
        if(this.m_board.getCards()[index].isPresent()){
            if(!this.m_board.getCards()[index].get().getAbilities().contains("7 vies")) {
                m_cemetery.add(this.m_board.getCards()[index].get());
                this.m_board.removeFromBoard(index);
            }
        }
        else {
            throw new Exception("Il n'y a pas de carte à cet emplacement, impossible de sacrifier la carte.");
        }
    }

    /**
     * Joue une carte depuis la main du Bot vers son nextBoard
     * @param handIndex entier, indice de la carte à jouer depuis la main du Bot
     * @param slotIndex entier, indice de l'emplacement sur lequel jouer la carte
     * @throws Exception retourne des exceptions si les indices sont hors des valeurs prévues
     */
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

    /**
     * Indique si le Board du Bot contient au moins un emplacement libre
     * @return vrai si le Board du Bot contient au moins un emplacement libre, faux sinon
     */
    @Override
    public boolean boardHasEmptySlot() {
        return m_board.hasEmptySlot();
    }

    /**
     * Analyse si la carte passée en parametre est jouable en fonction de ses couts en os et en gouttes de sang
     * @param card carte à analyser
     * @return vrai si le Bot dispose de suffisamment de ressources pour jouer la carte passée en parametre, faux sinon
     */
    @Override
    public boolean isCardPlayable(Card card) {
        return (card.getBlood() > 0 &&
                (card.getBones() <= this.getBonesFromGrave() && card.getBlood() <= this.m_totalBlood))
                || (card.getBlood() == 0 && (boardHasEmptySlot() && card.getBones() <= this.getBonesFromGrave()));
    }

    /**
     * Attaque un joueur
     * @param other joueur à attaquer
     * @throws Exception retourne une exception si la méthode bury rencontre une erreur
     */
    @Override
    public void attack(Joueur other) throws Exception {
        this.m_score += this.m_board.attack(other.getBoard());
        other.bury();
    }
}
