package Text;

import java.util.Vector;

public class TextEnemy {
    public FightData fightData;
    public SpeechData speechData;

    Vector<Line> line = new Vector<Line>();
    
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
        line.add(new Line(speechData.getSpeechs(i++), false));

        for(int j=0; i<4;i++){
        //attack and answer (number on vector : 1-4) 
            line.add(new Line(
            speechData.getSpeechs(i++) + fightData.getAttack(j).attack + "?  ("+fightData.getAttack(j).xp+ ") " + "\n" +
            fightData.getAttack(j).answer1 + "\n" +
            fightData.getAttack(j).answer2 + "\n" + 
            fightData.getAttack(j).answer3 + "\n" + 
            fightData.getAttack(j).answer4, true ));
            // TODO mélanger les attaques aléatoirement
        }
        //finish (win and death)
        line.add(new Line(speechData.getSpeechs(i++), false));
        line.add(new Line(speechData.getSpeechs(i++), false));
    }

    
}
class Line {
    String line;
    boolean attackOn;

    Line( String line, boolean attackOn){
        this.line = line;
        this.attackOn = attackOn;
    }
}
