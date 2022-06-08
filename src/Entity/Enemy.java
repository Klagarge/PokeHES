package Entity;

import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.lib.GdxGraphics;

public class Enemy extends Character{

    public Enemy(String name, int x, int y, String img) {
        super(name, x, y, img);
        turn(Character.Direction.DOWN);
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
