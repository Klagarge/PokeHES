package Game;

import java.util.Arrays;

import Entity.Enemy;
import Text.TextEnemy;

public class Battle {

    private Enemy e;
    public TextEnemy textEnemy;
    private int lineSpeech;
	public int answer;

    private int newXp;

    public boolean screenBattleOn = true;
   
    public Battle(Enemy e){
        if(e != null){
            textEnemy = new TextEnemy(e);  
            textEnemy.generateText();
        }
        
        lineSpeech = 0;
        newXp = 0;

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

        //get the answer of the player
        String answerPlayer = textEnemy.fightData.getAttack(currentAttack).getAnswer(currentAnswer);
        System.out.println("answer player  : " + answerPlayer);

        //get true answer
        String trueAsnwer = textEnemy.fightData.getAttack(currentAttack).getTrueAnswer();
        System.out.println("true answer : " + trueAsnwer);

        //check the choice of the player
        if(answerPlayer == trueAsnwer){
            newXp += textEnemy.fightData.getAttack(currentAttack).getXp();
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
        if(e==null) return null;
    
        return textEnemy.lines.get(lineSpeech).line;
        
    }

    
    public int getLineSpeech() {
        return lineSpeech;
    }

    public boolean getScreenBattleOn(){
        return screenBattleOn;
    }

    public int getNewXp(){
        return newXp;
    }

    public void setEnemy(Enemy e){
        this.e = e;
    }
}
