package Text;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SpeechData {

    Vector<String> speechs = new Vector<String>();
    File file;
    

    public SpeechData(String name){
        file = new File("resources//fight//" + name + ".csv");
    }
    
    public void readFile() {
        String line = "";
        try {
            FileReader f = new FileReader(file);
            BufferedReader bf = new BufferedReader(f); 

            line = bf.readLine(); 
            while(line != null){
                
                Speechs.add(line);

                line = bf.readLine();
            }

            bf.close();

          } catch (Exception e) {
            e.printStackTrace();
          }
        
    }
    public String getSpeechs(int i) {
        return speechs.elementAt(i);
    }
}
