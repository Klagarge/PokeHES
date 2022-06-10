package Text;

import java.util.Vector;

public class TextEnemy {
    public FightData fightData;
    public SpeechData speechData;

    public Vector<Line> lines = new Vector<Line>();

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

    public void generateText(){
        int i =1;
        //introduction line
        lines.add(new Line(speechData.getSpeechs(0), false));
        for(int j=0; j<4;j++){
        //attack and answer (number on vector : 1-4) 
            lines.add(new Line(
                speechData.getSpeechs(i++) + fightData.getAttack(j).attack + "?  ("+fightData.getAttack(j).xp+ ") " + "\n" +
                fightData.getAttack(j).answer1 + "\n" +
                fightData.getAttack(j).answer2 + "\n" + 
                fightData.getAttack(j).answer3 + "\n" + 
                fightData.getAttack(j).answer4, true));
            // TODO mélanger les attaques aléatoirement
        }
        //finish (win and death)
        lines.add(new Line(speechData.getSpeechs(5), false));
        lines.add(new Line(speechData.getSpeechs(6), false));
    }

    
}

