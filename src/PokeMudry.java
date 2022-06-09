import java.util.Vector;

import com.badlogic.gdx.Input;

import Control.Controller;
import Entity.Enemy;
import Entity.Entity;
import Entity.Player;
import Screen.ScreenMap;
import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeMudry extends PortableApplication {
    public final boolean ANDROID = false;
    public final int PLAYERS = 1;
    public static final int TIME = 10; // number of minutes for kill all enemy

    private ScreenPlayer sp;
    private Controller controller;
	//private Player p1;
    private static Vector<Enemy> enemies = new Vector<>();
	private static Vector<Entity> entities = new Vector<>();

    
    public static void main(String[] args) {
        new PokeMudry();
    }

    PokeMudry(){
        super(1000, 800);
        controller = new Controller();
        sp = new ScreenPlayer();
    }

    public static Vector<Enemy> getEnemies() {
		return enemies;
	}

    @Override
    public void onInit() {
        sp.init();
        controller.init();
		entities.add((Entity) sp.p);
		enemies.add(new Enemy("Mudry", 10, 15, "lumberjack_sheet32", "desert"));
		enemies.add(new Enemy("Pignat", 12, 15, "lumberjack_sheet32", "desert"));

        for (Enemy enemy : enemies) {
            entities.add(enemy);
        }

		for (Entity entity : entities) {
			entity.init();
		}
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        g.clear();
		sp.p.manageEntity(sp.sm, controller);
        sp.render(g);
		for (Entity entity : entities) {
			entity.graphicRender(g);
		}
    }


    //key gestion
    @Override
    public void onKeyDown(int keycode) {
        super.onKeyDown(keycode);
        
        switch (keycode) {
            case Input.Keys.Z:
                if (sp.sm.zoom == 1.0) {
                    sp.sm.zoom = 0.5f;
                } else if (sp.sm.zoom == 0.5) {
                    sp.sm.zoom = 0.25f;
                } else {
                    sp.sm.zoom = 1;
                }
                return;
    
            default:
                break;
        }
        controller.keyStatus.put(keycode, true);
        sp.screenManager.getActiveScreen().onKeyUp(keycode);
    }
    @Override
    public void onKeyUp(int keycode) {
        super.onKeyUp(keycode);
        controller.keyStatus.put(keycode, false);
        sp.screenManager.getActiveScreen().onKeyDown(keycode);
    }
}
