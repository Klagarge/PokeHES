package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import Control.Controller;
import Entity.Player;
import Game.Battle;
import Main.PokeMudry;
import Main.Settings;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenEnd extends RenderingScreen{

    private String textEnd = null;
    private BitmapFont unbuntuRegularWhite;


    @Override
    public void onInit() {
        unbuntuRegularWhite = generateFont("./Data/font/Ubuntu-Regular.ttf", 30, Color.WHITE);
        
    }

    @Override
    protected void onGraphicRender(GdxGraphics g) {
        g.clear(Color.BLACK);
        if(textEnd != null) g.drawStringCentered(Settings.SIDE/2, textEnd, unbuntuRegularWhite);
        
    }

    @Override
	public void dispose() {
		unbuntuRegularWhite.dispose();
	}

    public void setText(Player p){
        if(p.getXp() >= p.getXpMax()){
            textEnd = "Bravo, tu as réussi ton année avec " + p.getXp()/100.0 + " crédits en " + (Settings.TIME*60-p.getPv()) + " secondes.\n\nMais, seras-tu près pour le prochain....";
        }
        else{
            textEnd = "L'année est terminée et tu as obtenu " + p.getXp()/100.0 + " crédits.\n\nA l'année prochaine...";
        }
    }


    public BitmapFont generateFont(String file, int height, Color c ){
		//Generate font with the file .ttf
		BitmapFont font;
		FileHandle fileHandle = Gdx.files.internal(file);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fileHandle);
		parameter.size = generator.scaleForPixelHeight(height);
		parameter.color = c;
		font = generator.generateFont(parameter);
		generator.dispose();
		return font;

	}
    
}
