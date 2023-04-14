package MP.Adventure;
import MP.Inventory.Character;
import MP.Inventory.Weapon.Bladed;
import MP.Inventory.Weapon.Magical;
import MP.Inventory.Weapon.Ranged;
import MP.Inventory.Resource;

import java.util.ArrayList;

public class Map {
    private String name;
    private int baseAmount;
    private final ArrayList <Enemy> mapEnemy;

    //Methods
    //Name
    public void setName(String name) {
        this.name=name;
    }

    //baseAmount
    public void setBaseAmount(int baseAmount) {
        this.baseAmount=baseAmount;
    }

    //Constructor
    public Map (String name, int baseAmount) {
        this.setName(name);
        this.setBaseAmount(baseAmount);
        this.mapEnemy= new ArrayList<>();
        enemyList(name);
    }

    public String toString(){
        return "\nWelcome to the " + name + " \nBase Amount: " + baseAmount+ " Resources";
    }

    //Sets the enemies depending on the map
    private void enemyList (String name) {
        Enemy slime = new Enemy("Slime", 73);
        Enemy orc = new Enemy("Orc", 84);
        Enemy familiar = new Enemy("Familiar", 144);
        Enemy faerie = new Enemy("Faerie", 175);
        Enemy elf = new Enemy("Elf", 224);
        Enemy sorcerer = new Enemy("Sorcerer", 313);
        Enemy hydra = new Enemy("Hydra", 360);
        Enemy basilisk = new Enemy("Basilisk", 499);
        Enemy harpy = new Enemy("Harpy", 639);
        Enemy loki = new Enemy("Loki", 740);

        switch (name) {
            case "Underground Cavern" -> {
                this.mapEnemy.add(faerie);
                for (int i = 1; i < 7; i++)
                    this.mapEnemy.add(slime);
            }
            case "Forest of Enchantments" -> {
                for (int i = 0; i < 5; i++)
                    this.mapEnemy.add(slime);
                for (int i = 5; i < 10; i++)
                    this.mapEnemy.add(orc);
                for (int i = 10; i < 13; i++)
                    this.mapEnemy.add(familiar);
                for (int i = 13; i < 16; i++)
                    this.mapEnemy.add(faerie);
                for (int i = 16; i < 18; i++)
                    this.mapEnemy.add(elf);
                this.mapEnemy.add(sorcerer);
            }
            case "Sea of Hope" -> {
                for (int i = 0; i < 75; i++)
                    this.mapEnemy.add(slime);
                for (int i = 75; i < 95; i++)
                    this.mapEnemy.add(sorcerer);
                for (int i = 95; i < 100; i++)
                    this.mapEnemy.add(hydra);
            }
            case "Tower of Ether" -> {
                for (int i = 0; i < 20; i++)
                    this.mapEnemy.add(basilisk);
                for (int i = 20; i < 27; i++)
                    this.mapEnemy.add(harpy);
                for (int i = 27; i < 32; i++)
                    this.mapEnemy.add(loki);
            }
            case "Celestial Plane" -> {
                for (int i = 0; i < 50; i++)
                    this.mapEnemy.add(faerie);
                for (int i = 50; i < 70; i++)
                    this.mapEnemy.add(hydra);
                for (int i = 70; i < 80; i++)
                    this.mapEnemy.add(loki);
            }
        }
    }

    public void computeSuccess (Character character1, Character character2, Resource refina, Resource anima){
        /*Adventure is successful if Character Superiority > Enemy Superiority
        Character_Superiority = Total_Final_Weapon_Power * (Total_Character_Influence /10)
        Total Final Weapon Power = Power of weapon* Rarity Multiplier of weapon + Level of weapon
        CharacterInfluence=Character Level * (1 + ((Character Rarity - 1) / 5))
        Enemy Superiority = Sum of all Enemies;*/

        int enemySuperiority=0;

        if (character1.getWeapon()!=null && character1.getWeapon()!=null) {
            int basePower1= character1.getWeapon().getPower();
            int basePower2= character2.getWeapon().getPower();


            if(character1.getWeapon() instanceof Ranged)
                basePower1= ((Ranged) character1.getWeapon()).getTemp();
            else if(character1.getWeapon() instanceof Bladed)
                basePower1= ((Bladed) character1.getWeapon()).getTempPower();


            if(character2.getWeapon() instanceof Ranged)
                basePower2= ((Ranged) character2.getWeapon()).getTemp();
            else if(character1.getWeapon() instanceof Bladed)
                basePower2= ((Bladed) character2.getWeapon()).getTempPower();


            System.out.println("BasePower1: " +basePower1);
            System.out.println("BasePower2: " +basePower2);



            //Computations
            double totalFinalWeaponPower = (basePower1 * weaponRarityMultiplier(character1)
                    + character1.getWeapon().getLevel()) + (basePower2 * weaponRarityMultiplier(character2)
                    + character2.getWeapon().getLevel());
            double rarity1 = character1.getRarity();
            double rarity2 = character2.getRarity();

            double characterInfluence = (character1.getLevel() * (1 + (rarity1 - 1) / 5)) +
                    (character2.getLevel() * (1 + (rarity2 - 1) / 5));

            double characterSuperiority = totalFinalWeaponPower * (characterInfluence/10);

            System.out.println("You deployed " + character1.getName() + " with weapon " + character1.getWeapon().getName() + " and " +
                    character2.getName() + " with weapon " + character2.getWeapon().getName() + " on an adventure.");


            for (Enemy enemy : mapEnemy) {
                enemySuperiority += enemy.getPower();
            }
            isSuccess(characterSuperiority, enemySuperiority, character1, character2);

            double totalResources= (this.baseAmount + (totalFinalWeaponPower/24) * (characterInfluence/36)) * rewardsMultiplier(character1, character2);
            int intTotal= (int)totalResources; //Convert double to int
            double animaResources = totalResources*0.70;
            int intTotal2= (int)animaResources;
            System.out.println("you gained " + intTotal + " refina resources.");
            refina.addResourceValue(intTotal);
            System.out.println("you gained " + intTotal2 + " anima resources.");
            anima.addResourceValue(intTotal2);

            System.out.println("TOTAL REFINA: "+ refina.getResourceValue());
            System.out.println("TOTAL ANIMA: "+ anima.getResourceValue());
        }
        else {
            System.out.println("You need to equip weapon first before setting an adventure.");
        }
    }

    private void isSuccess (double characterSuperiority, int enemySuperiority, Character character1, Character character2) {
        if (characterSuperiority>=(enemySuperiority*1.5)) {
            System.out.println("*The adventure was excellently completed!*");
            character1.setLevel(Math.min(character1.getLevel() + 2, 100));
            character2.setLevel(Math.min(character2.getLevel() + 2, 100));
        }
        else if (characterSuperiority > enemySuperiority) {
            System.out.println("*The adventure was successfully completed!*");
            character1.setLevel(Math.min(character1.getLevel() + 1, 100));
            character2.setLevel(Math.min(character2.getLevel() + 1, 100));
        }
        else{
            System.out.println("*The adventure was not successfully completed.*");
        }
    }

    private double weaponRarityMultiplier (Character character) {

        //Until 6- rarity for magical weapons
        double multiplier = 1.5;
        int rarity;

        //Checks first if the weapon is magical or not
        if(character.getWeapon() instanceof Magical) {
            rarity= ((Magical) character.getWeapon()).getTempRarity();
        }
        else {
            rarity= character.getWeapon().getRarity();
        }


        //1-rarity
        if (rarity == 1) {
            multiplier = 0.7;
        }

        //2-rarity
        else if (rarity == 2) {
            multiplier = 0.8;
        }

        //3-rarity
        else if (rarity == 3) {
            multiplier = 0.9;
        }

        //4-rarity
        else if (rarity == 4) {
            multiplier = 1.0;
        }

        //5-rarity
        else if (rarity == 5) {
            multiplier = 1.2;
        }

        return multiplier;
    }

    private double rewardsMultiplier (Character character1, Character character2){

        double multiplier = 1;

        //Normal Pairing
        if (character1.getElement().equals(character2.getElement())) {
            multiplier = 1.1;
        }

        //Perfect Pairing
        else if (((character1.getElement().equals("Cyclone") && character2.getElement().equals("Joker")) ||
                (character1.getElement().equals("Joker") && character2.getElement().equals("Cyclone"))) ||
                ((character1.getElement().equals("Luna") && character2.getElement().equals("Trigger")) ||
                (character1.getElement().equals("Trigger") && character2.getElement().equals("Luna"))) ||
                ((character1.getElement().equals("Metal") && character2.getElement().equals("Heat")) ||
                (character1.getElement().equals("Heat") && character2.getElement().equals("Metal"))))  {
            multiplier = 1.75;
        }

        //Nice Pairing
        else if (((character1.getElement().equals("Luna") && character2.getElement().equals("Joker")) ||
                (character1.getElement().equals("Joker") && character2.getElement().equals("Luna"))) ||
                ((character1.getElement().equals("Heat") && character2.getElement().equals("Joker")) ||
                (character1.getElement().equals("Joker") && character2.getElement().equals("Heat")))) {
            multiplier = 1.5;
        }

        //Decent Pairing
        else if (((character1.getElement().equals("Trigger") && character2.getElement().equals("Joker")) ||
                (character1.getElement().equals("Joker") && character2.getElement().equals("Trigger"))) ||
                ((character1.getElement().equals("Metal") && character2.getElement().equals("Cyclone")) ||
                (character1.getElement().equals("Cyclone") && character2.getElement().equals("Metal"))) ||
                ((character1.getElement().equals("Metal") && character2.getElement().equals("Joker")) ||
                (character1.getElement().equals("Joker") && character2.getElement().equals("Metal"))) ||
                ((character1.getElement().equals("Metal") && character2.getElement().equals("Luna")) ||
                (character1.getElement().equals("Luna") && character2.getElement().equals("Metal")))) {
            multiplier = 1.25;
        }

        //Bad Pairing
        else if (((character1.getElement().equals("Cyclone") && character2.getElement().equals("Luna")) ||
                (character1.getElement().equals("Luna") && character2.getElement().equals("Cyclone"))) ||
                ((character1.getElement().equals("Luna") && character2.getElement().equals("Heat")) ||
                (character1.getElement().equals("Heat") && character2.getElement().equals("Luna"))) ||
                ((character1.getElement().equals("Cyclone") && character2.getElement().equals("Heat")) ||
                (character1.getElement().equals("Heat") && character2.getElement().equals("Cyclone")))) {
            multiplier = 0.75;
        }

        return multiplier;
    }
}