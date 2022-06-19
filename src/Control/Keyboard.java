package Control;

import com.badlogic.gdx.Input;

import Screen.ScreenPlayer;

/**
 * Manage input from keyboard for write on controller
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class Keyboard {
    public void keyDown(int keycode, ScreenPlayer sp, Controller c) {
        if (keycode == Input.Keys.SPACE) c.keyStatus.put(Input.Keys.A, true);
        c.keyStatus.put(keycode, true);
    }
    public void onKeyUp(int keycode, ScreenPlayer sp, Controller c) {
        if (keycode == Input.Keys.SPACE) c.keyStatus.put(Input.Keys.A, false);
        c.keyStatus.put(keycode, false);
    }
}
