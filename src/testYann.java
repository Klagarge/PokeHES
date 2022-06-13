
import Text.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;





class testYann{
    public static void main(String[] args) {

        FightData t = new FightData("enemi");
        t.readFile();

        System.out.println(t.getAttack(1).getAnswer(0).toString());
       
    }

   





}