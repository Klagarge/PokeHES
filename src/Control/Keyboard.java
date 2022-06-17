package Control;

import Screen.ScreenPlayer;

/**
 * Manage input from keyboard for write on controller
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class Keyboard {
    public void keyDown(int keycode, ScreenPlayer sp, Controller c) {
        c.keyStatus.put(keycode, true);
    }
    public void onKeyUp(int keycode, ScreenPlayer sp, Controller c) {
        c.keyStatus.put(keycode, false);
    }
}
