package Main;


import java.util.Vector;
import com.badlogic.gdx.Input;
import Control.Controller;
import Entity.Enemy;
import Entity.Entity;
import Screen.ScreenMap;
import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeMudry extends PortableApplication {

    private ScreenPlayer sp;
    private Controller controller;
    private static Vector<Enemy> enemies = new Vector<>();
	private static Vector<Entity> entities = new Vector<>();


    public static void main(String[] args) {
        new PokeMudry();
    }

    PokeMudry(){
        super(Settings.SIDE, Settings.SIDE);
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
		enemies.add(new Enemy("Pignat", 5, 1, "lumberjack_sheet32", "test"));

        for (Enemy enemy : enemies) { entities.add(enemy); }

		for (Entity entity : entities) { entity.init(); }
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        g.clear();
		sp.p.manageEntity(sp.sm, controller);
        sp.render(g);
        System.out.println(sp.screenManager.getActiveScreen().getClass());
        //System.out.println(ScreenMap.class);
		for (Entity entity : entities) {
            
            if (entity.getMap().equals(sp.sm.map) && sp.screenManager.getActiveScreen().getClass().equals(ScreenMap.class))
                entity.graphicRender(g);

            
		}
        
        if (sp.p.frontOfEnemy && sp.screenManager.getActiveScreen().getClass().equals(ScreenMap.class)){
            sp.e = sp.p.lastEnemy;
            System.out.println("switch screen");
            sp.screenManager.activateNextScreen();
            g.resetCamera();
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
