package Entity;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    private Vector2 position;
    private String name;

    public Entity(String name){
        this.name = name;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
