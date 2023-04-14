package MP.Mechanics;

import MP.Inventory.Character;
import MP.Inventory.Weapon.Weapon;
import MP.Inventory.Resource;

public class LevellingManager {

    //Methods to perform level up task for character
    public void levelUpCharacter(Resource resource, Character character, int levelInstance){
        if (character.getLevel()+levelInstance <= 100) {
            resource.subtractResourceValue(levelInstance);
            character.setLevel(character.getLevel() + levelInstance);
            System.out.println("You successfully levelled up " + character.getName() +
                    ". Remaining resource is: " + resource.getResourceValue());
        }
        else {
            resource.subtractResourceValue(100- character.getLevel());
            character.setLevel(100);
            System.out.println(character.getName()+" has reached the Max level" +
                    ". Remaining resource is: " + resource.getResourceValue() );
        }
    }

    //Methods to perform level up task for weapon
    public void levelUpWeapon(Resource resource, Weapon weapon, int levelInstance) {
        if (weapon.getLevel()+levelInstance <= 50) {
            resource.subtractResourceValue(levelInstance);
            weapon.setLevel(weapon.getLevel() + levelInstance);
            System.out.println("You successfully levelled up " + weapon.getName() +
                    ". Remaining resource is: " + resource.getResourceValue());
        }
        else {
            resource.subtractResourceValue(50- weapon.getLevel());
            weapon.setLevel(50);
            System.out.println(weapon.getName()+" has reached the Max level" +
                    ". Remaining resource is: " + resource.getResourceValue() );
        }
    }
}
