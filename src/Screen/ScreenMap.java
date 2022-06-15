package Screen;

import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import Entity.Character;
import Entity.Player;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenMap extends RenderingScreen{

	// tiles management
	private Vector<TiledMapTileLayer> tiledLayer = new Vector<>();
	private MapObjects doors;
	Map<String,TiledMap> tMap = new TreeMap<String,TiledMap>();
	Map<String,TiledMapRenderer> tMapRenderer = new TreeMap<String,TiledMapRenderer>();
	public String map;
	public float zoom;
    private int width;
    public int tileWidth;
    private int height;
    public int tileHeight;
    private Player player;
    
    private void createMap(String name){
		TiledMap tm =new TmxMapLoader().load("./resources/map/"+ name + ".tmx");
		tMap.put(name,tm);
		tMapRenderer.put(name,new OrthogonalTiledMapRenderer(tm));
	}

    @Override
    public void onInit() {
		// Set initial zoom
		zoom = 1;

        try { map = player.getMap(); } catch (Exception e) {}

		// create map
		createMap("test");
		createMap("test_couloir");
		createMap("desert");

        createMap("SS");
        //createMap("RI");
        //createMap("RS");
        
        createMap("21RI");
        createMap("21RS");
        createMap("21N2");
        createMap("21N205");
        createMap("21N3");
        createMap("21N304");
        createMap("21N307");
        createMap("21N308");
        
        createMap("23RI");
        createMap("23RS");
        createMap("23N1");
        //createMap("23N2");
        //createMap("23N215");
        //createMap("23N3");
        //createMap("23N308");
	}

    @Override
    public void onGraphicRender(GdxGraphics g) {
        
        tiledLayer.clear();
        
        // Get actual map of the player
        try { map = player.getMap(); } catch (Exception e) {}

		// Get all layers on the current map
        for (int i = 0; i < 50; i++) {
            try { tiledLayer.add((TiledMapTileLayer) tMap.get(map).getLayers().get(i)); } catch (Exception e) { }
        }

        // Get heigh and width of tiles and the size of the tiles
        TiledMapTileLayer tl = tiledLayer.get(0);
        width = tl.getWidth();
        tileWidth = (int) tl.getTileWidth();
        height = tl.getHeight();
        tileHeight = (int) tl.getTileHeight();
        
        //System.out.println(width + " x " + height + " - " + tileWidth + " x " + tileHeight);
        
		// Get all doors on the current map
        try {
            doors = tMap.get(map).getLayers().get("door").getObjects();
		} catch (Exception e) {	doors = null; }
        
		
		// Render the tileMap
        g.zoom(zoom);
        g.moveCamera((int)player.getPosition().x, (int)player.getPosition().y, width * tileWidth, height * tileHeight);

		tMapRenderer.get(map).setView(g.getCamera());
		tMapRenderer.get(map).render();
        
		g.drawFPS();
	}


    public Vector<TiledMapTile> getTile(Vector2 position, int offsetX, int offsetY) {
        Vector<TiledMapTile> tiles = new Vector<>();

        for (TiledMapTileLayer tl : tiledLayer) {
            int x = (int) (position.x / tileWidth) + offsetX;
            int y = (int) (position.y / tileHeight) + offsetY;
            try {
				Cell cell = tl.getCell(x, y);
				if (cell == null) continue;
                tiles.add(cell.getTile());
            } catch (Exception e) { System.out.println("error: cell");}
        }

        return tiles;
	}

    public boolean isWalkable(Vector<TiledMapTile> tile) {
		if (tile == null) return false;
		if (tile.size() == 0) return false;
        boolean walkable = true;
        for (TiledMapTile tiledMapTile : tile) {
            Object test = tiledMapTile.getProperties().get("walkable");
            walkable = Boolean.parseBoolean(test.toString()) ? walkable:false;
        }
        return walkable;
	}

    public float getSpeed(Vector<TiledMapTile> tile) {
        float speed = 0;
        for (TiledMapTile tiledMapTile : tile) {
            Object test = tiledMapTile.getProperties().get("speed");
            float newSpeed = Float.parseFloat(test.toString());
            speed = newSpeed > speed ? newSpeed:speed;
        }
        return speed;
	}

    public boolean isDoor(Vector2 position) {
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
				try { Door.nextMap = mapProperties.get("nextMap").toString(); } catch (Exception e) { }
				try { Door.nextX = Integer.parseInt(mapProperties.get("nextX").toString()); } catch (Exception e) { }
				try { Door.nextY = Integer.parseInt(mapProperties.get("nextY").toString()); } catch (Exception e) { }
                try { Door.nextDirection = Character.Direction.valueOf(mapProperties.get("nextDirection").toString()); } catch (Exception e) { }
			}
        }
        
        return onDoor;
	}

	public static class Door {
		public static String nextMap;
		public static Integer nextX;
		public static Integer nextY;
        public static Character.Direction nextDirection;

		public static void reset(){
			nextMap = null;
			nextX = null;
			nextY = null;
            nextDirection = null;
		}
	}

    public void setPlayer(Player p) {
        player = p;
    }
}
