package Game;

import Entity.Enemy;
import Text.TextEnemy;

public class Battle {

    private Enemy enemy;

    TextEnemy textEnemy;
    public int lineSpeech = 0;
	
	public int answer = 0;
    private boolean c;

    public Battle(Enemy enemy){
        this.enemy = enemy;
        textEnemy = new TextEnemy("enemi"); // should be enemy.name
		textEnemy.generateText();

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
     
    
}
