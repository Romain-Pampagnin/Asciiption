package Logique;
import java.util.Optional;

public class Board {
    private Optional<Card>[] m_slots;

    public Board(int size){
        m_slots = new Optional[size];
        for(int i = 0; i < size; i ++){
            m_slots[i] = Optional.empty();
        }
    }

    public Optional<Card>[] getCards(){
        return this.m_slots;
    }

    public void addToBoard (int index, Card card){
        this.m_slots[index] = Optional.of(card);
    }

    public void removeFromBoard(int index){
        this.m_slots[index] = Optional.empty();
    }

    public int attack(Board other){
        int totalDamage = 0;
        for(int i = 0; i < this.m_slots.length; i ++){
            if(m_slots[i].isPresent() && other.getCards()[i].isPresent()){
                totalDamage += m_slots[i].get().attack(other.m_slots[i].get());
            }
            else if (m_slots[i].isPresent() && other.getCards()[i].isEmpty()){
                totalDamage += m_slots[i].get().getDamage();
            }
        }
        return totalDamage;
    }

    public boolean hasEmptySlot(){
        for (int i = 0; i < m_slots .length; i++){
            if(m_slots[i].isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String board = "";
        for (int i =0; i < this.m_slots.length; i++){
            if(this.m_slots[i].isEmpty()){
                board += "slot " + i + ": vide\n";
            }
            else{
                board += "slot " + i + ": " +this.m_slots[i].get().toString() +"\n";
            }
        }
        return board;
    }
}
