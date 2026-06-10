package Logique;

import Affichage.Display;
import Affichage.TurnDialog;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static java.util.Collections.shuffle;

public class Round {
    private Human m_player;
    private Bot m_bot;
    private int m_roundNumber;
    private int m_tourNumber;
    private boolean m_playerHasWon;

    public Round(String playerName,int number){
        m_roundNumber = number;
        m_tourNumber = 1;
        m_playerHasWon = false;
        //a changer
        Deck deckPlayer = new Deck(m_roundNumber, false);
        Deck deckEnemy = new Deck(m_roundNumber, true);
        //fin à changer
        m_player = new Human(playerName,deckPlayer);
        m_bot= new Bot(deckEnemy);
        Affichage.TurnDialog.setRound(this);
    }


    public void run() throws Exception {
        initialize(this.m_roundNumber);
        do {
            //début du tour
            m_player.resetTotalBlood();
            m_bot.resetTotalBlood();

            //updater les cartes du bot et avancer ses cartes prévisionnelles
            m_bot.updateCards();
            m_bot.pushCards();
            //actions du bot
            m_bot.draw(1);
            //bot pose des cartes sur son board prévisionnel
            botPlayCard(this.m_tourNumber);
            TurnDialog.botHasPlayedCards();
            //bot attaque le joueur
            m_bot.attack(m_player);

            //joueur update ses cartes
            m_player.updateCards();
            //actions du joueur
            m_player.draw(1);
            actions(m_player,m_bot);

            //incrémenter le numéro du round
            incrementTourNumber(1);


        }
        while(this.m_player.getScore() - this.m_bot.getScore() < 5 && this.m_player.getScore() - this.m_bot.getScore() > -5);

        if(this.m_player.getScore() - this.m_bot.getScore() < -5) {
            TurnDialog.botHasWon();
        }
        else{
            TurnDialog.humanHasWon();
            m_playerHasWon = true;
        }
    }

    public Human getPlayer(){
        return m_player;
    }
    public Bot getBot(){
        return m_bot;
    }

    public void incrementTourNumber(int i){
        m_tourNumber += i;
    }

    public int getTourNumber(){
        return m_tourNumber;
    }

    public void actions(Joueur player,Joueur other) throws Exception {
        //TurnDialog.actionChoices();
        String choix = "";
        while(!choix.equals("attack")) {
            choix =  TurnDialog.actionChoices();
            switch (choix) {
                case "sacrifice":
                    m_player.incrementTotalBlood(sacrifice(player));
                    break;
                case "play":
                    play(player);
                    break;
                case "attack":
                    player.attack(other);
                    break;
                default:
                    System.out.println("Saisie incorecte : "+choix);
                    break;
            }
        }
    }

    public void botPlayCard(int tourNumber) throws Exception {
        switch (tourNumber){
            case 1:
                m_bot.playCard(0,1);
                m_bot.playCard(0,3);
                break;
            case 2:
                m_bot.playCard(0,2);
                break;
            case 3:
                if(m_bot.getBoard().getCards()[2].isPresent() && m_bot.getBoard().getCards()[2].isPresent()){
                    m_bot.sacrifice(2);
                    m_bot.sacrifice(3);
                    m_bot.playCard(2,2);
                }
                else if (m_bot.getBoard().getCards()[2].isEmpty()){
                    m_bot.playCard(1,2);
                }
                else if (m_bot.getBoard().getCards()[3].isEmpty()){
                    m_bot.playCard(1,3);
                }
                break;
            case 4:
                if(m_bot.getBoard().getCards()[1].isPresent()) {
                    m_bot.sacrifice(1);
                    m_bot.playCard(3,1);
                }
                else if (m_bot.getBoard().getCards()[2].isPresent()) {
                    m_bot.sacrifice(2);
                    m_bot.playCard(3,2);
                }
                else{
                    m_bot.playCard(0,1);
                }
                break;

        }
    }

    public int sacrifice(Joueur player) throws Exception {
        int choix = -1;
        while (choix < 1 || choix > 4){
            choix = TurnDialog.sacrifice();
        }
        choix --; //pour adapter le choix saisi aux indices des listes, tableaux
        if(player.getBoard().getCards()[choix].isEmpty()){
            TurnDialog.sacrificeError();
            return 0;
        }
        else{
            if(player.getBoard().getCards()[choix].get().getExpendable()){
                TurnDialog.sacrificeOk(player.getBoard().getCards()[choix].get());
                player.sacrifice(choix);
                return 1;
            }
            else{
                TurnDialog.sacrificeError();
                return 0;
            }
        }
    }

    public void play(Joueur player) throws Exception {
        if(player.getHand().isEmpty()){
            TurnDialog.handIsEmpty();
        }
        else{
            int choix = -1;
            while (choix < 0 || choix > player.getHand().size()){
                choix = TurnDialog.chooseCard();
            }
            choix --; //pour adapter le choix saisi aux indices des listes, tableaux

            if(player.isCardPlayable(m_player.getHand().get(choix))){
                int slot = -1;
                while ((slot < 1 || slot > 4 )){
                    slot = TurnDialog.chooseSlot(m_player.getHand().get(choix));
                }
                slot --; //pour adapter le choix saisi aux indices des listes, tableaux
                if(player.getBoard().getCards()[slot].isEmpty()){
                    player.playCard(choix, slot);
                }
                else{
                    TurnDialog.slotIsOccupied();
                }
            }
            else {
                TurnDialog.cardUnplayable();
            }
        }
    }

    public void initialize(int roundNumber){
        //faire piocher + affichage message de début
        this.m_player.draw(3);
        this.m_bot.draw(3);
        TurnDialog.setRound(this);

        //ajouter les terrains
        addObstacles();
    }

    public void addObstacles(){
        //sélectionner l'obstacle
        Random randomNumbers = new Random();
        int indexP1 = randomNumbers.nextInt(4);
        int indexP2 = 0;
        int typeP1 = randomNumbers.nextInt(2);
        int typeP2 = randomNumbers.nextInt(2);

        if(typeP1 == 0){
            m_player.getBoard().getCards()[indexP1] = Optional.of(CardFactory.createSapin());
        }
        else{
            m_player.getBoard().getCards()[indexP1] = Optional.of(CardFactory.createRocher());
        }
        if(typeP2 == 0){
            m_bot.getBoard().getCards()[indexP2] = Optional.of(CardFactory.createSapin());
        }
        else{
            m_bot.getBoard().getCards()[indexP2] = Optional.of(CardFactory.createRocher());
        }
    }

    public boolean hasHumanWon (){
        return m_playerHasWon;
    }


}
