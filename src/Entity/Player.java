package Entity;

import java.util.Vector;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;

import Control.Controller;
import Main.PokeHES;
import Main.Settings;
import Screen.ScreenMap;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class Player extends Character{

    private int xp = 0;
	public Enemy lastEnemy = null;
	public boolean onEnemy = false;
	private static final int XP_MAX = 6000;
	public boolean onDoor;
	private boolean moveBlocked;
	private long beginBlocked;

    /**
	 * Create a player
	 * @param x initial x position
	 * @param y initial y position
	 * @param map initial map
	 */
	public Player(int x, int y, String map) {
        super("player", x, y, "sprite_sacha", map);
		this.pv = Settings.TIME*60;
    }

    public void addXp(int xp){
		this.xp += xp;
    }

	public int getXp(){
		return xp;
	}

    /**
	 * All action for manage the Player
	 * @param sm the screenMap where is the player
	 * @param c the controller of this player
	 */
	public void manageEntity(ScreenMap sm, Controller c) {

		onDoor = sm.isDoor(getPosition());
		if(onDoor){
			moveBlocked = true;
			beginBlocked = System.currentTimeMillis();
		}
		if(System.currentTimeMillis() - beginBlocked >= Settings.SWITCH_MAP_TIME && !onDoor) { moveBlocked = false; }

		// Do nothing if hero is already moving
		if (!isMoving()) {

			// Compute direction and next cell
			Vector<TiledMapTile> nextCell = new Vector<>();
			Player.Direction goalDirection = Player.Direction.NULL;
			Vector2 nextPos = new Vector2(position);



			if (c.keyStatus.get(Input.Keys.RIGHT) && !moveBlocked) {
				goalDirection = Player.Direction.RIGHT;
				nextCell = sm.getTile(getPosition(), 1, 0);
				nextPos.x+=sm.tileWidth;
			} else if (c.keyStatus.get(Input.Keys.LEFT) && !moveBlocked) {
				goalDirection = Player.Direction.LEFT;
				nextCell = sm.getTile(getPosition(), -1, 0);
				nextPos.x-=sm.tileWidth;
			} else if (c.keyStatus.get(Input.Keys.UP) && !moveBlocked) {
				goalDirection = Player.Direction.UP;
				nextCell = sm.getTile(getPosition(), 0, 1);
				nextPos.y+=sm.tileHeight;
			} else if (c.keyStatus.get(Input.Keys.DOWN) && !moveBlocked) {
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
					setSpeed(sm.getSpeed(nextCell)*1.5f);
					go(goalDirection);
				}
			} else {
				// Face the wall
				turn(goalDirection);
			}

			
			if(onDoor){
				//long time = System.currentTimeMillis();
				//while (System.currentTimeMillis()-time < Settings.SWITCH_MAP_TIME) { }
				String nMap = null;
				Integer x = null;
				Integer y = null;
				try {
					nMap = ScreenMap.Door.nextMap;
					x = ScreenMap.Door.nextX;
					y = ScreenMap.Door.nextY;
					goalDirection = ScreenMap.Door.nextDirection;
				} catch (Exception e) { }
				ScreenMap.Door.reset();
				if (nMap == null || x == null || y == null) return;
				map = nMap;
				if(map.equals("FabLab")) addXp(400); // * Like an Easter egg, but necessary for win the game
				setPosition(x*sm.tileWidth, y*sm.tileHeight);
				turn(goalDirection);
				System.out.println("Go to: " + map + " in " + x + " x " + y);
			}
		}
	}

    /**
	 * Return true if an enemy is on next position
	 * @param sm Screen map where is the player
	 * @param nextPos Vector of next position
	 * @return true if an enemy is on next position
	 */
	private boolean enemy(ScreenMap sm, Vector2 nextPos) {
		Vector<Enemy> enemies = PokeHES.getEnemies();
		for (Enemy enemy : enemies) {
			boolean bMap = sm.map.equals(enemy.getMap());
			int pX = (int) nextPos.x/sm.tileWidth;
			int pY = (int) nextPos.y/sm.tileHeight;
			int eX = (int) enemy.position.x/sm.tileWidth;
			int eY = (int) enemy.position.y/sm.tileHeight;
			
			if(bMap && pX==eX && pY==eY) {
				lastEnemy = enemy;
				onEnemy = true;
				return true;
			}
		}
		return false;
	}

    @Override
    public void removedPv(int pv) {
        this.pv -= pv;
    }

	public int getXpMax(){
		return XP_MAX;
	}
}
