package Logique;

public class PlayerScale {
    static int m_scale=0;

    static void playerHit(int damage){
        m_scale+=damage;
    }
    static void OpponentHit(int damage){
        m_scale-=damage;
    }
}
