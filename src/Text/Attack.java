package Text;

public class Attack {
    String attack;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    float xp;

    Attack(String attack, String answer1,String answer2,String answer3, String answer4, float xp){
        this.attack = attack;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.xp = xp;
    }

    public String toString(){
        return attack+ "  " + answer1+ "  " + answer2+ "  " + answer3+ "  " + answer4+ "  " + xp;
    }
}
