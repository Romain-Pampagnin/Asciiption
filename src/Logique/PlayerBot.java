package Logique;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBot extends Player {

    private Optional<Card>[] m_nextBoard;

    public PlayerBot(ArrayList<Card> deck) {
        super("adversaire", deck);
        m_nextBoard = new Optional[4];
        for (int i = 0; i < m_nextBoard.length; i++) {
            m_nextBoard[i] = Optional.empty();
        }
    }

    public Optional<Card>[] getNextBoard() {
        return m_nextBoard;
    }
}
