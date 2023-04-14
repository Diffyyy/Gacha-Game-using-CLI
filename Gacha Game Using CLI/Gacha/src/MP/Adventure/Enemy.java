package MP.Adventure;

public class Enemy {
    private String name;
    private int power;

    //Methods
    //Power
    public int getPower() {return this.power;}

    //Constructor
    public Enemy (String name, int power) {
        this.name=name;
        this.power=power;
    }
}
