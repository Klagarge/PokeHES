package Text;

public class Attack {
    String attack;
    int currentAttack;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    public String[] s;

    float xp;

    Attack(String attack, String answer1,String answer2,String answer3, String answer4, float xp){
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

    public String getAnswer(int i){
        return s[i];
    }

    public String getTrueAnswer(){
        return answer1;
    }

}
