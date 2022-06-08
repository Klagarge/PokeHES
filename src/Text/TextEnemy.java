package Text;

import java.util.Vector;

public class TextEnemy {
    public FightData fightData;
    public SpeechData speechData;
    public boolean attackOn = false;

    Vector<String> line = new Vector<String>();
    
    public TextEnemy(String name){
        //generate the vector of fight
        fightData = new FightData(name);
        fightData.readFile();

        //generate the vector of Speechs
        speechData = new SpeechData(name);
        speechData.readFile();

    }

    public void generateText(){
        int i =0;
        //introduction line
        line.add(speechData.getSpeechs(i++));

        for(int j=0; i<4;i++){
        //attack and answer (number on vector : 1-4)
            line.add(
            speechData.getSpeechs(i++) + fightData.getAttack(j).attack + "?  ("+fightData.getAttack(j).xp+ ") " + "\n" +
            fightData.getAttack(j).answer1 + "\n" +
            fightData.getAttack(j).answer2 + "\n" + 
            fightData.getAttack(j).answer3 + "\n" + 
            fightData.getAttack(j).answer4);
        }
        //finish (win and death)

    }

    
}
