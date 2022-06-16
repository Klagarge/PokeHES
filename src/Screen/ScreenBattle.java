package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import Control.Controller;
import Entity.Enemy;
import Entity.Player;
import Game.Battle;
import Main.PokeMudry;
import Main.Settings;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenBattle extends RenderingScreen{

	private static int EDGE = 10;
	private static int HEIGHT_DIALOG = Settings.SIDE / 3;
	private static int WIDTH_DIALOG = Settings.SIDE - 2*EDGE;

	private BitmapFont optimus40;

	private Battle b = null;

    @Override
	public void onInit() {
        //display the question
		generateFont("./Data/font/Ubuntu-Regular.ttf", 30, Color.BLACK);
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

	
	public void setBattle(Battle battle) {
		this.b = battle;
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
		if(b == null) return;
		if(b.getLine() == null) return;
		g.drawString(15, 260 ,b.getLine() , optimus40);

	}

	public void displayEnemy(Enemy e){
		// TODO affficher l'enemi
  	}

	public void displayPlayer(Player p){
		//TODO afficher le joueur                 
	}



	public void manage(Controller c, Battle battle){
		if(PokeMudry.front_montant){

			if( battle.getAttackOn() == false){
				if (c.keyStatus.get(Input.Keys.SPACE)){
					battle.action(-1);
				}
				if (c.keyStatus.get(Input.Keys.ENTER)){
					battle.screenBattleOn = battle.screenBattleOn;
				}
			}

			if(battle.getAttackOn() == true){
				if (c.keyStatus.get(Input.Keys.NUM_1)){
					battle.action(1);
				}
				else if (c.keyStatus.get(Input.Keys.NUM_2)){
					battle.action(2);
				}
				else if (c.keyStatus.get(Input.Keys.NUM_3)){
					battle.action(3);
				}
				else if (c.keyStatus.get(Input.Keys.NUM_4)){
					battle.action(4);
					
				}
			}

			//mettre le front à false jusqu'à ce que le bouton soit relâché
			PokeMudry.front_montant = false;
		}
	


	

	}
}
