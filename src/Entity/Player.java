package Entity;

import java.util.Vector;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;

import Control.Controller;
import Main.PokeMudry;
import Screen.ScreenMap;

public class Player extends Character{

    private int xp;
	public Enemy lastEnemy = null;
	public boolean frontOfEnemy = false;

    public Player(int x, int y, String map) {
        super("Player", x, y, "Character", map);
    }

    public void addXp(int xp){

    }

    public void manageEntity(ScreenMap sm, Controller c) {

		// Do nothing if hero is already moving
		if (!isMoving()) {

			// Compute direction and next cell
			Vector<TiledMapTile> nextCell = new Vector<>();
			Player.Direction goalDirection = Player.Direction.NULL;
			Vector2 nextPos = position;

			if (c.keyStatus.get(Input.Keys.RIGHT)) {
				goalDirection = Player.Direction.RIGHT;
				nextCell = sm.getTile(getPosition(), 1, 0);
				nextPos.x+=sm.tileWidth;
			} else if (c.keyStatus.get(Input.Keys.LEFT)) {
				goalDirection = Player.Direction.LEFT;
				nextCell = sm.getTile(getPosition(), -1, 0);
				nextPos.x-=sm.tileWidth;
			} else if (c.keyStatus.get(Input.Keys.UP)) {
				goalDirection = Player.Direction.UP;
				nextCell = sm.getTile(getPosition(), 0, 1);
				nextPos.y+=sm.tileHeight;
			} else if (c.keyStatus.get(Input.Keys.DOWN)) {
				goalDirection = Player.Direction.DOWN;
				nextCell = sm.getTile(getPosition(), 0, -1);
				nextPos.y-=sm.tileHeight;
			}

			// Is the move valid ?
			if (sm.isWalkable(nextCell)) {
				
				if (enemy(sm, nextPos)) {
					turn(goalDirection);
					System.out.println("It's a enemy !!");
				} else {
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
					goalDirection = ScreenMap.Door.direction;
				} catch (Exception e) { }
				ScreenMap.Door.reset();
				if (nMap == null || x == null || y == null) return;
				map = nMap;
				setPosition(x*sm.tileWidth, y*sm.tileHeight);
				System.out.println("Go to: " + map + " in " + x + " x " + y);
			}
		}
	}

    private boolean enemy(ScreenMap sm, Vector2 nextPos) {
		Vector<Enemy> enemies = PokeMudry.getEnemies();
		for (Enemy enemy : enemies) {
			boolean bMap = sm.map.equals(enemy.getMap());
			int pX = (int) nextPos.x/sm.tileWidth;
			int pY = (int) nextPos.y/sm.tileHeight;
			int eX = (int) enemy.position.x/sm.tileWidth;
			int eY = (int) enemy.position.y/sm.tileHeight;
			
			if(bMap && pX==eX && pY==eY) {
				lastEnemy = enemy;
				frontOfEnemy = true;
				return true;
			}
		}
		return false;
	}

    @Override
    protected void removedPv(int pv) {
        // TODO Auto-generated method stub
        
    }
}
