
import Text.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;





class testYann{
    public static void main(String[] args) {

        
        String line = "Je vais te manger tout cru, dans le creux de ma main et tu verra rien du tout.";
        String cutLine = "";
        String newLine = "";

        int cut = 25;

        int startC = 0;
        int stoppC = cut;
        

        char[] c = new char[line.length()];

        for(int i=0; i<c.length;i++){
            c[i] = line.charAt(i);
        }
        System.out.println("start\n");

        while(true){

            for(int i =stoppC; i>=startC; i--){
                if(c[i] == ' '){
                    stoppC = i;
                    break;
                }
                else if(stoppC == c.length-1){
                    break;
                }
            }

            //découper le mot 
            for(int i=startC;i<=stoppC;i++){
                cutLine += c[i];
            }

            newLine += "\n" + cutLine;
            cutLine = "";

            startC = stoppC + 1;

           
            if(c.length-1-stoppC <=0){
                break;
            }
            else if(c.length-1-stoppC <= 10){
                stoppC = c.length-1;
            }
            else{
                stoppC += cut;
            }
        }

        System.out.println(newLine);

        

        
       
    }

   





}