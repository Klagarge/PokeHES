package Entity;

import Text.FightData;

import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.lib.GdxGraphics;

public class Enemy extends Character{

    public FightData fightData;

    public Enemy(String name, int x, int y, String img) {
        super(name, x, y, img);
        //generate the vector of fight
        fightData = new FightData(name);
        //TODO Auto-generated constructor stub

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

    @Override
    public void draw(GdxGraphics arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
