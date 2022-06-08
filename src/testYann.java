
import java.util.TreeMap;
import java.util.Map;

import com.badlogic.gdx.Input;

import Control.Controller;
import Entity.Enemy;
import Screen.ScreenBattle;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;

public class testYann extends PortableApplication{

    private ScreenManager s = new ScreenManager();
    public Map<Integer, Boolean> keyStatus = new TreeMap<Integer, Boolean>();
    double zoom;
    Controller controller = new Controller();
    
    public static void main(String[] args) {
        new testYann();
        
    }

    testYann(){
		super(800, 800);
	}

    @Override
    public void onInit() {
        
        s.registerScreen(ScreenBattle.class);
        Enemy e = new Enemy("enemi", 50, 50, "resources//lumberjack_sheet32.png");
        
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        s.render(g);
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
		default:
			break;
		}
		controller.keyStatus.put(keycode, true);
	}
}
