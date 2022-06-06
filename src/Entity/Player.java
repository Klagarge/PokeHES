package Entity;

import com.badlogic.gdx.math.Vector2;

public class Player extends Character{

    private int xp;

    public Player(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    public void addXp(int xp){

    }

    public void move(int x, int y){

    }

    public void move(Vector2 vMove){
        move((int)vMove.x, (int)vMove.y);
    }

    @Override
    protected void removedPv(int pv) {
        // TODO Auto-generated method stub
        
    }
}
