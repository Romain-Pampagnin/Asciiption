package Logique;

public class Board {
    private Slot[] m_slots;
    public Board(){
        m_slots = new Slot[4];
        for (int i=0; i< m_slots.length; i++){
            m_slots[i] = new Slot();
        }
    }
    public boolean sacrifice(int index){
        if(index<4 && index>=0){
            return m_slots[index].removeCard();
        }
        return false;
    }
    public int attack(Board other){
        int totalDamage = 0;
        for(int i=0;i<4;i++){
            totalDamage += this.m_slots[i].attack(other.m_slots[i]);
        }
        return totalDamage;
    }
//    public boolean isSlotFree(int nbSlot){
//        return this.m_slots[nbSlot].getCard().isEmpty();
//    }
    public boolean addToSlot(int nbSlot, Card card){
        return m_slots[nbSlot].addCard(card);
    }

    public int getTotalBlood(){
        int totalBlood = 0;
        for(Slot slot : m_slots){
            if(slot.getCard().isPresent()) {
                totalBlood += slot.getCard().get().getBlood();
            }
        }
        return totalBlood;
    }
}
