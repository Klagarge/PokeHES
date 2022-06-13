package Text;

import java.util.Vector;
import java.util.Arrays;
import java.util.Random;

public class TextEnemy {
    public FightData fightData;
    public SpeechData speechData;

    public Vector<Line> lines = new Vector<Line>();

    private int[] orderAttack;
    private int[] orderAnswer;

    private Vector<int[]> currentData;

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

        //save random data (attack and ansver) : attack, answer 1, answer 2 answer 3, answer 4
        currentData = new Vector<int[]>();

    }

    public static int[] randomGenerate( int min, int max, int nbreRandom){
        //create an array with all the number I need
        int[] a = new int[max-min+1];
        int k = min;
        for(int i=0;k<=max;i++){
            a[i] = k;
            k++;
        }

        //create a new array with the numbers I want
        int[] b = new int[nbreRandom];

        // Creating object for Random class
        Random rd = new Random();
         
        // Starting from the last element and swapping one by one.
        for (int i = a.length-1; i > 0; i--) {
             
            // Pick a random index from 0 to i
            int j = rd.nextInt(i+1);
             
            // Swap array[i] with the element at random index
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        //add the numbers I want
        for(int i=0;i<nbreRandom;i++){
            b[i] = a[i];
        }
        return b;
    }


    public void generateText(){
        int i =1;
        

        //introduction line
        lines.add(new Line(speechData.getSpeechs(0), false));
        orderAttack = randomGenerate(0, fightData.nbre_line-1, 4);
        for(int j=0; j<4;j++){
            int[] currentRandom = new int[5];
            currentRandom[0] = orderAttack[j];

            //generate the order of the answer
            orderAnswer = randomGenerate(0, 3, 4);
            System.out.println("\n attaque " + j + " : " + Arrays.toString(orderAnswer) + "\n");

            //save the order of answer and attack
            for(int k=1;k<5;k++){
                currentRandom[k] = orderAnswer[k-1];
            }

            //attack and answer (number on vector : 1-4) 
            lines.add(new Line(
                speechData.getSpeechs(i++) + fightData.getAttack(orderAttack[j]).attack + " ?  ("+fightData.getAttack(orderAttack[j]).xp+ ") " + "\n" +
                "1. " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[0]) + "\n" +
                "2. " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[1]) + "\n" + 
                "3. " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[2]) + "\n" + 
                "4. " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[3]), true));

            
            currentData.add(currentRandom);
        }
        
        for(int[] a : currentData){
            System.out.println(Arrays.toString(a));
        }

        //finish (win and death)
        lines.add(new Line(speechData.getSpeechs(5), false));
        lines.add(new Line(speechData.getSpeechs(6), false));
    }

    public Vector<int[]> getCurrentData() {
        return currentData;
    }

    
}

