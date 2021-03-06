package Screen;

import Entity.Enemy;
import Entity.Player;
import Game.Battle;
import ch.hevs.gdx2d.lib.GdxGraphics;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class ScreenPlayer {
    public ManagerOfScreen screenManager = new ManagerOfScreen();
    public Player p = null;
    public Enemy e = null;
    public Battle b = null;
    public ScreenMap sm = null;
    public ScreenBattle sb = null;
    public ScreenEnd se = null;

    public void init(){

        // One player by ScreenPlayer
        p = new Player(4, 2, "21RI");

        e = new Enemy("enemy", 0, 0, "21RI", 50, "enemy");
        b = new Battle(e);

        // Create both type of screen and record for reuse
        screenManager.registerScreen(ScreenMap.class);
        screenManager.registerScreen(ScreenBattle.class);
        screenManager.registerScreen(ScreenEnd.class);
        sb = screenManager.getScreenBattle();
        se = screenManager.getScreenEnd();
        sm = screenManager.getScreenMap();
        
    }

    public void render(GdxGraphics g){
        if(sm != null){
            sm.setPlayer(p);
        }
        
        if(sb != null){
            sb.setBattle(b);
            b.setEnemy(e);
            b.setPlayer(p);
            sb.setImg();
        }

        if(se != null){
            se.setText(p);
        }
        
        screenManager.render(g);
    }

}