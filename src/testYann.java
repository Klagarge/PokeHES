
import Screen.ScreenBattle;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;

public class testYann extends PortableApplication{

    private ScreenManager s = new ScreenManager();

    ScreenBattle b;

    public static void main(String[] args) {
        new testYann();
        
    }

    testYann(){
		super(1000, 800);
	}

    @Override
    public void onInit() {
        b = new ScreenBattle();
        s.registerScreen(b.getClass());
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        s.render(g);
        
    }

    

    @Override
    public void onKeyDown(int keycode) {
        // TODO Auto-generated method stub
        super.onKeyDown(keycode);
    }
    @Override
    public void onKeyUp(int keycode) {
        // TODO Auto-generated method stub
        super.onKeyUp(keycode);
    }
    
}
