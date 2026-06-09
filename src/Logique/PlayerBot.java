package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBot extends Player {

    private Board m_nextBoard;

    public PlayerBot(Deck deck) {
        super("adversaire", deck);
        m_nextBoard = new Board(4);
        for (int i = 0; i < m_nextBoard.getCards().length; i++) {
            m_nextBoard.getCards()[i] = Optional.empty();
        }
    }

    public Board getNextBoard() {
        return m_nextBoard;
    }

//    public int chooseCardToPlay(){
//        //vérifier si la main contient des cartes coutant du blood
//        ArrayList<Integer> costBlood = new ArrayList<Integer>();
//        for (int i = 0; i < this.getHand().size(); i++){
//            if(this.getHand().get(i).getBlood() > 0){
//                costBlood.add(i);
//            }
//        }
//
//        //créer une liste des cartes jouables
//        ArrayList<Integer> playables = new ArrayList<Integer>();
//        for (int i = 0; i < this.getHand().size(); i++){
//            if(this.isCardPlayable(this.getHand().get(i))){
//                playables.add(i);
//            }
//        }
//
//        //passer en revue la liste des cartes jouables
//
//    }

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
        if(card.getAbilities().contains("vol")){
            sum += 5;
        }
        return sum;
    }
}
