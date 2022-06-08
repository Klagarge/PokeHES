package Screen;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class ScreenBattle extends RenderingScreen{

	private BitmapFont optimus40;

    @Override
	public void onInit() {

        //display the question
		generateFont("resources//font//OptimusPrinceps.ttf", optimus40, 100, Color.WHITE);

	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.GREEN);
		g.drawStringCentered(g.getScreenHeight()/2, "question", optimus40);

	}

	@Override
	public void dispose() {
		optimus40.dispose();

	}

	public void generateFont(String file, BitmapFont bitmapFont, int height, Color c ){
		FileHandle fileHandle = Gdx.files.internal(file);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fileHandle);
		parameter.size = generator.scaleForPixelHeight(height);
		parameter.color = c;
		optimus40 = generator.generateFont(parameter);
		generator.dispose();

	}
    



}
