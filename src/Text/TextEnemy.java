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
        int max = 8-1;
        Random r = new Random();

        int nbre = 4;

        int[] t = new int[nbre];
        int x;
        int i=0;
        boolean same = false;

        // initialize array at -1
        for(int j=0; j<nbre ; j++){
            t[j] = -1;
        }

        //assign 4 different random value between 0 and max
        while(i< nbre){
            x = r.nextInt(max);
            
            //test if the value is valid
            for(int j : t){
                if(x==j){
                    same = true;
                    break;
                }
            }

            //do again the loop
            if(same){
                same = false;
            }
            else{
                t[i] = x;
                i++;
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

