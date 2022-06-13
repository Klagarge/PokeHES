package Entity;

import com.badlogic.gdx.math.Vector2; 

public class Enemy extends Character{



    public Enemy(String name, int x, int y, String img, String map, int pv) {
        super(name, x, y, img, map);
        //generate his text

        this.map = map;

        turn(Character.Direction.DOWN);

        this.pv = pv;


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
    
}
