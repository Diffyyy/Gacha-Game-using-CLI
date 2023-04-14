package MP.Inventory.Weapon;

public class Bladed extends Weapon{
    protected int tempPower;

    public Bladed(String name, int rarity, int basePower, int level) {
        super(name, rarity, basePower, level);
        tempPower= getTempPower();
    }

    public int getTempPower(){
        return this.basePower + 10 * this.rarity;
    }

}
