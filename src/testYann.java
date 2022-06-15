
import Text.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;





class testYann{
    public static void main(String[] args) {

        System.out.println(111);
        
        String line = "   Je vais te manger tout cru asdnkajsdh asl      ";
        String cutLine = "";
        String newLine = "";

        int cut = 12;

        int startC = 0;
        int stoppC = cut;

        if(cut>line.length()-1){
            newLine  =line;
        }
        else{
            
            char[] c = new char[line.length()];

            for(int i=0; i<c.length;i++){
                c[i] = line.charAt(i);
            }



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

                newLine +=  cutLine+"\n";
                cutLine = "";

                startC = stoppC + 1;

                
                if(c.length-1-stoppC <=0){
                    break;
                }
                else if(c.length-1-stoppC <= cut){
                    stoppC = c.length-1;
                }
                else{
                    stoppC += cut;
                }
            }
        }
        



       


        System.out.println(newLine);

        

        
       
    }

   





}