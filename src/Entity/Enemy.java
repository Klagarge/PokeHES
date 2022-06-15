package Entity;

import com.badlogic.gdx.math.Vector2; 

public class Enemy extends Character{

    private String branch;



    public Enemy(String name, int x, int y, String img, String map, int pv, String branch, Character.Direction dir) {
        super(name, x, y, img, map);
        //generate his text

        this.map = map;

        turn(dir);

        this.branch = branch;

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

    public String getBranch(){
        return branch;
    }
    
}
