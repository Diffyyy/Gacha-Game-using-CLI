package MP.Game;

import MP.Inventory.Weapon.Weapon;
import MP.Inventory.Character;
import MP.Inventory.Resource;

import java.util.ArrayList;

public class Player {

    //Constructors
    ArrayList<Character> characterList= new ArrayList<>();
    ArrayList<Weapon> weaponList= new ArrayList<>();
    Resource refina= new Resource(4000);
    Resource anima= new Resource(7000);



    public void startGame(){
        Menu Menu = new Menu();

        //Start of the game

        //Free 2 characters and weapons
        Menu.freeDraw(characterList, weaponList);
        int menuChoice= Menu.mainMenu();

        //Main Menu of the game
        while(menuChoice != 10){

            //Gacha Machine
            if( menuChoice == 1 ){
                Menu.gachaMenu(characterList, weaponList, refina);
            }
            
            else if(menuChoice==2){
                Menu.viewCharactersMenu(characterList, anima);
            }

            else if(menuChoice==3){
                Menu.viewWeaponsMenu(weaponList, anima);
            }

            //Equip Weapon
            else if (menuChoice==4) {
                Menu.equipMenu(characterList, weaponList);
                Menu.printEquip(characterList);
            }

            //Set Adventure
            else if(menuChoice == 5){
                Menu.mapsMenu(characterList, refina, anima);
            }

            menuChoice= Menu.mainMenu();
        }

        System.out.println("Thank you for playing!");
        Menu.sc.close();
    }

}
