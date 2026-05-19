public class Board {
    private final Slot[] m_slots;
    public Board(){
        m_slots = new Slot[4];
        for (int i=0; i< m_slots.length; i++){
            m_slots[i] = new Slot();
        }
    }
    public boolean addToSlot(Card card,int index){
        if(index<4 && index>=0){
            return m_slots[index].addCard(card);
        }
        return false;
    }
    public boolean removeOfSlot(int index){
        if(index<4 && index>=0){
            return m_slots[index].removeCard();
        }
        return false;
    }
    public void attack(Board other){
        for(int i=0;i<4;i++){
            //à voir :)
        }
    }
//    public boolean isSlotFree(int nbSlot){
//        return this.m_slots[nbSlot].getCard().isEmpty();
//    }
    public boolean addCard(int nbSlot,Card card){
        return m_slots[nbSlot].addCard(card);
    }
}
