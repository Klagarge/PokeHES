package Game;

import Entity.Enemy;
import Text.TextEnemy;

public class Battle {

    private Enemy enemy;

    TextEnemy textEnemy;
    private int lineSpeech;
	
	public int answer;
   

    public Battle(Enemy enemy){
        this.enemy = enemy;
        textEnemy = new TextEnemy("enemi"); // should be enemy.name
		textEnemy.generateText();

        lineSpeech = 0;
        answer = 0;

        //initialize the first line
        System.out.println("lll : "+ getLine());
        
    }

    public void readNextLine(){
    //change line
		lineSpeech++;

	}

    public boolean getAttackOn(){
        return textEnemy.lines.get(lineSpeech).attackOn;
    }

    public String getLine(){
        return textEnemy.lines.get(lineSpeech).line;
    }

    public int getLineSpeech() {
        return lineSpeech;
    }
     
    
}
