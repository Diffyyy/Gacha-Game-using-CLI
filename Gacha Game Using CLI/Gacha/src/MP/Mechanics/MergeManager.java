package MP.Mechanics;

import MP.Inventory.Character;
import MP.Inventory.Weapon.Weapon;

public class MergeManager{


    //Method that merges character
    public void mergeCharacter(Character ch1, Character ch2, Character ch3){

        Weapon weapon2 = ch2.getWeapon();
        Weapon weapon3 = ch3.getWeapon();

//        if(ch1.getName().equals(ch2.getName())
//                && ch2.getName().equals(ch3.getName())
//                && ch1.getRarity() == ch2.getRarity()
//                && ch2.getRarity() == ch3.getRarity()
//                && ch1.getRarity() <5){

            ch1.setRarity(ch1.getRarity()+1);

            //Check if fodders have weapon equipped; if yes, set the equippedTo of the weapons to null
            if(weapon2!=null){
                weapon2.setEquippedTo(null);
            }
            if(weapon3!=null){
                weapon3.setEquippedTo(null);
            }

//        }
//        else{
//            System.out.println("Merge Unsuccessful");
//        }
    }

    //Method that merges the weapon;
    public void mergeWeapon(Weapon wp1, Weapon wp2, Weapon wp3){
        Character ch2= wp2.getEquippedTo();
        Character ch3= wp3.getEquippedTo();

//        if(wp1.getName().equals(wp2.getName())
//                && wp2.getName().equals(wp3.getName())
//                && wp1.getRarity() == wp2.getRarity()
//                && wp2.getRarity() == wp3.getRarity()
//                && wp1.getRarity() <5){

            wp1.setRarity(wp1.getRarity()+1);

            //If the consumed weapon is equipped, set the weapon of the character in which the consumed weapon is equipped to previously to null
            if(ch2!=null){
                ch2.setWeapon(null);
            }
            if(ch3!=null){
                ch3.setWeapon(null);
            }

//        }
//        else{
//            System.out.println("Merge Unsuccessful");
//        }
    }

}
