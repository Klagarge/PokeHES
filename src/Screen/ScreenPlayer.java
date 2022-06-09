package Screen;

import Entity.Player;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenPlayer {
    public ManagerOfScreen screenManager = new ManagerOfScreen();
    public Player p;
    public ScreenMap sm;


    public void init(){
        screenManager.registerScreen(ScreenMap.class);
        screenManager.registerScreen(ScreenBattle.class);
        sm = screenManager.getScreenMap();
        p = new Player(8, 15);
    }

    public void render(GdxGraphics g){
        screenManager.render(g);
        sm.camera(g, p);
    }

}