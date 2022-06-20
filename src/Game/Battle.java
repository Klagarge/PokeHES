package Game;

import Entity.Enemy;
import Entity.Player;
import Text.TextEnemy;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.2
 */
public class Battle {

    public Enemy e;
    public Player player;
    
    public TextEnemy textEnemy;
    private int lineSpeech;


    public int newXp;
    public int pvEnemy;
    public int xpPlayer;

    public boolean screenBattleOn = true;
    public int cursor = 0;

    public Battle(Enemy e){
        if(e != null){
            textEnemy = new TextEnemy(e);  
            textEnemy.generateText(cursor);
        }
        
        pvEnemy = e.getPv();
        lineSpeech = 0;
        newXp = 0;
    }

    public void readNextLine(){
    //change line
    //System.out.println(textEnemy.lines.size());
        if(lineSpeech < 5){
            lineSpeech++;
        }
	}

    public void action(int answer){
       //the player is at the last question, the finish text must be displayed
        if(getLineSpeech() == 4){
            checkAnswer(answer);
            finishSpeech();
        }
        //the player is at the finish text and he must quit the battle
        else if( getLineSpeech() == 5  || getLineSpeech() == 6){
            finish();
        }
        //the player answer the question and it is check and if the enemy is killed
        else if(0 < getLineSpeech() && getLineSpeech() < 4){
            checkAnswer(answer);

            if(pvEnemy <= 0){
                finishSpeech();
            }
        }
        //default case : increase speech to display the new line
        else{
        readNextLine();
        }
        
        textEnemy.randomAnswer();
    }

    //check the choice answer
    public void checkAnswer(int answer){
        int attack = lineSpeech-1;
        //get number current attack random
        int currentAttack = textEnemy.getCurrentData().get(attack)[0];

        //get number current answer random
        int currentAnswer = textEnemy.getCurrentData().get(attack)[answer];

        //get the answer of the player
        String answerPlayer = textEnemy.fightData.getAttack(currentAttack).getAnswer(currentAnswer);

        //get true answer
        String trueAnswer = textEnemy.fightData.getAttack(currentAttack).getTrueAnswer();

        //check the choice of the player
        if(answerPlayer == trueAnswer){
            newXp += textEnemy.fightData.getAttack(currentAttack).getXp();
            updatePlayerEnemy(textEnemy.fightData.getAttack(currentAttack).getXp());
            System.out.println("it's true !!!!");

        }
        else{
            System.out.println("it's false !!!!");
        }
        readNextLine();
    }

    public void updatePlayerEnemy(int xp){
        //add xp for the player
        xpPlayer += xp;
        if(xpPlayer>6000) xpPlayer = 6000;
        //remove pv enemy
        pvEnemy -= xp;
        if(pvEnemy<0) pvEnemy =0;
    }

    public void finishSpeech(){
        if(pvEnemy>0){
            //alive (speechLine = 6)
            lineSpeech = 6;
            System.out.println("enemy alive");
        }
        else{
            //dead (speechLine = 5)
            lineSpeech = 5;
            System.out.println("enemy dead");
        }
    }

    
    public void finish(){
        screenBattleOn = false;
        e.recoveredTime = 0;
    }

    public boolean getAttackOn(){
        return textEnemy.lines.get(lineSpeech).attackOn;
    }

    public String getLine(){
        if(e==null) return null;
        return textEnemy.lines.get(lineSpeech).line;
    }

    //get the line for the dialog
    public int getLineSpeech() {
        return lineSpeech;
    }

    //return true if the screen is active
    public boolean getScreenBattleOn(){
        return screenBattleOn;
    }

    //get the total xp win in the battle
    public int getNewXp(){
        return newXp;
    }

    public void setXpPlayer(int xp){
        xpPlayer = xp;
    }

    //set enemy
    public void setEnemy(Enemy e){
        this.e = e;
    }
    public void setPlayer(Player p){
        this.player = p;
    }

    public void updateText(){
        if(e != null) textEnemy.generateText(cursor);
    }
}
