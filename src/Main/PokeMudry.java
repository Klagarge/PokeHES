package Main;


import java.util.Vector;
import com.badlogic.gdx.Input;

import Control.Controller;
import Entity.Enemy;
import Entity.Entity;
import Game.Battle;
import Screen.ScreenBattle;
import Screen.ScreenMap;
import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeMudry extends PortableApplication {

    private ScreenPlayer sp;
    private Controller controller;
    private static Vector<Enemy> enemies = new Vector<>();
	private static Vector<Entity> entities = new Vector<>();

    public static boolean front_montant = false;


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

        // add player, create and add all enemies in entities
		entities.add((Entity) sp.p);
		enemies.add(new Enemy("Mudry", 10, 15, "lumberjack_sheet32", "desert"));
		enemies.add(new Enemy("Pignat", 5, 1, "lumberjack_sheet32", "test"));
        for (Enemy enemy : enemies) { entities.add(enemy); }

		//Init all entities
        for (Entity entity : entities) { entity.init(); }
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        g.clear();
        boolean onMapScreen = sp.screenManager.getActiveScreen().getClass().equals(ScreenMap.class);
        boolean onBattleScreen = sp.screenManager.getActiveScreen().getClass().equals(ScreenBattle.class);
		
        if(onMapScreen) sp.p.manageEntity(sp.sm, controller);
        
        // Switch screen
        if (sp.p.onEnemy && onMapScreen){
            sp.e = sp.p.lastEnemy;
            sp.sb = sp.screenManager.getScreenBattle();
            sp.b = new Battle(sp.e);
            g.resetCamera();
        }
        
        if(onBattleScreen) sp.sb.manage(controller, sp.b);

        // Graphics render
        sp.render(g);
        for (Entity entity : entities) {
            // Render only entities on the good map
            if (entity.getMap().equals(sp.sm.map) && onMapScreen)
            entity.graphicRender(g);
		}
    }


    //key gestion
    @Override
    public void onKeyDown(int keycode) {
        super.onKeyDown(keycode);
        front_montant = true;
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
        front_montant = false;
        controller.keyStatus.put(keycode, false);
        sp.screenManager.getActiveScreen().onKeyDown(keycode);
    }
}
