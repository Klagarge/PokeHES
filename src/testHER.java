import com.badlogic.gdx.Input;

import Control.Controller;
import Entity.Entity;
import Entity.Player;
import Screen.ScreenMap;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;


public class testHER extends PortableApplication{

	private Controller controller;
	private ScreenMap sm;
	private Player p1;
	private static Entity[] entities;
	

	public testHER(){
		controller = new Controller();
		p1 = new Player(8, 15);
		sm = new ScreenMap();
	}



    public static void main(String[] args) {
        new testHER();
    }

    @Override
    public void onInit() {
		controller.init();
        sm.init();
		p1.init();
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
		g.clear();
		p1.manageEntity(sm, controller);
        sm.graphicRender(g, p1); // load p1 by Entity[]
		p1.graphicRender(g);
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
