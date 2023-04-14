package MP.Inventory.Weapon;

public class Magical extends Weapon{


    public Magical(String name, int rarity, int basePower, int level) {
        super(name, rarity, basePower, level);
    }

    public int getTempRarity() {
        return this.rarity+1;
    }
}
