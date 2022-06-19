package Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class SpeechData {

    Vector<String> speechs = new Vector<String>();
    private  InputStream stream;

    public SpeechData(String name){
        stream = FightData.class.getResourceAsStream("/battle/speech/" + name + ".txt");
    }
    
    public void readFile() {
        String line = "";

        // try to read the file of the speech of the enemy
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            //read and add a new line in the vector speechs
            line = bf.readLine(); 
            while(line != null){
                speechs.add(line);
                line = bf.readLine();
            }

            bf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    //return the element i an teh vector speechs
    public String getSpeechs(int i) {
        return speechs.elementAt(i);
    }
}
