
import java.util.Random;




class testYann{
    public static void main(String[] args) {
        int max = 8-1;
        Random r = new Random();

        int nbre = 4;

        int[] a = new int[nbre];
        int x;
        int i=0;
        boolean same = false;

        for(int j=0; j<nbre ; j++){
            a[j] = -1;
        }

        while(i< nbre){
            x = r.nextInt(max);
            System.out.println(x);
            for(int j : a){
                if(x==j){
                    same = true;
                    break;
                }
            }
            if(same){
                same = false;
            }
            else{
                a[i] = x;
                i++;
            }
        }
        System.out.println("\n");


        for(int j : a){
            System.out.println(j);
        }
        

        
        
        
    }


}