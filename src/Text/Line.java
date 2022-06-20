package Text;

/**
 * @author Rémi Heredero
 * @author Yann Sierro
 * @version 1.0.0
 */
public class Line {
    public String line;
    public boolean attackOn;

    //in the battle screen the line is played and attack on is used by the button
    Line( String line, boolean attackOn){
        this.line = line;
        this.attackOn = attackOn;
    }
    
}
