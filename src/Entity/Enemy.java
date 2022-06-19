package Entity;

import com.badlogic.gdx.math.Vector2;

import Main.Settings; 

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class Enemy extends Character{

    private String subject;
    public int recoveredTime = Settings.RECOVERED;

    private int pvInit;

    /**
     * Create an enemy
     * @param name The name of this enemy
     * @param x Initial x position
     * @param y Initial y position
     * @param map Initial map for this enemy
     * @param pv Maximum pv of this enemy (it's also the maximum of XP the player can win)
     * @param subject The subject taught by the enemy
     */
    public Enemy(String name, int x, int y, String map, int pv, String subject) {

        super(name, x, y, subject, map);
        //generate his text

        this.map = map;

        this.subject = subject;

        this.pv = pv;

        pvInit = pv;
        
    }

    public void setPosition(int x, int y, String map){
        position.set(x, y);
        this.map = map;
    }

    public void setPosition(Vector2 vPosition, String map){
        setPosition((int)vPosition.x, (int)vPosition.y, map);
    }

    @Override
    public void removedPv(int pv) {
        this.pv -= pv;
        
    }

    public String getBranch(){
        return subject;
    }

    @Override
    public int getPv(){
        //the pv can go under 0, but his real pv is 0 (for the player)
        return (pv<0) ? 0 : pv;
    }

    public int getPvInit(){
        return pvInit;
    }

    
}
