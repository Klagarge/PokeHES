import com.badlogic.gdx.Input;

import Screen.ScreenMap;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class testHER extends PortableApplication{

    private static ScreenMap sm;


    public static void main(String[] args) {
        sm = new ScreenMap();
        new testHER();
    }

    @Override
    public void onInit() {
        sm.init();
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        sm.graphicRender(g);
    }

    @Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);

		sm.keyStatus.put(keycode, false);
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
		sm.keyStatus.put(keycode, true);
	}
    
}
