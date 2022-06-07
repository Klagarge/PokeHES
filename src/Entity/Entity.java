package Entity;

import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.components.bitmaps.Spritesheet;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Entity implements DrawableObject {
    protected String name;

    Spritesheet ss;
    
    protected final static int SPRITE_WIDTH = 32;
    protected final static int SPRITE_HEIGHT = 32;

    Vector2 lastPosition;
    Vector2 newPosition;
    Vector2 position;

    protected boolean move = false;

    public Entity(String name){
        this(name, new Vector2(0,0));
    }

    public Entity(String name, int x, int y){
        this(name, new Vector2(SPRITE_WIDTH * x, SPRITE_HEIGHT * y));
    }

    public Entity(String name, Vector2 initialPosition){
        this.name = name;
        lastPosition = new Vector2(initialPosition);
        newPosition = new Vector2(initialPosition);
        position = new Vector2(initialPosition);
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
}
