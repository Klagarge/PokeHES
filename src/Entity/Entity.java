package Entity;

import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.components.bitmaps.Spritesheet;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * Main class for manage entity 
 * Can create all type of entity character or just stuff.
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public abstract class Entity implements DrawableObject {
    protected String name;
    protected String map;

    Spritesheet ss;
    
    protected final static int SPRITE_WIDTH = 32;
    protected final static int SPRITE_HEIGHT = 32;

    Vector2 lastPosition;
    Vector2 newPosition;
    Vector2 position;

    protected boolean move = false;


    /**
     * Create an entity
     * @param name The name of this new entity
     * @param x The initial x position
     * @param y The initial y position
     * @param map The initial map
     */
    public Entity(String name, int x, int y, String map){
        this(name, new Vector2(SPRITE_WIDTH * x, SPRITE_HEIGHT * y), map);
    }

    /**
     * Create an entity
     * @param name The name of this new entity
     * @param initialPosition The initial position by a Vector2
     * @param map The initial map
     */
    public Entity(String name, Vector2 initialPosition, String map){
        this.name = name;
        lastPosition = new Vector2(initialPosition);
        newPosition = new Vector2(initialPosition);
        position = new Vector2(initialPosition);
        this.map = map;
    }

    public void init(){
    }

    public void graphicRender(GdxGraphics g){
    }

    /**
     * @return the current position of the entity on the map.
     */
    public Vector2 getPosition(){
        return this.position;
    }

    public void setPosition(int x, int y){
        lastPosition.set(x, y);
        newPosition.set(x, y);
        position.set(x, y);
    }

    public String getName() {
        return name;
    }

    public String getMap() {
        return map;
    }
}
