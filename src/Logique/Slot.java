package Logique;

import java.util.Optional;

public class Slot {
    private Optional<Card> m_card;
    public Slot(){
        m_card=Optional.empty();
    }

    public boolean addCard(Card card){
        if (m_card.isEmpty()){
            m_card=Optional.of(card);
            return true;
        }
        return false;
    }

    public Card removeCard()throws Exception {
        if(this.m_card.isEmpty()){
            throw new Exception("Le slot ne contient pas de carte, ou une carte non sacrifiable.");
        }
        else{
            Card toBury = this.m_card.get();
            this.m_card = Optional.empty();
            return toBury;
        }
    }

    public Optional<Card> getCard(){
        return m_card;
    }

    public int attack(Slot other) throws Exception {
        if(this.m_card.isPresent() && this.m_card.get().getDamage()>0){
            if(this.m_card.get().isFlying() || other.getCard().isEmpty()) {
                return this.m_card.get().getDamage();
            }
            if(this.m_card.get().getDamage() >= other.m_card.get().getHealth()){
                other.removeCard();
            }else{
                other.getCard().get().takeDamage(this.m_card.get().getDamage());
            }
        }
        return 0;
    }
}
