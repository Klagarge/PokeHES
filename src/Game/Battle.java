package Game;

import Entity.Enemy;
import Entity.Player;
import Text.TextEnemy;

public class Battle {

    public Enemy e;
    public Player player;
    
    public TextEnemy textEnemy;
    private int lineSpeech;


    public int newXp;
    public int pvEnemy;
    public int xpPlayer;

    public boolean screenBattleOn = true;
   
    public Battle(Enemy e){
        if(e != null){
            textEnemy = new TextEnemy(e);  
            textEnemy.generateText();
        }
        pvEnemy = e.getPv();
        lineSpeech = 0;
        newXp = 0;
    }

    public void readNextLine(){
    //change line
    System.out.println(textEnemy.lines.size());
        if(lineSpeech < 5){
            lineSpeech++;
        }

	}

    public void action(int answer){
        System.out.println("pv enemy : " +pvEnemy);
        System.out.println("xp player : " + xpPlayer);
        System.out.println("xp win " + newXp);
        
        if(getLineSpeech() == 4){
            FinishSpeech();
        }
        else if( getLineSpeech() == 5  || getLineSpeech() == 6){
            finish();
        }
        else if(0 < getLineSpeech() && getLineSpeech() < 4){
            checkAnswer(answer);
        }
        else{
        readNextLine();
        }
        
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
        String trueAsnwer = textEnemy.fightData.getAttack(currentAttack).getTrueAnswer();

        //check the choice of the player
        if(answerPlayer == trueAsnwer){
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
        //remove pv enemy
        pvEnemy -= xp;
        
    }

    public void FinishSpeech(){
        if(pvEnemy>0){
            //alive (speechline = 6)
            lineSpeech += 2;
            System.out.println("enemy alive");
        }
        else{
            //dead (speechline = 5)
            lineSpeech += 1;
            System.out.println("enemy dead");
        }
    }
    
    public void finish(){
        screenBattleOn = false;
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
}
