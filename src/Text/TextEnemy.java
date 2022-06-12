package Text;

import java.util.Vector;
import java.util.Random;

public class TextEnemy {
    public FightData fightData;
    public SpeechData speechData;

    public Vector<Line> lines = new Vector<Line>();

    public int[] orderAnswer;

    public static void main(String[] args) {
        
        TextEnemy t  = new TextEnemy("enemi");

        t.generateText();

        for(Line l : t.lines) {
            System.out.println(l.line);
        }
        
       
    }
    
    public TextEnemy(String name){
        //generate the vector of fight
        fightData = new FightData(name);
        fightData.readFile();

        //generate the vector of Speechs
        speechData = new SpeechData(name);
        speechData.readFile();

    }

     int[] randomGenerate(int max_val){
        int min_val = 0;
        int x;
        int[] t = new int[max_val-1];
        Random ran = new Random();

        int i=0;

            
        while(i<t.length){
            System.out.println(i);
            t[i] = ran.nextInt(max_val) + min_val;
            for(int j : t){
                if(t[i] == j){
                    t[i] = ran.nextInt(max_val) + min_val;
                }
                else{
                    i++;
                }
            }
        }
        
        return t;        
    }


    public void generateText(){
        int i =1;
        
        //introduction line
        lines.add(new Line(speechData.getSpeechs(0), false));
        orderAnswer = randomGenerate(fightData.nbre_line);
        for(int j=0; j<4;j++){

            //generate the order of the answer
            
        //attack and answer (number on vector : 1-4) 
            lines.add(new Line(
                speechData.getSpeechs(i++) + fightData.getAttack(orderAnswer[j]).attack + " ?  ("+fightData.getAttack(orderAnswer[j]).xp+ ") " + "\n" +
                fightData.getAttack(orderAnswer[j]).answer1 + "\n" +
                fightData.getAttack(orderAnswer[j]).answer2 + "\n" + 
                fightData.getAttack(orderAnswer[j]).answer3 + "\n" + 
                fightData.getAttack(orderAnswer[j]).answer4, true));
            // TODO mélanger les attaques aléatoirement
        }
        //finish (win and death)
        lines.add(new Line(speechData.getSpeechs(5), false));
        lines.add(new Line(speechData.getSpeechs(6), false));
    }

    
}

