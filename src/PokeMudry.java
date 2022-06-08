import Screen.ScreenPlayer;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class PokeMudry extends PortableApplication {
    public final boolean ANDROID = false;
    public final int PLAYERS = 1;
    public static final int TIME = 10; // number of minutes for kill all enemy

    private ScreenPlayer screenPlayer = new ScreenPlayer();

    
    public static void main(String[] args) {
        new PokeMudry();
    }

    PokeMudry(){
        super(1000, 800);
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
