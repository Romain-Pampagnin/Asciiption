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

    public boolean removeCard(){
        if(this.m_card.isPresent()){
            this.m_card = Optional.empty();
            return true;
        }
        return false;
    }

    public Optional<Card> getCard(){
        return m_card;
    }

    public int attack(Slot other){
        if(this.m_card.isPresent() && this.m_card.get().getDamage()>0){
            if(this.m_card.get().isFlying() || other.getCard().isEmpty()) {
                return this.m_card.get().getDamage();
            }
            if(this.m_card.get().getDamage() >= other.getCard().get().getHealth()){
                other.removeCard();
            }else{
                other.getCard().get().takeDamage(this.m_card.get().getDamage());
            }
        }
        return 0;
    }
}
