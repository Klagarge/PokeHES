package Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class FightData {

    private  Vector<Attack> attacks = new Vector<Attack>();
    private  InputStream stream;
    private static String regex = ";";

    public int nbr_line =0;

    public FightData(String branch) {
        stream = FightData.class.getResourceAsStream("/battle/fight/" + branch + ".csv");
    }

    public void readFile() {
        Attack attack;
        String line = "";

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            //add the line in the vector attacks of attack
            line = bf.readLine(); 
            while(line != null){
                String[] a = line.split(regex);//change the regex if it is another
                attack = new Attack(a[0], a[1], a[2], a[3], a[4], Integer.valueOf(a[5]));
                attacks.add(attack);
                line = bf.readLine();
                //add line
                nbr_line++;
            }

            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    //return the vector with all attacks of one enemy
    public Vector<Attack> getAllAttacks(){
        return attacks;
    }

    //return the vector with one attack
    public Attack getAttack(int a){
        return attacks.get(a);
    }





}

    

