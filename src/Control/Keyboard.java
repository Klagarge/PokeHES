package Control;

import Screen.ScreenPlayer;

public class Keyboard {
    public void keyDown(int keycode, ScreenPlayer sp, Controller c) {
        c.keyStatus.put(keycode, true);
    }
    public void onKeyUp(int keycode, ScreenPlayer sp, Controller c) {
        c.keyStatus.put(keycode, false);
    }
}
