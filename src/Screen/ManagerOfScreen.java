package Screen;

import ch.hevs.gdx2d.lib.ScreenManager;

public class ManagerOfScreen extends ScreenManager{
    ManagerOfScreen(){
    }

    public ScreenMap getScreenMap(){
        this.activateScreen(0);
        return (ScreenMap)this.getActiveScreen();
    }

    public ScreenBattle getScreenBattle(){
        this.activateScreen(1);
        return (ScreenBattle)this.getActiveScreen();
    }

    public ScreenEnd getScreenEnd(){
        this.activateScreen(2);
        return (ScreenEnd) this.getActiveScreen();
    }
}