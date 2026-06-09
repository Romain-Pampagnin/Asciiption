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

    public void addToBoard (int index, Card card) throws Exception {
        if(this.m_slots[index].isEmpty()) {
            this.m_slots[index] = Optional.of(card);
        }
        else{
            throw new Exception ("L'emplacement auquel vous voulez ajouter une carte est déjà occupé.");
        }
    }

    public void removeFromBoard(int index) throws Exception{
        if(this.m_slots[index].isPresent()) {
            this.m_slots[index] = Optional.empty();
        }
        else{
            throw new Exception ("L'emplacement dont vous voulez retirer la carte est déjà vide.");
        }
    }

    public int attack(Board other){
        //lister les slots ayant attaqué ou pas
        boolean slotAttacked[] = new boolean[m_slots.length];
        //tout mettre à false
        for(int i = 0; i < m_slots.length; i ++){
            slotAttacked[i] = false;
        }

        int totalDamage = 0;
        for(int i = 0; i < this.m_slots.length; i ++){
            if(m_slots[i].isPresent() && other.getCards()[i].isPresent()){
                totalDamage += m_slots[i].get().attack(other.m_slots[i].get());
            }
            else if (m_slots[i].isPresent() && other.getCards()[i].isEmpty()){
                totalDamage += m_slots[i].get().getDamage();
            }
            //gestion des coureurs
            if(m_slots[i].isPresent() && !slotAttacked[i] && m_slots[i].get().getAbilities().contains(("coureur"))) {
                //pour les élans qui doivent courir
                if(!slotAttacked[i] && m_slots[i].isPresent() && m_slots[i].get().getAbilities().contains(("coureur"))){
                    if(i == m_slots.length -1 && m_slots[m_slots.length -2].isEmpty()){ //si on se trouve sur le slot tout à droite
                        //faire une copie de la carte
                        m_slots[m_slots.length -2] = Optional.of(new Card(m_slots[m_slots.length -1].get()));
                        slotAttacked[m_slots.length -2] = true;
                        //la supprimer de son slot d'origine
                        m_slots[m_slots.length -1] = Optional.empty();
                    }
                    else if( i != 0 && i != m_slots.length -1) { //cas général si on est sur un slot au "milieu"
                        if (m_slots[i + 1].isEmpty()) {
                            //faire une copie de la carte
                            m_slots[i + 1] = Optional.of(new Card(m_slots[i].get()));
                            slotAttacked[i + 1] = true;
                            //la supprimer de son slot d'origine
                            m_slots[i] = Optional.empty();
                        } else if (m_slots[i + 1].isPresent() && m_slots[i - 1].isEmpty()) {
                            //faire une copie de la carte
                            m_slots[i - 1] = Optional.of(new Card(m_slots[i].get()));
                            slotAttacked[i - 1] = true;
                            //la supprimer de son slot d'origine
                            m_slots[i] = Optional.empty();
                        }
                    }
                    else if(i == 0 && m_slots[1].isEmpty()){ //si on se trouve sur le slot tout à gauche
                        //faire une copie de la carte
                        m_slots[1] = Optional.of(new Card(m_slots[0].get()));
                        slotAttacked[1] = true;
                        //la supprimer de son slot d'origine
                        m_slots[0] = Optional.empty();
                    }
                }
            }
            //mettre le slot à true
            slotAttacked[i] = true;
        }
        return totalDamage;
    }

    public int getFirstEmptySlot(){
        for(int i = 0; i < m_slots.length; i++){
            if(m_slots[i].isEmpty()){
                return i;
            }
        }
        return -1;
    }

    public boolean hasEmptySlot(){
        for (int i = 0; i < m_slots .length; i++){
            if(m_slots[i].isEmpty()){
                return true;
            }
        }
        return false;
    }

    public void updateCards(){
        for(int i = 0; i < m_slots.length; i++){
            //pour les louveteaux qui doivent grandir
            if(m_slots[i].isPresent() && m_slots[i].get().getAbilities().contains(("croissance"))){
                m_slots[i] = Optional.empty();
                m_slots[i] = Optional.of(CardFactory.createLoup());
            }
        }
    }

    @Override
    public String toString() {
        String board = "";
        for (int i =0; i < this.m_slots.length; i++){
            if(this.m_slots[i].isEmpty()){
                board += "\nslot " + i + ": vide";
            }
            else{
                board += "\nslot " + i + ": " +this.m_slots[i].get().toString();
            }
        }
        board += "\n";
        return board;
    }
}
