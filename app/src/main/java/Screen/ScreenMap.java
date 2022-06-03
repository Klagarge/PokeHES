package Screen;

import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class ScreenMap {
    // key management
	public Map<Integer, Boolean> keyStatus = new TreeMap<Integer, Boolean>();

	// character
	private Hero hero;

	// tiles management
	private Vector<TiledMapTileLayer> tiledLayer = new Vector<>();
	private MapObjects doors;
	Map<String,TiledMap> tMap = new TreeMap<String,TiledMap>();
	Map<String,TiledMapRenderer> tMapRenderer = new TreeMap<String,TiledMapRenderer>();
	private String map = "desert";
	public float zoom;
    private int width;
    private int tileWidth;
    private int height;
    private int tileHeight;


    // position
	Vector2 pannel = new Vector2(30, 30);

    private void createMap(String name){
		TiledMap tm =new TmxMapLoader().load("app/src/main/resources/map/"+ name + ".tmx");
		tMap.put(name,tm);
		tMapRenderer.put(name,new OrthogonalTiledMapRenderer(tm));
	}

    public void init() {

		// Create hero
		hero = new Hero(9, 4);

		// Set initial zoom
		zoom = 1;

		// init keys status
		keyStatus.put(Input.Keys.UP, false);
		keyStatus.put(Input.Keys.DOWN, false);
		keyStatus.put(Input.Keys.LEFT, false);
		keyStatus.put(Input.Keys.RIGHT, false);

		// create map
		createMap("test");
		createMap("test_couloir");
		createMap("desert");
	}

    public void graphicRender(GdxGraphics g) {
		g.clear();

        tiledLayer.clear();
		for (int i = 0; i < 50; i++) {
            try { tiledLayer.add((TiledMapTileLayer) tMap.get(map).getLayers().get(i)); } catch (Exception e) { }
        }
		//System.out.println(tiledLayer.size() + " layers imported");
        TiledMapTileLayer tl = tiledLayer.get(0);
        width = tl.getWidth();
        tileWidth = (int) tl.getTileWidth();
        height = tl.getHeight();
        tileHeight = (int) tl.getTileHeight();
        //System.out.println(width + " x " + height + " - " + tileWidth + " x " + tileHeight);
		try {
			doors = tMap.get(map).getLayers().get("door").getObjects();
		} catch (Exception e) {	doors = null; }

        // Hero activity
		manageHero();
        System.out.println("Hero: " + (int)hero.getPosition().x/tileWidth + " x " + (int)hero.getPosition().y/tileHeight);
		
		// Camera follows the hero
		g.zoom(zoom);
		g.moveCamera(hero.getPosition().x, hero.getPosition().y, width * tileWidth, height * tileHeight);
        
		// Render the tileMap
		tMapRenderer.get(map).setView(g.getCamera());
		tMapRenderer.get(map).render();
		
		// Draw the hero
		hero.animate(Gdx.graphics.getDeltaTime());
		hero.draw(g);

		g.drawFPS();
	}

    private Vector<TiledMapTile> getTile(Vector2 position, int offsetX, int offsetY) {
        Vector<TiledMapTile> tiles = new Vector<>();
        for (TiledMapTileLayer tl : tiledLayer) {
            int x = (int) (position.x / tileWidth) + offsetX;
            int y = (int) (position.y / tileHeight) + offsetY;
            try {
				Cell cell = tl.getCell(x, y);
				if (cell == null) continue;
                tiles.add(cell.getTile());
            } catch (Exception e) { }
        }

        return tiles;
	}

    private boolean isWalkable(Vector<TiledMapTile> tile) {
		if (tile == null) return false;
        boolean walkable = false;
        for (TiledMapTile tiledMapTile : tile) {
            Object test = tiledMapTile.getProperties().get("walkable");
            walkable = Boolean.parseBoolean(test.toString()) ? true:walkable;
        }
        return walkable;
	}

    private float getSpeed(Vector<TiledMapTile> tile) {
        float speed = 0;
        for (TiledMapTile tiledMapTile : tile) {
            Object test = tiledMapTile.getProperties().get("speed");
            float newSpeed = Float.parseFloat(test.toString());
            speed = newSpeed > speed ? newSpeed:speed;
        }
        return speed;
	}

    private boolean isDoor(Vector2 position) {
		if (doors == null) return false;
        boolean onDoor = false;
        Integer x = null;
        Integer y = null;
        int ox = 0;
        int oy = 0;
        try {
            x = (int) (position.x / tileWidth);
            y = (int) (position.y / tileHeight);
        } catch (Exception e) { }

        for (MapObject object : doors){
            MapProperties mapProperties = null;
            try { mapProperties = object.getProperties(); } catch (Exception e) { }
            try { ox = (int) ((float) mapProperties.get("x")); } catch (Exception e) { }
            try { oy = (int) ((float) mapProperties.get("y")); } catch (Exception e) { }

            ox /= tileWidth;
            oy /= tileHeight;

			if ((x != null || y != null) && (x == ox && y == oy)) {
				onDoor = true;
				try { Door.nextMap = mapProperties.get("nextMap").toString(); } catch (Exception e) { System.out.println("shit 1"); }
				try { Door.nextX = Integer.parseInt(mapProperties.get("nextX").toString()); } catch (Exception e) {  System.out.println("shit 2");  }
				try { Door.nextY = Integer.parseInt(mapProperties.get("nextY").toString()); } catch (Exception e) {  System.out.println("shit 3"); }
			}
        }
        
        return onDoor;
	}

    private void manageHero() {

		// Do nothing if hero is already moving
		if (!hero.isMoving()) {

			// Compute direction and next cell
			Vector<TiledMapTile> nextCell = new Vector<>();
			Hero.Direction goalDirection = Hero.Direction.NULL;

			if (keyStatus.get(Input.Keys.RIGHT)) {
				goalDirection = Hero.Direction.RIGHT;
				nextCell = getTile(hero.getPosition(), 1, 0);
			} else if (keyStatus.get(Input.Keys.LEFT)) {
				goalDirection = Hero.Direction.LEFT;
				nextCell = getTile(hero.getPosition(), -1, 0);
			} else if (keyStatus.get(Input.Keys.UP)) {
				goalDirection = Hero.Direction.UP;
				nextCell = getTile(hero.getPosition(), 0, 1);
			} else if (keyStatus.get(Input.Keys.DOWN)) {
				goalDirection = Hero.Direction.DOWN;
				nextCell = getTile(hero.getPosition(), 0, -1);
			}

			// Is the move valid ?
			if (isWalkable(nextCell)) {
				// Go
				hero.setSpeed(getSpeed(nextCell));
				hero.go(goalDirection);
			} else {
				// Face the wall
				hero.turn(goalDirection);
			}

			
			if(isDoor(hero.getPosition())){
				String nMap = null;
				Integer x = null;
				Integer y = null;
				try {
					nMap = Door.nextMap;
					x = Door.nextX;
					y = Door.nextY;
				} catch (Exception e) { }
				Door.reset();
				if (nMap == null || x == null || y == null) return;
				map = nMap;
				hero.setPosition(x*tileWidth, y*tileHeight);
				System.out.println("Go to: " + map + " in " + x + " x " + y);
			}
		}
	}

	static class Door {
		static String nextMap;
		static Integer nextX;
		static Integer nextY;

		static void reset(){
			nextMap = null;
			nextX = null;
			nextY = null;
		}
	}
		
}
