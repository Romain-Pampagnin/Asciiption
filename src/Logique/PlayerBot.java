package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBot extends Player {

    private Optional<Card>[] m_nextBoard;

    public PlayerBot(ArrayList<Card> deck) {
        super("adversaire", deck);
        m_nextBoard = new Optional[4];
        for (int i = 0; i < m_nextBoard.length; i++) {
            m_nextBoard[i] = Optional.empty();
        }
    }

    public Optional<Card>[] getNextBoard() {
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
        for(int i = 0; i < this.m_nextBoard.length; i++){
            if(this.m_nextBoard[i].isPresent() && this.getBoard()[i].isEmpty()){
                this.getBoard()[i] = Optional.of(m_nextBoard[i].get());
                m_nextBoard[i] = Optional.empty();
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
}
