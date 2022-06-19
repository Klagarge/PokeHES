package Text;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class SpeechData {

    Vector<String> speechs = new Vector<String>();
    File file;

    public SpeechData(String name){
        file = new File("./data/battle/speech/" + name + ".txt");
    }
    
    public void readFile() {
        String line = "";

        // try to read the file of the speech of the enemy
        try {
            FileReader f = new FileReader(file, StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(f); 
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
