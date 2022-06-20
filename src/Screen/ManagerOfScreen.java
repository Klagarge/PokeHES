package Screen;

import ch.hevs.gdx2d.lib.ScreenManager;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class ManagerOfScreen extends ScreenManager{
    ManagerOfScreen(){
    }

    /**
     * Get the current screen map
     * @return the current screen map
     */
    public ScreenMap getScreenMap(){
        this.activateScreen(0);
        return (ScreenMap)this.getActiveScreen();
    }

    /**
     * Get the current screen battle
     * @return the current screen battle
     */
    public ScreenBattle getScreenBattle(){
        this.activateScreen(1);
        return (ScreenBattle)this.getActiveScreen();
    }

    /**
     * Get the current end screen
     * @return the current end screen
     */
    public ScreenEnd getScreenEnd(){
        this.activateScreen(2);
        return (ScreenEnd) this.getActiveScreen();
    }
}