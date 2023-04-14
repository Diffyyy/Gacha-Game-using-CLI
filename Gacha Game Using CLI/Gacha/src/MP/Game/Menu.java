package MP.Game;

import MP.Inventory.Weapon.*;
import MP.Mechanics.Gacha;
import MP.Mechanics.MergeManager;
import MP.Adventure.Map;
import MP.Inventory.Character;
import MP.Inventory.Resource;
import MP.Mechanics.LevellingManager;

import java.util.ArrayList;
import java.util.Scanner;

//UI and Processes/ Operations Class
public class Menu {

    //Property=- choice/ scanner
    Scanner sc;

    //Constructors
    Gacha gacha= new Gacha();
    MergeManager mergeManager= new MergeManager();
    LevellingManager levellingManager = new LevellingManager();

    Map cavern = new Map("Underground Cavern", 53);
    Map forest = new Map("Forest of Enchantments", 77);
    Map sea = new Map("Sea of Hope", 85);
    Map tower = new Map("Tower of Ether", 91);
    Map plane = new Map("Celestial Plane", 100);


    //Method to get user input
    public int getUserInput() {
        sc = new Scanner(System.in);
        return sc.nextInt();
    }


    public void freeDraw(ArrayList<Character> characterList, ArrayList<Weapon> weaponList){
        for(int i=0; i<3; i++){
            characterList.add(gacha.characterPull());
            weaponList.add(gacha.weaponPull());
        }

        System.out.println("You got 2 free characters and 2 free weapons! ");
    }

    //Method for the Main Menu; takes user input and verify it
    public int mainMenu() {

        System.out.println("\nMAIN MENU");
        System.out.println("[1] Gacha Machine");
        System.out.println("[2] View Characters");
        System.out.println("[3] View Weapons");
        System.out.println("[4] Equip Weapon");
        System.out.println("[5] Set Adventure");
        System.out.println("[6] Exit Game");

        int choice;

        do {
            System.out.print("Enter Choice: ");
            choice = getUserInput();
        } while (choice > 10 || choice < 1);

        return choice;
    }

    public void viewCharactersMenu(ArrayList<Character> characterList, Resource resource){
        int choice;
        int input;

        printCharacterList(characterList);
        System.out.println("What do you want to do? Enter 0 to go back to the previous menu");
        do{
            System.out.print("Choose Character: ");
            choice= getUserInput();
        }while (choice > characterList.size() || choice < 0);

        if (choice!=0) {
            do {
                System.out.println("\nOPTIONS");
                System.out.println("[1] Merge Character");
                System.out.println("[2] Level Up Character");
                System.out.print("Input choice: ");
                input= getUserInput();
            } while (input<0 || input>2);

            if (input==1) {
                int choice2;
                int choice3;

                if (characterList.size() >= 3) {
                    do {
                        System.out.print("Choose 2 other characters you want your character to consume: ");
                        choice2 = getUserInput();
                        choice3 = getUserInput();
                    } while (choice > characterList.size() || choice == choice2 || choice2 == choice3);

                    if ((characterList.get(choice - 1).getName().equals(characterList.get(choice2 - 1).getName())) &&
                       (characterList.get(choice2 - 1).getName().equals(characterList.get(choice3 - 1).getName())) &&
                       (characterList.get(choice-1).getRarity()==characterList.get(choice2-1).getRarity()) &&
                       (characterList.get(choice2-1).getRarity()==characterList.get(choice3-1).getRarity()) &&
                       (characterList.get(choice - 1).getRarity()<5)) {
                        mergeManager.mergeCharacter(characterList.get(choice - 1), characterList.get(choice2 - 1), characterList.get(choice3 - 1));
                        System.out.println(characterList.get(choice - 1).getName() + " was empowered. Rarity has been increased by 1! ");
                        characterList.remove(choice2 - 1);
                        characterList.remove(choice3 - 2);
                    }
                    else {
                        System.out.println("Merge Unsuccessful");
                    }
                } else {
                    System.out.println("You need to have at least 3 characters with the same name and rarity to be able to merge");
                }
            }
            else if (input==2) {
                //Ask the user for the amount of resources to be used
                System.out.println("Your anima resource: " + resource.getResourceValue());
                System.out.print("\nInput the amount of anima resources to level up character: ");

                int levelInstance = getUserInput(); //change to getUserInput after fixing the method above

                //Check if the cost is less than the resource
                if (resource.getResourceValue() >= 1 && levelInstance <= resource.getResourceValue()) {
                    levellingManager.levelUpCharacter(resource, characterList.get(choice-1), levelInstance);
                } else {
                    System.out.println("You don't have enough resources to level up character.");
                }
            }
        }
    }

    //Handles the hone and reroll
    public void viewWeaponsMenu(ArrayList<Weapon> weaponList, Resource resource){
       int choice;
       int input;
       int option;

        printWeaponList(weaponList);
        System.out.println("What do you want to do? Enter 0 to go back to the previous menu");
        do{
            System.out.print("Choose a weapon: ");
            choice= getUserInput();
        }while (choice > weaponList.size() || choice < 0);

        //Check if user input is 0
        if(choice !=0){
            do {
                System.out.println("\nOPTIONS");
                System.out.println("[1] Merge Weapon");
                System.out.println("[2] Level Up Weapon");
                System.out.println("[3] Hone Weapon");
                System.out.println("[4] Reroll Weapon");
                System.out.print("Input choice: ");
                option= getUserInput();
            } while (option<0 || option>4);

            //Merge
            if (option==1) {
                int choice2, choice3;
                if (weaponList.size() >= 3) {
                    do {
                        System.out.print("Choose 2 other weapons you want your character to consume: ");
                        choice2 = getUserInput();
                        choice3 = getUserInput();
                    } while (choice > weaponList.size() || choice == choice2 || choice2 == choice3);

                    if ((weaponList.get(choice - 1).getName().equals(weaponList.get(choice2 - 1).getName())) &&
                       (weaponList.get(choice2 - 1).getName().equals(weaponList.get(choice3 - 1).getName())) &&
                       (weaponList.get(choice - 1).getRarity() == weaponList.get(choice2 - 1).getRarity()) &&
                       (weaponList.get(choice2 - 1).getRarity() == weaponList.get(choice3 - 1).getRarity()) &&
                       (weaponList.get(choice - 1).getRarity() < 5)) {
                            mergeManager.mergeWeapon(weaponList.get(choice - 1), weaponList.get(choice2 - 1), weaponList.get(choice3 - 1));
                            System.out.println(weaponList.get(choice - 1).getName() + " was empowered. Rarity has been increased by 1! ");
                            weaponList.remove(choice2 - 1);
                            weaponList.remove(choice3 - 2);
                        } else {
                            System.out.println("Merge Unsuccessful");
                        }
                } else {
                    System.out.println("You need to have at least 3 characters with the same name and rarity to be able to merge");
                }
            }

            //Levelup
            else if (option==2) {
                System.out.println("Your anima resource: " + resource.getResourceValue());
                System.out.print("\nInput the amount of anima resources to level up weapon: ");
                int levelInstance = getUserInput();
                if (resource.getResourceValue() >= 1 && levelInstance <= resource.getResourceValue()) {
                    levellingManager.levelUpWeapon(resource, weaponList.get(choice-1), levelInstance);
                } else {
                    System.out.println("You don't have enough anima resources to level up weapon.");
                }
            }

            //Hone
            else if(option==3){
                if (weaponList.get(choice-1) instanceof Ranged) {
                    do {
                        System.out.print("Enter the amount of anima resource you want to spend on honing (10 resource per 20% increase): ");
                        input = getUserInput();
                    } while (input > resource.getResourceValue() || input / 10 < 1);

                    //While the crit damage does not exceed 2
                    if(((Ranged) weaponList.get(choice - 1)).getCritDamage()<= 1.8)
                        ((Ranged) weaponList.get(choice - 1)).hone(input / 10, resource);
                    else
                        System.out.println("Weapon at max crit damage");

                        System.out.print("The crit damage of " + weaponList.get(choice - 1).getName() + " has been increased to ");
                        System.out.format("%.1f\n", ((Ranged) weaponList.get(choice - 1)).getCritDamage());

                }
                else {
                    System.out.println("Weapon is not of Ranged Type.");
                }
            }

            //Reroll
            else if(option==4){
                //Check if the weapon to be rerolled is equipped
                if (weaponList.get(choice-1) instanceof Golden) {
                    if (weaponList.get(choice - 1).getEquippedTo() != null) {
                        weaponList.get(choice - 1).getEquippedTo().setWeapon(null);
                    }
                    weaponList.set(choice - 1, ((Golden) weaponList.get(choice - 1)).reroll((Golden) weaponList.get(choice - 1), resource));
                }
                else {
                    System.out.println("Weapon is not of Golden Type.");
                }
            }
        }
    }

    //Method for the Gacha Menu; takes user input and verify it; performs necessary tasks/ processes
    public void gachaMenu(ArrayList<Character> characterList, ArrayList<Weapon> weaponList, Resource resource) { // Pull from Gacha
        System.out.println("\nGACHA MENU");
        System.out.println("[1] 1x Character Pull");
        System.out.println("[2] 10x Character Pull");
        System.out.println("[3] 1x Weapon Pull");
        System.out.println("[4] 10x Weapon Pull");
        System.out.println("[5] Back to Main Menu");

        int choice;

        System.out.println("Refina resource: " + resource.getResourceValue());

        do {
            System.out.print("Enter Choice: ");
            choice = getUserInput();
        } while (choice > 5 || choice < 1);


        if (choice == 1 && resource.getResourceValue() >= 300) {
            characterList.add(gacha.characterPull());
            resource.subtractResourceValue(300);
            System.out.println("You spent 300 refina resource. Remaining Refina Resource is: " + resource.getResourceValue());
        }
        else if (choice == 2 && resource.getResourceValue() >= 2700) {
            gacha.characterTenPull(characterList);
            resource.subtractResourceValue(2700);
            System.out.println("You spent 2700 refina resource. Remaining Refina Resource is: " + resource.getResourceValue());
        }
        else if (choice == 3 && resource.getResourceValue() >= 300) {
            weaponList.add(gacha.weaponPull());
        }
        else if (choice == 4 && resource.getResourceValue() >= 2700) {
            gacha.weaponTenPull(weaponList);
            resource.subtractResourceValue(2700);
            System.out.println("You spent 300 refina resource. Remaining Refina Resource is: " + resource.getResourceValue());
        } else if (choice != 5) {
            System.out.println("Insufficient Refina Resource. Deploy your character to adventure to earn more refina resources");
            System.out.println("You spent 2700 refina resource. Remaining Refina Resource is: " + resource.getResourceValue());
        }

    }

    //Menu for equipping weapons to character
    public void equipMenu(ArrayList<Character> characterList, ArrayList<Weapon> weaponList) {

        int choiceWeapon;
        int choiceCharacter;

        printWeaponList(weaponList);


        do {
            System.out.print("Choose which weapon to equip: ");
            choiceWeapon = getUserInput();
        } while (choiceWeapon > weaponList.size() || choiceWeapon < 1);

        printCharacterList(characterList);

        do {
            System.out.print("Choose which character to equip the weapon to: ");
            choiceCharacter = getUserInput();
        } while (choiceCharacter > characterList.size() || choiceCharacter < 1);

        if ((weaponList.get(choiceWeapon-1) instanceof Bladed && characterList.get(choiceCharacter-1).getType().equals("Bladed")) ||
           (weaponList.get(choiceWeapon-1) instanceof Magical && characterList.get(choiceCharacter-1).getType().equals("Magical")) ||
           (weaponList.get(choiceWeapon-1) instanceof Ranged && characterList.get(choiceCharacter-1).getType().equals("Ranged"))) {
            characterList.get(choiceCharacter-1).equipWeapon(characterList.get(choiceCharacter-1), weaponList.get(choiceWeapon-1));
        }
        else{
            System.out.println("Weapon and character not compatible");
        }
    }

    //Method for the Maps Menu; takes user input and verify it
    public void mapsMenu(ArrayList<Character> characterList, Resource refina, Resource anima) { //Choose a map

        System.out.println("\nMAPS MENU");
        System.out.println("[1] Underground Caverns");
        System.out.println("[2] Forest of Enchantments");
        System.out.println("[3] Sea of Hope");
        System.out.println("[4] Tower of Ether");
        System.out.println("[5] Celestial Plane");
        System.out.println("[6] Back to Main Menu");

        int choice;

        do {
            System.out.print("Enter Map choice: ");
            choice = getUserInput();
        } while (choice > 6 || choice < 1);

        if (choice == 1) {
            System.out.println(cavern);
            setAdventure(characterList, refina, anima, cavern);
        } else if (choice == 2) {
            System.out.println(forest);
            setAdventure(characterList, refina, anima, forest);
        } else if (choice == 3) {
            System.out.println(sea);
            setAdventure(characterList, refina, anima, sea);
        } else if (choice == 4) {
            System.out.println(tower);
            setAdventure(characterList, refina, anima, tower);
        } else if (choice == 5) {
            System.out.println(plane);
            setAdventure(characterList, refina, anima, plane);
        }
    }

    //Method that sends 2 characters to an adventure
    public void setAdventure(ArrayList<Character> characterList, Resource refina, Resource anima, Map map) {
        printCharacterList(characterList);
        System.out.println("Select which 2 Characters to Deploy: ");

        int choice1;
        int choice2;

        do {
            System.out.print("Enter player 1: ");
            choice1 = getUserInput();
            System.out.print("Enter player 2: ");
            choice2 = getUserInput();
        } while (choice1 < 0 || choice2 < 0 || choice1 > characterList.size() || choice2 > characterList.size() || choice1 == choice2);

        map.computeSuccess(characterList.get(choice1 - 1), characterList.get(choice2 - 1), refina, anima);

    }

    //Method that prints the character and the weapon equipped IF there is a weapon equipped
    public void printEquip(ArrayList<Character> characterList) {
        for (Character character : characterList) {
            Weapon weapon = character.getWeapon();
            if (weapon != null) {
                System.out.println(character.getName() + " has equipped " +
                        character.getWeapon().getName());
            }
        }
    }

    //Method that prints all the characters owned by the player
    public void printCharacterList(ArrayList<Character> characterList) {
        System.out.println("\nID\tCHARACTER                            RARITY     ELEMENT           LEVEL   ");
        for (int i = 0; i < characterList.size(); i++) {
            System.out.print(i + 1 + "\t");
            System.out.println(characterList.get(i));
        }
    }

    //Method that prints all the weapons owned by the player
    public void printWeaponList(ArrayList<Weapon> weaponList) {
        System.out.println("\nID\tWEAPON                              RARITY     POWER        LEVEL   ");
        for (int i = 0; i < weaponList.size(); i++) {
            System.out.print(i + 1 + "\t");
            System.out.println(weaponList.get(i));
        }
    }

}

