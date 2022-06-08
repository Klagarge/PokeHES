package Screen;

import Entity.Player;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;

public class ScreenPlayer {
    public ScreenManager screenManager = new ScreenManager();

    private Player player;

    public void init(){
        
        player = new Player(8, 15);


        screenManager.registerScreen(ScreenMap.class);
        screenManager.registerScreen(ScreenBattle.class);

    }

    public void render(GdxGraphics g){
        screenManager.render(g);
        
    }
}
