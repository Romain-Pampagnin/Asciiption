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
        if(m_card.isPresent()){
            m_card= Optional.empty();
            return true;
        }
        return false;
    }

    public Optional<Card> getCard(){
        return m_card;
    }
    public void attack(Slot other){
        if(this.m_card.isPresent()){
            if(this.m_card.get().isFlying()){
                PlayerScale.playerHit(this.m_card.get().getDamage());
            }
        }
    }
}
