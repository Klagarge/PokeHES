package Control;

import com.badlogic.gdx.Input;

import Screen.ScreenPlayer;

public class Keyboard {
    public void keyDown(int keycode, ScreenPlayer sp, Controller c) {
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
        c.keyStatus.put(keycode, true);
    }
    public void onKeyUp(int keycode, ScreenPlayer sp, Controller c) {
        c.keyStatus.put(keycode, false);
    }
}
