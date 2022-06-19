package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import Control.Controller;
import Game.Battle;
import Main.PokeHES;
import Main.Settings;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class ScreenBattle extends RenderingScreen{

	private static int EDGE = 10;
	private static int HEIGHT_DIALOG = Settings.SIDE / 3;
	private static int WIDTH_DIALOG = Settings.SIDE - 2*EDGE;

	private BitmapFont ubuntuRegularBlack;
	private BitmapFont ubuntuRegularWhite;
	private BitmapImage enemyImg;
	private BitmapImage playerImg;

	private Battle b = null;


    @Override
	public void onInit() {
        //display the question
		ubuntuRegularBlack = generateFont("font/Ubuntu-Regular.ttf", 30, Color.BLACK);
		ubuntuRegularWhite = generateFont("font/Ubuntu-Regular.ttf", 45, Color.WHITE);
	}


	@Override
	public void onGraphicRender(GdxGraphics g) {
		//color the background in black
		g.clear(Color.BLACK);
		//display the dialog, the enemy and the player
		displayDialog(g);
		displayEnemy(g);
		displayPlayer(g);
	}

	@Override
	public void dispose() {
		ubuntuRegularBlack.dispose();
		ubuntuRegularWhite.dispose();
	}

	//set the images for the player and the enemy
	public void setImg(){
		enemyImg = new BitmapImage(b.e.getImgBattle()); //width : 192, height : 240
		playerImg = new BitmapImage(b.player.getImgBattle()); //width : 192, height : 240
	}

	//set the battle
	public void setBattle(Battle battle) {
		this.b = battle;
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

	public void displayDialog(GdxGraphics g){
		//draw the background
		g.drawFilledRectangle(Settings.SIDE/2, HEIGHT_DIALOG/2 + EDGE, WIDTH_DIALOG, HEIGHT_DIALOG, 0);
		//draw the dialog
		if(b == null) return;
		if(b.getLine() == null) return;
		g.drawString(15, 260, b.getLine(), ubuntuRegularBlack);
	}

	public void displayEnemy(GdxGraphics g){
		//draw image
		g.drawPicture(Settings.SIDE - (192/2), Settings.SIDE-240/2, enemyImg);
		//draw pv
		g.drawString(300, Settings.SIDE - 15 , "PV : " + b.pvEnemy + " / " + b.e.getPvInit(), ubuntuRegularWhite);

  	}

	public void displayPlayer(GdxGraphics g){
		//draw image
		g.drawPicture((192/2), HEIGHT_DIALOG + 10 + 240/2, playerImg);
		//draw pv
		g.drawString(255, HEIGHT_DIALOG + 100 , "XP : " + b.xpPlayer + " / " + b.player.getXpMax() + "\nPV : " + b.player.getPv() + " / " + Settings.TIME*60, ubuntuRegularWhite);              

	}

	public void manage(Controller c, Battle battle){
		//add a rising front to have one impulsion
		if(PokeHES.risingFront){
			//the enemy is attacking
			if( battle.getAttackOn() == false){
				if (c.keyStatus.get(Input.Keys.SPACE) || c.keyStatus.get(Input.Keys.A)){
					battle.action(-1);
				}
			}

			if (c.keyStatus.get(Input.Keys.DOWN)){
				b.cursor++;
			}
			else if (c.keyStatus.get(Input.Keys.UP)){
				b.cursor--;
			}

			if (b.cursor>3)b.cursor =0;
			if (b.cursor<0)b.cursor =3;

			System.out.println("" + b.cursor);

			//the enemy is speaking
			if(battle.getAttackOn() == true){
				if (c.keyStatus.get(Input.Keys.NUM_1) || c.keyStatus.get(Input.Keys.A) && b.cursor == 0){
					battle.action(1);
				}
				else if (c.keyStatus.get(Input.Keys.NUM_2) || c.keyStatus.get(Input.Keys.A) && b.cursor == 1){
					battle.action(2);
				}
				else if (c.keyStatus.get(Input.Keys.NUM_3) || c.keyStatus.get(Input.Keys.A) && b.cursor == 2){
					battle.action(3);
				}
				else if (c.keyStatus.get(Input.Keys.NUM_4) || c.keyStatus.get(Input.Keys.A) && b.cursor == 3){
					battle.action(4);
				}
			}
			//mettre le front à false jusqu'à ce que le bouton soit relâché
			PokeHES.risingFront = false;
		}
	}
}
