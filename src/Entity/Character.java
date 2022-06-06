package Entity;

public abstract class Character extends Entity{
    public Character(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    private int pv;

    public int getPv() {
        return pv;
    }

    abstract protected void removedPv(int pv);
}
