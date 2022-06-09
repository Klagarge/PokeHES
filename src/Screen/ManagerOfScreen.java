package Screen;

import ch.hevs.gdx2d.lib.ScreenManager;

public class ManagerOfScreen extends ScreenManager{
    ManagerOfScreen(){
        
    }

    ScreenMap getScreenMap(){
        this.activateScreen(0);
        return (ScreenMap)this.getActiveScreen();
    }

    ScreenBattle getScreenBattle(){
        this.activateScreen(1);
        return (ScreenBattle)this.getActiveScreen();
    }
}