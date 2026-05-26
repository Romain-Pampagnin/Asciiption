package Turn;

import Logique.*;
import Affichage.*;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Scanner;

public class PlayerTurn {

    public void run(Player player){
        int i = 0;
        do{i = turnAction(player);}
        while(i == 0);
    }

    public int turnAction(Player player){
        TurnDialog.ActionChoices();
        Scanner action = new Scanner(System.in);
        String choix = action.nextLine();
        switch (choix){
            case "sacrifice" :
                if(Sacrifice(player).isPresent()){
                    player.
                }
                break;
            default :
                return 0;
        }
    }

    public Optional<Card> Sacrifice(Player player){
        TurnDialog.Sacrifice();
        int choix = 0;
        Scanner sacrifice = new Scanner(System.in);
        while (choix < 1 || choix > 5){
            choix = sacrifice.nextInt();
        }

        if(player.getBoard().getSlots(choix).getCard().isEmpty()){
            return Optional.empty();
        }
        else{
            return player.getBoard().getSlots(choix).getCard();
        }

    }


}
