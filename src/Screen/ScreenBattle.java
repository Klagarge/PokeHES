package Screen;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;

import Control.Controller;
import Entity.Enemy;
import Entity.Player;
import Game.Battle;
import Text.Line;
import Text.TextEnemy;
import Main.PokeMudry;
import Main.Settings;

public class ScreenBattle extends RenderingScreen{

	private static int EDGE = 10;
	private static int HEIGHT_DIALOG = Settings.SIDE / 3;
	private static int WIDTH_DIALOG = Settings.SIDE - 2*EDGE;


	private BitmapFont optimus40;

	private Battle battle;

	private TextEnemy textEnemy;
	private static int lineSpeech = 0;
	private static String lineDialog = "";
	int i = 0;
	
	private int answer = 0;




    @Override
	public void onInit() {

		textEnemy = new TextEnemy("enemi");
		textEnemy.generateText();

		//new battle game
		battle = new Battle();

	
        //display the question
		generateFont("resources/font/OptimusPrinceps.ttf", 40, Color.BLACK);
		

		//initialize the first line
		readNextLine();
		System.out.println(1);
	}


	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);

		displayDialog(g);

		
		

	}

	@Override
	public void dispose() {
		optimus40.dispose();
	}

	public void generateFont(String file, int height, Color c ){
		//Generate font with the file .ttf
		FileHandle fileHandle = Gdx.files.internal(file);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fileHandle);
		parameter.size = generator.scaleForPixelHeight(height);
		parameter.color = c;
		optimus40 = generator.generateFont(parameter);
		generator.dispose();

	}

	public void displayDialog(GdxGraphics g){
		//dialog background
		g.drawFilledRectangle(Settings.SIDE/2, HEIGHT_DIALOG/2 + EDGE, WIDTH_DIALOG, HEIGHT_DIALOG, 0);

		//dialog
		g.drawString(15, 245 ,lineDialog , optimus40);

	}

	public void displayEnemy(Enemy e){
		// TODO affficher l'enemi
  	}

	public void displayPlayer(Player p){
		//TODO afficher le joueur                 
	}

	public void readNextLine(){
		//display the speech and change line
		lineDialog = textEnemy.lines.get(lineSpeech).line;
		lineSpeech++;

	}


	public void manage(Controller c){
		if(PokeMudry.front_montant){
			if (c.keyStatus.get(Input.Keys.SPACE)){
				if(textEnemy.lines.get(lineSpeech).attackOn == false){
					readNextLine();
			}
			else if (c.keyStatus.get(Input.Keys.NUM_1)){
				if(textEnemy.lines.get(lineSpeech).attackOn == true){
					readNextLine();
					answer = 1;
				}
			}
			else if (c.keyStatus.get(Input.Keys.NUM_2)){
				if(textEnemy.lines.get(lineSpeech).attackOn == true){
					readNextLine();
					answer = 2;
				}
			}
			else if (c.keyStatus.get(Input.Keys.NUM_3)){
				if(textEnemy.lines.get(lineSpeech).attackOn == true){
					readNextLine();
					answer = 3;
				}
			}
			else if (c.keyStatus.get(Input.Keys.NUM_4)){
				if(textEnemy.lines.get(lineSpeech).attackOn == true){
					readNextLine();
					answer = 4;
				}
			}

			//mettre le front à false jusqu'à ce que le bouton soit relâché
			PokeMudry.front_montant = false;
		}


	}

	}
}
