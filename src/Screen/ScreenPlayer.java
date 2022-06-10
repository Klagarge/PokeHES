package Screen;

import Entity.Player;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenPlayer {
    public ManagerOfScreen screenManager = new ManagerOfScreen();
    public Player p;
    public ScreenMap sm;

    public void init(){
        p = new Player(8, 15, "desert");
        screenManager.registerScreen(ScreenMap.class);
        screenManager.registerScreen(ScreenBattle.class);
        sm = screenManager.getScreenMap();
    }

    public void render(GdxGraphics g){
        sm.setPlayer(p);
        screenManager.render(g);
    }

}