package Text;

import Entity.Enemy;
import java.util.Vector;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Random;

public class TextEnemy {
    private static final int CUT = 55;
    public FightData fightData;
    public SpeechData speechData;

    public Vector<Line> lines = new Vector<Line>();

    private int[] orderAttack;
    private int[] orderAnswer;

    private Vector<int[]> currentData;
    
    public TextEnemy(Enemy e){
        //generate the vector of fight
        fightData = new FightData(e.getBranch());
        fightData.readFile();

        //generate the vector of Speechs
        speechData = new SpeechData(e.getName());
        speechData.readFile();

        //save random data (attack and answer) : attack, answer 1, answer 2 answer 3, answer 4
        currentData = new Vector<int[]>();

    }

    public static int[] randomGenerate( int min, int max, int nbrRandom){
        //create an array with all the number I need
        int[] a = new int[max-min+1];
        int k = min;
        for(int i=0;k<=max;i++){
            a[i] = k;
            k++;
        }

        //create a new array with the numbers I want
        int[] b = new int[nbrRandom];

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
        for(int i=0;i<nbrRandom;i++){
            b[i] = a[i];
        }
        return b;
    }


    //generate the text who is displays in battle screen
    public void generateText(int cursor){
        int i =1;
        
        //introduction line
        String introduction = formatLine(speechData.getSpeechs(0), CUT);
        lines.add(new Line(introduction, false));

        orderAttack = randomGenerate(0, fightData.nbr_line-1, 4);

        for(int j=0; j<4;j++){
            int[] currentRandom = new int[5];
            currentRandom[0] = orderAttack[j];

            //generate a random array to determine the order of the answer
            orderAnswer = randomGenerate(0, 3, 4);

            //save the order of answer and attack
            for(int k=1;k<5;k++){
                currentRandom[k] = orderAnswer[k-1];
            }

            //Format the line
            String[] row = new String[4];
            row[0] = row[1] = row[2] = row[3] = "";
            row[cursor] = "->";
            String attack = formatLine(speechData.getSpeechs(i++) + fightData.getAttack(orderAttack[j]).attack + "  ("+fightData.getAttack(orderAttack[j]).getXp()+ ") ", CUT);
            String answer1 = formatLine(row[0] + " " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[0]) , CUT);
            String answer2 = formatLine(row[1] + " " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[1]) , CUT);
            String answer3 = formatLine(row[2] + " " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[2]) , CUT);
            String answer4 = formatLine(row[3] + " " + fightData.getAttack(orderAttack[j]).getAnswer(orderAnswer[3]) , CUT);

            //attack and answer (number on vector : 1-4)
            lines.add(new Line(attack + "\n" +answer1 + "\n" + answer2 + "\n" + answer3 + "\n" + answer4, true));

            //save the order of the answer
            currentData.add(currentRandom);
        }
        
        //display answer
        for(int[] a : currentData){
            System.out.println(Arrays.toString(a));
        }
        

        //finish (win and death)
        String dead = formatLine(speechData.getSpeechs(5),CUT);
        String alive = formatLine(speechData.getSpeechs(6), CUT);
        lines.add(new Line(dead, false));
        lines.add(new Line(alive, false));
    }

    //get the saved order of the attacks and answer
    public Vector<int[]> getCurrentData() {
        return currentData;
    }

    //format a String with a specific length of char
    public String formatLine(String line, int cut){

        String cutLine = "";
        String newLine = "";

        int startC = 0;
        int stopC = cut;
        
        //check if the line is shorter than the character limit 
        if(cut>line.length()-1){
            newLine  =line;
        }
        else{
            
            //create a array with the line
            char[] c = new char[line.length()];
            for(int i=0; i<c.length;i++){
                c[i] = line.charAt(i);
            }

            while(true){
                for(int i =stopC; i>=startC; i--){

                    if(c[i] == ' '){
                        stopC = i;
                        break;
                    }
                    else if(stopC == c.length-1){
                        break;
                    }
                }

                //découper le mot 
                for(int i=startC;i<=stopC;i++){

                    cutLine += c[i];
                }

                //rebuild the line with the line breaks
                newLine +=  cutLine+"\n";
                cutLine = "";

                startC = stopC + 1;

                
                if(c.length-1-stopC <=0){

                    break;
                }
                else if(c.length-1-stopC <= cut){
                    stopC = c.length-1;
                }
                else{
                    stopC += cut;
                }
            }
        }
        return newLine;
    }

    
}

