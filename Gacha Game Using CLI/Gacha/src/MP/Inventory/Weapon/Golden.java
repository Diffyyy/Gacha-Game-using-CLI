package MP.Inventory.Weapon;

import MP.Inventory.Resource;

public class Golden extends Bladed{

    public Golden(String name, int rarity, int basePower, int level) {
        super(name, rarity, basePower, level);
    }

    public Golden reroll(Golden weapon, Resource resource){
        Golden cudgel= new Golden("Golden Cudgel", 2, 200, 1);
        Golden scythe= new Golden("Scythe of Father Time", 3, 230, 1);
        resource.subtractResourceValue(150);
        System.out.println("\nRemaining resource is: " + resource.getResourceValue());

        if(weapon.name.equals("Golden Cudgel"))
            return scythe;
        else
            return cudgel;
    }
}
