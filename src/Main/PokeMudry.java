package Main;

import java.util.Vector;

import Control.Controller;
import Entity.Character.Direction;
import Entity.Enemy;
import Entity.Entity;
import Game.Battle;
import Screen.ScreenBattle;
import Screen.ScreenEnd;
import Screen.ScreenMap;
import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeMudry extends PortableApplication {

    private ScreenPlayer sp;
    private Controller controller;
    private static Vector<Enemy> enemies = new Vector<>();
	private static Vector<Entity> entities = new Vector<>();
    private long beginTime;
    private long lastMesure;

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
		enemies.add(new Enemy("Gloeckner", 1, 7, "lumberjack_sheet32", "21N307", 600, "allemand", Direction.RIGHT));
		enemies.add(new Enemy("Nicollier", 4, 2, "lumberjack_sheet32", "21N308", 1600, "mathematique", Direction.LEFT));
		enemies.add(new Enemy("Mudry", 5, 11, "lumberjack_sheet32", "21N304", 700, "informatique", Direction.DOWN));
		enemies.add(new Enemy("Ellert", 1, 4, "lumberjack_sheet32", "23N215", 300, "physique", Direction.RIGHT));
		enemies.add(new Enemy("Bianchi", 1, 3, "lumberjack_sheet32", "23N308", 1200, "electricite", Direction.RIGHT));
		enemies.add(new Enemy("Paciotti", 5, 11, "lumberjack_sheet32", "21N205", 1200, "mecanique", Direction.DOWN));
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
        if((timeNow-lastMesure) >= 1000 && !onEndScreen){ // one second
            lastMesure = timeNow;
            sp.p.removedPv(1);
            for (Enemy enemy : enemies) { enemy.recoveredTime++; }
        }

        //end of the game
        if(sp.p.getPv() <= 0 && !onEndScreen) {
            g.zoom(1);
            g.resetCamera();
            sp.se = sp.screenManager.getScreenEnd();
            System.out.println("Game finished");
        }
		
        if(onMapScreen) sp.p.manageEntity(sp.sm, controller);
        


        // Switch screen
        if (sp.p.onEnemy && onMapScreen){
            sp.e = sp.p.lastEnemy;

            int pv = sp.e.getPv();
            boolean recovered = sp.e.recoveredTime>=Settings.RECOVERED;


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


        if(!sp.b.getScreenBattleOn() && onBattleScreen){
            //addXp for the player
            sp.p.addXp(sp.b.getNewXp());
            //remove pv of the enemy 
            sp.e.removedPv(sp.b.getNewXp());

            sp.p.onEnemy = false;
            sp.sm = sp.screenManager.getScreenMap();
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
        front_montant = true;
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
