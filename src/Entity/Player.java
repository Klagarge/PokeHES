package Entity;

import java.util.Vector;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Vector2;

import Control.Controller;
import Main.PokeMudry;
import Main.Settings;
import Screen.ScreenMap;

public class Player extends Character{

    private int xp = 0;
	public Enemy lastEnemy = null;
	public boolean onEnemy = false;
	private static final int XP_MAX = 6000;

    public Player(int x, int y, String map) {
        super("Player", x, y, "sprite_sacha", map); //Character_flipped
		this.pv = Settings.TIME*60;
    }

    public void addXp(int xp){
		this.xp += xp;
    }

	public int getXp(){
		return xp;
	}

    public void manageEntity(ScreenMap sm, Controller c) {

		boolean onDoor = sm.isDoor(getPosition());

		// Do nothing if hero is already moving
		if (!isMoving()) {

			// Compute direction and next cell
			Vector<TiledMapTile> nextCell = new Vector<>();
			Player.Direction goalDirection = Player.Direction.NULL;
			Vector2 nextPos = new Vector2(position);



			if (c.keyStatus.get(Input.Keys.RIGHT) && !onDoor) {
				goalDirection = Player.Direction.RIGHT;
				nextCell = sm.getTile(getPosition(), 1, 0);
				nextPos.x+=sm.tileWidth;
			} else if (c.keyStatus.get(Input.Keys.LEFT) && !onDoor) {
				goalDirection = Player.Direction.LEFT;
				nextCell = sm.getTile(getPosition(), -1, 0);
				nextPos.x-=sm.tileWidth;
			} else if (c.keyStatus.get(Input.Keys.UP) && !onDoor) {
				goalDirection = Player.Direction.UP;
				nextCell = sm.getTile(getPosition(), 0, 1);
				nextPos.y+=sm.tileHeight;
			} else if (c.keyStatus.get(Input.Keys.DOWN) && !onDoor) {
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
				long time = System.currentTimeMillis();
				while (System.currentTimeMillis()-time < Settings.SWITCH_MAP_TIME) { }
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
