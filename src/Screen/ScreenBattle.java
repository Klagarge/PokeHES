package Screen;

import Main.Settings;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import Control.Controller;
import Entity.Enemy;

public class ScreenBattle extends RenderingScreen{

	private static int EDGE = 10;
	private static int HEIGHT_DIALOG = Settings.SIDE / 3;
	private static int WIDTH_DIALOG = Settings.SIDE - 2*EDGE;
	
	private boolean attackOn;
	private int numAttack =0;


	private BitmapFont optimus40;

    @Override
	public void onInit() {

        //display the question
		generateFont("resources//font//OptimusPrinceps.ttf", optimus40, 20, Color.BLACK);

	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);
		g.drawStringCentered(g.getScreenHeight()/2, "attack", optimus40);
		g.drawFilledRectangle(Settings.SIDE/2, HEIGHT_DIALOG/2 + EDGE, WIDTH_DIALOG, HEIGHT_DIALOG, 0);

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

	public void displayEnemy(Enemy e){
		// stock his speech

		//display the person

	}

	public void readNextLine(){
		//display the speech
		
	}

	public void manage(Controller c){
		if (c.keyStatus.get(Input.Keys.SPACE)){
			readNextLine();
		}
	}
    



}
