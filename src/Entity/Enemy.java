package Entity;

import com.badlogic.gdx.math.Vector2; 

public class Enemy extends Character{



    public Enemy(String name, int x, int y, String img, String map) {
        super(name, x, y, img, map);
        //generate his text

        this.map = map;

        turn(Character.Direction.DOWN);
        //generate the vector of fight
        //FightData fightData = new FightData(name);

    }

    public void setPosition(int x, int y, String map){
        position.set(x, y);
        this.map = map;
    }

    public void setPosition(Vector2 vPosition, String map){
        setPosition((int)vPosition.x, (int)vPosition.y, map);
    }

    @Override
    protected void removedPv(int pv) {
        // TODO Auto-generated method stub
        
    }
    
}
