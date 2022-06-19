package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import Entity.Player;
import Main.Settings;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenEnd extends RenderingScreen{

    private String textEnd = null;
    private BitmapFont unbuntuRegularWhite;

    @Override
    public void onInit() {
        //generate a new font
        unbuntuRegularWhite = generateFont("font/Ubuntu-Regular.ttf", 30, Color.WHITE);
        
    }

    @Override
    protected void onGraphicRender(GdxGraphics g) {
        //color the background in black
        g.clear(Color.BLACK);

        //display the text
        if(textEnd != null) g.drawStringCentered(Settings.SIDE/2, textEnd, unbuntuRegularWhite);
        
    }

    @Override
	public void dispose() {
		unbuntuRegularWhite.dispose();
	}

    //set a different text if the player win or loose
    public void setText(Player p){
        if(p.getXp() >= p.getXpMax()){
            textEnd = "Bravo, tu as réussi ton année avec " + p.getXp()/100.0 + " crédits en " + (Settings.TIME*60-p.getPv()) + " secondes.\n\nMais, seras-tu près pour la prochaine....";
        }
        else{
            textEnd = "L'année est terminée et tu as obtenu " + p.getXp()/100.0 + " crédits.\n\nA l'année prochaine...";
        }
    }

    //create a font with a file .ttf ,  a height and a color
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
