package Entity;

import java.util.Vector;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;

import Control.Controller;
import Screen.ScreenMap;

public class Player extends Character{

    private int xp;

    public Player(int x, int y) {
        super("Player", x, y, "Character");
    }

    public void addXp(int xp){

    }

    public void manageEntity(ScreenMap sm, Controller c) {

		// Do nothing if hero is already moving
		if (!isMoving()) {

			// Compute direction and next cell
			Vector<TiledMapTile> nextCell = new Vector<>();
			Player.Direction goalDirection = Player.Direction.NULL;

			if (c.keyStatus.get(Input.Keys.RIGHT)) {
				goalDirection = Player.Direction.RIGHT;
				nextCell = sm.getTile(getPosition(), 1, 0);
			} else if (c.keyStatus.get(Input.Keys.LEFT)) {
				goalDirection = Player.Direction.LEFT;
				nextCell = sm.getTile(getPosition(), -1, 0);
			} else if (c.keyStatus.get(Input.Keys.UP)) {
				goalDirection = Player.Direction.UP;
				nextCell = sm.getTile(getPosition(), 0, 1);
			} else if (c.keyStatus.get(Input.Keys.DOWN)) {
				goalDirection = Player.Direction.DOWN;
				nextCell = sm.getTile(getPosition(), 0, -1);
			}

			// Is the move valid ?
			if (sm.isWalkable(nextCell)) {
				if(!enemy()){
					// Go
					setSpeed(sm.getSpeed(nextCell));
					go(goalDirection);
				}
			} else {
				// Face the wall
				turn(goalDirection);
			}

			
			if(sm.isDoor(getPosition())){
				String nMap = null;
				Integer x = null;
				Integer y = null;
				try {
					nMap = ScreenMap.Door.nextMap;
					x = ScreenMap.Door.nextX;
					y = ScreenMap.Door.nextY;
				} catch (Exception e) { }
				ScreenMap.Door.reset();
				if (nMap == null || x == null || y == null) return;
				sm.map = nMap;
				setPosition(x*sm.tileWidth, y*sm.tileHeight);
				System.out.println("Go to: " + sm.map + " in " + x + " x " + y);
			}
		}
	}

    private boolean enemy() {
		return false;
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
