package Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class FightData {

    private  Vector<Attack> attacks = new Vector<Attack>();
    private  File file;
    private static final String REGEX = ",";

    

    public int nbre_line =0;

    public FightData(String name) {
        file = new File("./resources/Battle/Fight/" + name + ".csv");
        
    }

    public void readFile() {
        Attack attack;
        String line = "";

        try {
            FileReader f = new FileReader(file);
            BufferedReader bf = new BufferedReader(f); 

            //add the line in the vector attacks of attack
            line = bf.readLine(); 
            while(line != null){
                String[] a = line.split(REGEX);//change the regex if it is another
                attack = new Attack(a[0], a[1], a[2], a[3], a[4], Float.valueOf(a[5]));
                attacks.add(attack);
                line = bf.readLine();
                //add line
                nbre_line++;
            }

            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    //return the vector with all attaks of one enemi
    public Vector<Attack> getAllAttacks(){
        return attacks;
    }

    //return the vector with one attak
    public Attack getAttack(int a){
        return attacks.get(a);
    }





}

    

