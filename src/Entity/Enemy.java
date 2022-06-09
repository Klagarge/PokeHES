package Entity;

import Text.FightData;
import Text.TextEnemy;

import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.lib.GdxGraphics;

public class Enemy extends Character{
    private String map;

    public TextEnemy textEnemy;

    public Enemy(String name, int x, int y, String img, String map) {
        super(name, x, y, img);
        //generate his text
        this.textEnemy = new TextEnemy("enemi"); //TODO should be name
        textEnemy.generateText();
        this.map = map;

        turn(Character.Direction.DOWN);
        //generate the vector of fight
        //FightData fightData = new FightData(name);

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
