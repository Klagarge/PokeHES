package Text;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class Attack {
    String attack;
    int currentAttack;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    public String[] s;

    private int xp;

    Attack(String attack, String answer1,String answer2,String answer3, String answer4, int xp){
        this.attack = attack;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.xp = xp;

        s = new String[4];
        s[0] = answer1;
        s[1] = answer2;
        s[2] = answer3;
        s[3] = answer4;
    }

    public String toString(){
        return attack+ "  " + answer1+ "  " + answer2+ "  " + answer3+ "  " + answer4+ "  " + xp;
    }

    //return the answer with teh number i in the array s
    public String getAnswer(int i){
        return s[i];
    }

    //return the true answer to verify the answer of the player
    public String getTrueAnswer(){
        return answer1;
    }

    public int getXp() {
        return xp;
    }

}
