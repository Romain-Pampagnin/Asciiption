package Logique;

import java.util.ArrayList;

public class PlayerBot extends Player{

    private Board m_nextBoard;

    public PlayerBot(ArrayList<Card> deck) {
        super("adversaire",deck);
        m_nextBoard = new Board();
    }
    public Board getNexBoard(){
        return this.m_nextBoard;
    }
}
