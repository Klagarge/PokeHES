package Screen;

import Entity.Enemy;
import Entity.Player;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenPlayer {
    public ManagerOfScreen screenManager = new ManagerOfScreen();
    public Player p;
    public Enemy e;
    public ScreenMap sm;
    public ScreenBattle sb;

    public void init(){
        p = new Player(8, 15, "desert");
        screenManager.registerScreen(ScreenMap.class);
        screenManager.registerScreen(ScreenBattle.class);
        sb = screenManager.getScreenBattle();
        sm = screenManager.getScreenMap();
    }

    public void render(GdxGraphics g){
        sb.displayEnemy(e);
        sm.setPlayer(p);
        screenManager.render(g);
    }

}