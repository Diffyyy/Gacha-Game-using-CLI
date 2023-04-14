package MP.Inventory.Weapon;


import MP.Inventory.Character;

public class Weapon {

    //Properties
    protected String name;
    protected int rarity;
    protected int basePower;
    protected int level;
    protected Character equippedTo;

    //Methods

    //Name
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }

    //Rarity
    public int getRarity() {
        return this.rarity;
    }
    public void setRarity(int rarity) {
        this.rarity=rarity;
    }

    //Power
    public int getPower() {
        return this.basePower;
    }
    public void setPower(int power) {
        this.basePower =power;
    }

    //Level
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level=level;
    }

    //Get the character of the weapon
    public Character getEquippedTo(){
        return this.equippedTo;
    }

    public void setEquippedTo(Character equippedTo){
        this.equippedTo = equippedTo;
    }

    //Constructor
    public Weapon(String name, int rarity, int basePower, int level) {
        this.setName(name);
        this.setRarity(rarity);
        this.setPower(basePower);
        this.setLevel(level);
        this.setEquippedTo(null); //null means by default, the weapon is not equipped to any character
    }

    public String toString(){
        return String.format("Weapon Name: %-22s Rarity: %-2s Power: %-5s Level: %-3s", this.getName(),
                this.getRarity(), this.getPower(), this.getLevel());
    }

}
