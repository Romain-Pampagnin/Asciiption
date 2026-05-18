public class Board {
    private final Slot[] m_slots;
    public Board(){
        m_slots = new Slot[4];
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
}
