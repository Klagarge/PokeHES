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

	private Enemy enemy;


    @Override
	public void onInit() {
		//new battle game
		battle = new Battle(enemy);
		
        //display the question
		generateFont("resources/font/OptimusPrinceps.ttf", 40, Color.BLACK);

	}


	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);

		displayDialog(g);

		System.out.println("render: " + battle.getLineSpeech());
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
		g.drawString(15, 245 ,battle.getLine() , optimus40);



	}

	public void setEnemy(Enemy enemy){
		this.enemy = enemy;
	}

	public void displayEnemy(Enemy e){
		// TODO affficher l'enemi
  	}

	public void displayPlayer(Player p){
		//TODO afficher le joueur                 
	}

	public void manage(Controller c){
		if(PokeMudry.front_montant){
 	     	System.out.println("manage: " + battle.getLineSpeech());


			if( battle.getAttackOn() == false){
				if (c.keyStatus.get(Input.Keys.SPACE)){
					System.out.println("in");
					battle.readNextLine();
				}
			}


			if(battle.getAttackOn() == true){
				if (c.keyStatus.get(Input.Keys.NUM_1)){
					System.out.println("je sui dansakjshfljkahflkasjhfdlkajshflkajshfdlkasjdhfalsdkjfh123412341234");
					battle.readNextLine();
					battle.answer = 1;
				}
				else if (c.keyStatus.get(Input.Keys.NUM_2)){
					battle.readNextLine();
					battle.answer = 2;
				}
				else if (c.keyStatus.get(Input.Keys.NUM_3)){
					battle.readNextLine();
					battle.answer = 3;
				}
				else if (c.keyStatus.get(Input.Keys.NUM_4)){
					battle.readNextLine();
					battle.answer = 4;
					
				}
			}

			//mettre le front à false jusqu'à ce que le bouton soit relâché
			PokeMudry.front_montant = false;
		}


	

	}
}
