class testYann{
    public static void main(String[] args) {


        
        String text = "aaaaaa aaaaaa\naaaaaa aaaaaa";
        String newText ="";
        String cutLine = "";
        String newLine = "";

        int cut = 6;

        int startC = 0;
        int stoppC = cut;

        String[] s = text.split("\n");

        for(String line : s){
            System.out.println(line);

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

            newText += newLine + "\n";
        
        }

        
        



       


        System.out.println(newText);

        

        
       
    }

   





}