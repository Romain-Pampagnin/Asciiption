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

    /**
     * Permet d'obtenir les cartes que contient le Board
     * @return tableau d'optionals de Card
     */
    public Optional<Card>[] getCards(){
        return this.m_slots;
    }

    /**
     * Ajouter une carte au Board
     * @param index index de l'emplacement sur lequel on veut ajouter la carte
     * @param card carte que l'on souhaite ajouter au Board
     * @throws Exception retourne une exception si l'emplacement est déjà occupé
     */
    public void addToBoard (int index, Card card) throws Exception {
        if(this.m_slots[index].isEmpty()) {
            this.m_slots[index] = Optional.of(card);
        }
        else{
            throw new Exception ("L'emplacement auquel vous voulez ajouter une carte est déjà occupé.");
        }
    }

    /**
     * Retirer une carte du Board
     * @param index index de l'emplacement duquel on veut retirer la carte
     * @throws Exception retourne une exception si l'emplacement est déjà vide
     */
    public void removeFromBoard(int index) throws Exception{
        if(this.m_slots[index].isPresent()) {
            this.m_slots[index] = Optional.empty();
        }
        else{
            throw new Exception ("L'emplacement dont vous voulez retirer la carte est déjà vide.");
        }
    }

    /**
     * Gère l'attaque d'un Board vers un autre Board, et le repositionnement des cartes du Board courant qui possède la capacité "coureur"
     * @param other Board ennemi que le Board courant attaque
     * @return la somme des dégats non-bloqués par les cartes du Board ennemi, ces dégats s'appliqueront au possesseur du Board ennemi
     */
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

    /**
     * Donne l'indice du premier emplacement libre du Board courant
     * @return indice du premier emplacement libre du Board courant
     */
    public int getFirstEmptySlot(){
        for(int i = 0; i < m_slots.length; i++){
            if(m_slots[i].isEmpty()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Indique si le Board courant contient au moins un emplacement libre
     * @return vrai si le Board courant contient un emplacement libre, faux sinon
     */
    public boolean hasEmptySlot(){
        for (int i = 0; i < m_slots .length; i++){
            if(m_slots[i].isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Met à jour les cartes du Board courant, cartes possédant la capacité "croisance"
     */
    public void updateCards(){
        for(int i = 0; i < m_slots.length; i++){
            //pour les louveteaux qui doivent grandir
            if(m_slots[i].isPresent() && m_slots[i].get().getAbilities().contains(("croissance"))){
                m_slots[i] = Optional.empty();
                m_slots[i] = Optional.of(CardFactory.createLoup());
            }
        }
    }

    /**
     * Transforme le Board courant en chaine de caractères
     * @return chaine de caractère contenant la liste des slots et les cartes présentes
     */
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
