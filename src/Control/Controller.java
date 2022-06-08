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
    }

}
