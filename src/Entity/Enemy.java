package Entity;

import com.badlogic.gdx.math.Vector2;

public class Enemy extends Character{
    
    public Enemy(String name) {
        super(name);
    }

    public void setPosition(int x, int y){

    }

    public void setPosition(Vector2 vPosition){
        setPosition((int)vPosition.x, (int)vPosition.y);
    }

    @Override
    protected void removedPv(int pv) {
        // TODO Auto-generated method stub
        
    }
    
}
