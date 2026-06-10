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
    /**
     * Pioche des cartes dans la pioche du joueur
     * @param nbCard entier, nombre de cartes à piocher
     * @return entier, nombre de cartes effectivement piochées
     */
    public abstract int draw(int nbCard);

    /**
     * Sacrifie la carte présente sur le Board du joueur, à l'indice passé en parametre
     * @param index entier, indice de la carte à sacrifier
     * @throws Exception retourne une exception si l'emplacement spécifié est vide
     */
    public abstract void sacrifice(int index) throws Exception;

    /**
     * Joue une carte depuis la main du joueur vers son Board
     * @param handIndex entier, indice de la carte à jouer depuis la main du joueur
     * @param slotIndex entier, indice de l'emplacement du Board sur lequel jouer la carte
     * @throws Exception retourne des exceptions si les indices sont hors des valeurs prévues
     */
    public abstract void playCard(int handIndex, int slotIndex) throws Exception;

    /**
     * Indique si le Board du joueur contient au moins un emplacement libre
     * @return vrai si le Board du joueur contient au moins un emplacement libre, faux sinon
     */
    public abstract boolean boardHasEmptySlot();

    /**
     * Analyse si la carte passée en parametre est jouable en fonction de ses couts en os et en gouttes de sang
     * @param card carte à analyser
     * @return vrai si le joueur dispose de suffisamment de ressources pour jouer la carte passée en parametre, faux sinon
     */
    public abstract boolean isCardPlayable(Card card);

    /**
     * Attaque un joueur
     * @param other joueur à attaquer
     * @throws Exception retourne une exception si la méthode bury rencontre une erreur
     */
    public abstract void attack(Joueur other) throws Exception;

    public void bury() throws Exception {
        for(int i = 0; i<this.m_board.getCards().length; i++) {
            if(m_board.getCards()[i].isPresent() && m_board.getCards()[i].get().isDead()){
                m_cemetery.add(this.m_board.getCards()[i].get());
                this.m_board.removeFromBoard(i);
            }
        }
    }

    /**
     * Met à jour les cartes du joueur courant en appelant la méthode updateCards de son Board
     */
    public void updateCards(){
        m_board.updateCards();
    }

    /**
     * Obtient le score du joueur courant
     * @return entier, score du joueur courant
     */
    public int getScore(){
        return m_score;
    }

    /**
     * Obtient le nombre de cartes présentes dans le cimetière du joueur courant, et donc le nombre d'os dont il dispose
     * @return entier, nombre de cartes dans le cimetière du joueur courant
     */
    public int getBonesFromGrave(){
        return this.m_cemetery.size();
    }

    /**
     * Obtient la main du joueur courant
     * @return liste de cartes
     */
    public ArrayList<Card> getHand(){
        return this.m_hand;
    }

    /**
     * Obtient le deck/la pioche du joueur courant
     * @return liste de cartes
     */
    public Deck getDeck(){
        return this.m_deck;
    }

    /**
     * Obtient le Board du joueur courant
     * @return Board
     */
    public Board getBoard(){
        return m_board;
    }

    /**
     * Obtient le nombre de gouttes de sang dont dispose le joueur courant
     * @return entier, nombre de gouttes de sang
     */
    public int getTotalBlood(){
        return this.m_totalBlood;
    }

    /**
     * Incrémente la réserve de gouttes de sang du joueur courant d'une certaine quantité
     * @param i entier, nombre de gouttes de sang à ajouter à la réserve du joueur courant
     */
    public void incrementTotalBlood(int i){
        this.m_totalBlood += i;
    }

    /**
     * Fixe la quantité de gouttes de sang dont dispose le joueur courant à 0
     */
    public void resetTotalBlood(){
        this.m_totalBlood = 0;
    }

    /**
     * Decrémente la réserve de gouttes de sang du joueur courant d'une certaine quantité
     * @param i entier, nombre de gouttes de sang à retirer de la réserve du joueur courant
     */
    public void decreaseTotalBlood(int i){
        this.m_totalBlood -= i;
    }

    /**
     * Obtient le nom du joueur courant
     * @return chaine de caractères
     */
    public String getName(){
        return this.m_name;
    }




}
