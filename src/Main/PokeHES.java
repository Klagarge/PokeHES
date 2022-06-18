package Main;

import java.util.Vector;

import Control.Controller;
import Entity.Enemy;
import Entity.Entity;
import Game.Battle;
import Screen.ScreenBattle;
import Screen.ScreenEnd;
import Screen.ScreenMap;
import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeHES extends PortableApplication {

    private ScreenPlayer sp;
    private Controller controller;
    private static Vector<Enemy> enemies = new Vector<>();
	private static Vector<Entity> entities = new Vector<>();
    private long beginTime;
    private long lastMesure;
    private long stairTime;

    public static boolean risingFront = false;


    public static void main(String[] args) {
        new PokeHES();
    }

    PokeHES(){
        super(Settings.SIDE, Settings.SIDE);
        controller = new Controller();
        sp = new ScreenPlayer();
    }

    public static Vector<Enemy> getEnemies() {
		return enemies;
	}

    @Override
    public void onInit() {
        setTitle("PokeHES");
        
        sp.init();
        controller.init();
        
        // add player, create and add all enemies in entities
		entities.add((Entity) sp.p);
		enemies.add(new Enemy("Gloeckner", 1, 7, "21N307", 600, "allemand"));
		enemies.add(new Enemy("Nicollier", 4, 2,  "21N308", 1600, "mathematique"));
		enemies.add(new Enemy("Mudry", 5, 11,  "21N304", 700, "informatique"));
		enemies.add(new Enemy("Ellert", 1, 4, "23N215", 300, "physique"));
		enemies.add(new Enemy("Bianchi", 1, 3, "23N308", 1200, "electricite"));
		enemies.add(new Enemy("Paciotti", 5, 11, "21N205", 1200, "mecanique"));
        for (Enemy enemy : enemies) { entities.add(enemy); }
        
		//Init all entities
        for (Entity entity : entities) { entity.init(); }

        beginTime = System.currentTimeMillis();
        lastMesure = beginTime;
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        g.clear();
        
        boolean onMapScreen = sp.screenManager.getActiveScreen().getClass().equals(ScreenMap.class);
        boolean onBattleScreen = sp.screenManager.getActiveScreen().getClass().equals(ScreenBattle.class);
        boolean onEndScreen = sp.screenManager.getActiveScreen().getClass().equals(ScreenEnd.class);

        long timeNow = System.currentTimeMillis();
        if((timeNow-lastMesure) >= 1000 && !onEndScreen){ // one second during the game
            lastMesure = timeNow;
            sp.p.removedPv(1);
            for (Enemy enemy : enemies) { enemy.recoveredTime++; }
        }

        //if (sp.p.onDoor) {
        //    while (System.currentTimeMillis()-timeNow < Settings.SWITCH_MAP_TIME) { g.clear(); }
        //}
        
		
        if(onMapScreen) sp.p.manageEntity(sp.sm, controller);
        
        // Switch to battle
        if (sp.p.onEnemy && onMapScreen){ // if player is onEnemy and on map screen
            sp.e = sp.p.lastEnemy; // Get this enemy
            
            int pv = sp.e.getPv(); // get how many pv have the enemy
            boolean recovered = sp.e.recoveredTime>=Settings.RECOVERED;
            
            // if the enemy is alive and have recovered, switch to Battle
            if (pv>0 && recovered) {
                sp.b = new Battle(sp.e);
                sp.sb = sp.screenManager.getScreenBattle();
                
                //set pv and xp to display
                sp.b.setXpPlayer(sp.p.getXp());
                
                g.zoom(1);
                g.resetCamera();
            } else {
                sp.p.onEnemy = false;
            }
            
        }
        

        if(onBattleScreen) sp.sb.manage(controller, sp.b);

        // switch to Map
        if(!sp.b.getScreenBattleOn() && onBattleScreen){
            
            sp.p.addXp(sp.b.getNewXp()); //add Xp for the player
            sp.e.removedPv(sp.b.getNewXp()); //remove pv of the enemy

            sp.p.onEnemy = false;
            sp.sm = sp.screenManager.getScreenMap();
        }

        // End of the game
        if((sp.p.getPv() <= 0 || sp.p.getXp() >= sp.p.getXpMax() ) && !onEndScreen  ) {
            g.zoom(1);
            g.resetCamera();
            sp.se = sp.screenManager.getScreenEnd();
            System.out.println("Game finished");
        }

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
        risingFront = true;
        controller.keyStatus.put(keycode, true);
        sp.screenManager.getActiveScreen().onKeyUp(keycode);
    }
    @Override
    public void onKeyUp(int keycode) {
        super.onKeyUp(keycode);
        risingFront = false;
        controller.keyStatus.put(keycode, false);
        sp.screenManager.getActiveScreen().onKeyDown(keycode);
    }
}
