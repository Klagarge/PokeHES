package Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.components.bitmaps.Spritesheet;
import ch.hevs.gdx2d.lib.GdxGraphics;

public abstract class Character extends Entity{

    public enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT,
        NULL
    }

    /**
     * The currently selected sprite for animation
     */
    int textureX = 0;
    int textureY = 1;
    float speed = 1;

    float dt = 0;
    int currentFrame = 0;
    int nFrames = 4;
    final float FRAME_TIME = 0.1f; // Duration of each frime
    private String img;
    private String imgBattle;

    protected int pv;

    /**
     * Create a character on the world
     * @param name Name of the new character
     * @param x initial x position
     * @param y initial y position
     * @param img the name of the spritesheet for this character
     * @param map the initial map
     */
    public Character(String name, int x, int y, String img, String map){
        super(name, x, y, map);
        this.img = img;

        imgBattle = "./Data/img/" + name + ".png";
    }

    @Override
    public void init() {
        super.init();
        ss = new Spritesheet("./Data/img/" + img + ".png", SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    @Override
    public void graphicRender(GdxGraphics g) {
        super.graphicRender(g);
        animate(Gdx.graphics.getDeltaTime());
		draw(g);
    }

    /**
     * Update the position and the texture of the hero.
     * @param elapsedTime The time [s] elapsed since the last time which this method was called.
     */
    public void animate(double elapsedTime) {
        float frameTime = FRAME_TIME / speed;

        position = new Vector2(lastPosition);
        if(isMoving()) {
            dt += elapsedTime;
            float alpha = (dt+frameTime*currentFrame)/(frameTime*nFrames);

            position.interpolate(newPosition, alpha,Interpolation.linear);
        }else{
            dt = 0;
        }

        if (dt > frameTime) {
            dt -= frameTime;
            currentFrame = (currentFrame + 1) % nFrames;

            if(currentFrame == 0){
                move = false;
                lastPosition = new Vector2(newPosition);
                position = new Vector2(newPosition);
            }
        }
    }

    /**
     * @return True if the entity is actually doing a step.
     */
    public boolean isMoving(){
        return move;
    }

    /**
     * @param speed The new speed of the character.
     */
    public void setSpeed(float speed){
        this.speed = speed;
    }

    /**
     * Do a step on the given direction
     * @param direction The direction to go.
     */
    public void go(Direction direction){
        move = true;
        switch(direction){
            case RIGHT:
                newPosition.add(SPRITE_WIDTH, 0);
                break;
            case LEFT:
                newPosition.add(-SPRITE_WIDTH, 0);
                break;
            case UP:
                newPosition.add(0, SPRITE_HEIGHT);
                break;
            case DOWN:
                newPosition.add(0, -SPRITE_HEIGHT);
                break;
            default:
                break;
        }

        turn(direction);
    }

    /**
     * Turn the hero on the given direction without do any step.
     * @param direction The direction to turn.
     */
    public void turn(Direction direction){
        switch(direction){
            case RIGHT:
                textureY = 2;
                break;
            case LEFT:
                textureY = 3;
                break;
            case UP:
                textureY = 1;
                break;
            case DOWN:
                textureY = 0;
                break;
            default:
                break;
        }
    }

    /**
     * Draw the character on the graphic object.
     * @param g Graphic object.
     */
    public void draw(GdxGraphics g) {
        g.draw(ss.sprites[textureY][currentFrame], position.x, position.y);
    }

    public int getPv() {
        return pv;
    }

    abstract protected void removedPv(int pv);

    public String getImgBattle(){
        return imgBattle;
    }
}
