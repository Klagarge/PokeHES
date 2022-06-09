package Control;

import java.util.Map;
import java.util.TreeMap;

import com.badlogic.gdx.Input;

public class Controller {
    public Map<Integer, Boolean> keyStatus = new TreeMap<Integer, Boolean>();

    public void init(){
        // init keys status
		keyStatus.put(Input.Keys.UP, false);
		keyStatus.put(Input.Keys.DOWN, false);
		keyStatus.put(Input.Keys.LEFT, false);
		keyStatus.put(Input.Keys.RIGHT, false);
        keyStatus.put(Input.Keys.NUM_1, false);
        keyStatus.put(Input.Keys.NUM_2, false);
        keyStatus.put(Input.Keys.NUM_3, false);
        keyStatus.put(Input.Keys.NUM_4, false);
        keyStatus.put(Input.Keys.SPACE, false);
    }

}
