package Main;

import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeMudry extends PortableApplication {
    
    private ScreenPlayer screenPlayer = new ScreenPlayer();

    public static void main(String[] args) {
        new PokeMudry();
    }

    PokeMudry(){
        super(Settings.SIDE, Settings.SIDE);
    }
    

    @Override
    public void onInit() {
        screenPlayer.init();
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        screenPlayer.render(g);
    }


    //key gestion
    @Override
    public void onKeyDown(int keycode) {
        screenPlayer.screenManager.getActiveScreen().onKeyDown(keycode);
        super.onKeyDown(keycode);
    }
    @Override
    public void onKeyUp(int keycode) {
        screenPlayer.screenManager.getActiveScreen().onKeyUp(keycode);
        super.onKeyUp(keycode);
    }
}
