package Logique;
import Affichage.*;

import java.util.Scanner;

public class Game {
    public String m_playerName;
    public int m_gamesWon;
    public int m_gamesLost;

    public Game() {
        m_gamesWon = 0;
        m_gamesLost = 0;
    }

    public void Initialize() {
        //afficher le texte introductif
        TurnDialog.intro();
        //(manque de temps)

        //demander au joueur son nom avec un scanner et stocker son nom dans une string redonnée à chaque round
        Scanner nom = new Scanner(System.in);
        TurnDialog.askName();
        this.m_playerName = nom.next();
    }

    public void StartRound(int i) throws Exception {
        //lancer le round
        Round newRound = new Round(m_playerName, i);
        newRound.run();
        if(newRound.hasHumanWon()){
            m_gamesWon ++;
        }
        else{
            m_gamesLost ++;
        }
    }

    public int getGamesWon(){
        return m_gamesWon;
    }

    public int getGamesLost(){
        return m_gamesLost;
    }
}
