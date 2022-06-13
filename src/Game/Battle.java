package Game;

import java.util.Arrays;

import Entity.Enemy;
import Text.TextEnemy;

public class Battle {
    

    TextEnemy textEnemy;
    private int lineSpeech;
	
	public int answer;

    private int winPoint;

    private boolean screenBattleOn = true;
   

    public Battle(Enemy enemy){
        textEnemy = new TextEnemy("enemi"); // should be enemy.name
		textEnemy.generateText();

        lineSpeech = 0;
        winPoint = 0;

        System.out.println("lll : "+ getLine());
        
    }

    public void readNextLine(){
    //change line
    System.out.println(textEnemy.lines.size());
        if(lineSpeech < textEnemy.lines.size()-1){
            lineSpeech++;
        }
	}

    //check the choice answer
    public void checkAnswer(int answer){
        int attack = lineSpeech-1;
        //get number current attack random
        int currentAttack = textEnemy.getCurrentData().get(attack)[0];
        System.out.println(Arrays.toString(textEnemy.getCurrentData().get(attack)));

        //get number current answer random
        int currentAnswer = textEnemy.getCurrentData().get(attack)[answer];
        System.out.println("current answer  : " + currentAnswer);

        //get the answer of the player
        String answerPlayer = textEnemy.fightData.getAttack(currentAttack).getAnswer(currentAnswer);
        System.out.println("answer player  : " + answerPlayer);

        //get true answer
        String trueAsnwer = textEnemy.fightData.getAttack(currentAttack).getTrueAnswer();
        System.out.println("true answer : " + trueAsnwer);

        //check the choice of the player
        if(answerPlayer == trueAsnwer){
            System.out.println("it's true !!!!");
        }
        else{
            System.out.println("it's false !!!!");
        }

        readNextLine();

    }

    
    public boolean finish(){
        return false;
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

    public boolean getScreenBattleOn(){
        return screenBattleOn;
    }
     
    
}
