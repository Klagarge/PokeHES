package Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class FightData {

    private  Vector<Attack> attacks = new Vector<Attack>();
    private  File file;
    private static final String REGEX = ",";

    public static void main(String[] args) {
        FightData d = new FightData("app/src/main/java/test_map/data/donnee.csv");
        d.readFile();
        for(Attack a : d.attacks){
            System.out.println(a);
        }
    }

    public FightData(String pathname){
        file = new File(pathname);
    }

    public void readFile(){
        Attack attack;
        String line = "";
        try {
            FileReader f = new FileReader(file);
            BufferedReader bf = new BufferedReader(f); 

            line = bf.readLine(); 
            while(line != null){
                String[] a = line.split(REGEX);//change the regex if it is another
                attack = new Attack(a[0], a[1], a[2], a[3], a[4], Float.valueOf(a[5]));
                attacks.add(attack);
                line = bf.readLine();
            }

            bf.close();

          } catch (Exception e) {
            e.printStackTrace();
          }
        
    }

    public Vector<Attack> getAllAttacks(){
        return attacks;
    }

    
    public Attack getAttacks(int a){
        return attacks.get(a);
    }

}

class Attack{
    String attack;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    float xp;

    Attack(String attack, String answer1,String answer2,String answer3, String answer4, float xp){
        this.attack = attack;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.xp = xp;
    }

    public String toString(){
        return attack+ "  " + answer1+ "  " + answer2+ "  " + answer3+ "  " + answer4+ "  " + xp;
    }
}
    

