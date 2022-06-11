
import java.util.Map;
import java.util.TreeMap;

import Control.Controller;
import Entity.Enemy;
import Screen.ScreenBattle;
import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;

public class testYann extends PortableApplication{

    ScreenPlayer sp = new ScreenPlayer();
    public Map<Integer, Boolean> keyStatus = new TreeMap<Integer, Boolean>();
    double zoom;
    Controller controller = new Controller();
    
    public static void main(String[] args) {
        new testYann();
        
    }

    testYann(){
		super( 800, 800);
	}

    @Override
    public void onInit() {
        
        
        Enemy e = new Enemy("enemi", 50, 50, "resources//lumberjack_sheet32.png", "desert");
        
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        
        sp.render(g);
        sp.sb.manage(controller);
    }
    
    @Override
    public void onDispose() {
        // TODO Auto-generated method stub
        super.onDispose();
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
