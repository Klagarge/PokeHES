import java.util.Vector;

import com.badlogic.gdx.Input;

import Control.Controller;
import Entity.Enemy;
import Entity.Entity;
import Entity.Player;
import Screen.ScreenMap;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;


public class testHER extends PortableApplication{

	private Controller controller;
	private ScreenMap sm;
	private Player p1;
	private static Vector<Entity> entities = new Vector<>();
	private static Vector<Player> players = new Vector<>();
	

	public testHER(){
		controller = new Controller();
		sm = new ScreenMap();
	}



    public static void main(String[] args) {
        new testHER();
    }

    @Override
    public void onInit() {
		controller.init();
        sm.init();
		p1 = new Player(8, 15);
		entities.add((Entity) p1);
		entities.add(new Enemy("Mudry", 10, 15, "lumberjack_sheet32"));
		entities.add(new Enemy("Pignat", 12, 15, "lumberjack_sheet32"));
		
		for (Entity entity : entities) {
			entity.init();
		}
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
		g.clear();
		p1.manageEntity(sm, controller);
        sm.graphicRender(g, p1);
		
		for (Entity entity : entities) {
			entity.graphicRender(g);
		}
    }

    @Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);

		controller.keyStatus.put(keycode, false);
	}

    @Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);

		switch (keycode) {
		case Input.Keys.Z:
			if (sm.zoom == 1.0) {
				sm.zoom = 0.5f;
			} else if (sm.zoom == 0.5) {
				sm.zoom = 0.25f;
			} else {
				sm.zoom = 1;
			}
			return;

		default:
			break;
		}
		controller.keyStatus.put(keycode, true);
	}
    
}
