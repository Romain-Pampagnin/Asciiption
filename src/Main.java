import Affichage.*;
import Logique.*;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws Exception {
        Game partie = new Game();
        partie.Initialize();

        for(int i = 1; i <= 3; i++) {
            partie.StartRound(i);
        }

        //compter les games perdues et gagnées
        if(partie.getGamesWon() > partie.getGamesLost()){
            TurnDialog.finalWon();
        }
        else {
            TurnDialog.finalLost();
        }

        //Round round1 = new Round("Joueur1", 1);
        //round1.run();


    }
}