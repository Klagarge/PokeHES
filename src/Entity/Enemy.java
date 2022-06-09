package Entity;

import Text.FightData;

import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.lib.GdxGraphics;

public class Enemy extends Character{
    private String map;

    public FightData fightData;

    public Enemy(String name, int x, int y, String img, String map) {
        super(name, x, y, img);
        this.map = map;

        turn(Character.Direction.DOWN);
        //generate the vector of fight
        fightData = new FightData(name);
        //TODO Auto-generated constructor stub

    }

    public String getMap() {
        return map;
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
