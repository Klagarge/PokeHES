package Screen;

import Entity.Enemy;
import Entity.Player;
import Game.Battle;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenPlayer {
    public ManagerOfScreen screenManager = new ManagerOfScreen();
    public Player p = null;
    public Enemy e = null;
    public Battle b = null;
    public ScreenMap sm = null;
    public ScreenBattle sb = null;

    public void init(){

        // One player by ScreenPlayer
        p = new Player(8, 15, "desert");

        b = new Battle(new Enemy("enemi", 0, 0, "charachter", "desert", 50, "enemi"));

        // Create both type of screen and record for reuse
        screenManager.registerScreen(ScreenMap.class);
        screenManager.registerScreen(ScreenBattle.class);
        sb = screenManager.getScreenBattle();
        sm = screenManager.getScreenMap();

    }

    public void render(GdxGraphics g){
        if(sm != null){
            sm.setPlayer(p);
        }
        
        if(sb != null){
            sb.setBattle(b);
            
        }
        
        screenManager.render(g);
    }

}