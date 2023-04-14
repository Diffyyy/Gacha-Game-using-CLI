package MP.Mechanics;
import MP.Inventory.Character;
import MP.Inventory.Weapon.*;

import java.util.*;


public class Gacha {

    public Character characterPull(){ //Should return whatever character is pulled

        //Constructors
        Character jekyll = new Character ("Jekyll", 1, "Joker", 20, "Bladed");
        Character earlRobert= new Character ("Earl Robert", 1, "Trigger", 20, "Ranged");
        Character countDArtagnan = new Character ("Count d'Artagnan", 1, "Metal", 20, "Magical");
        Character stede= new Character ("Stede", 1, "Cyclone", 20, "Bladed");
        Character kaguya = new Character ("Kaguya", 1, "Luna", 20, "Ranged");
        Character vanHelmont= new Character ("Van Helmont", 1, "Heat", 20, "Magical");

        Character gray = new Character ("Gray", 2, "Joker", 20, "Magical");
        Character bonney = new Character ("Bonney", 2, "Trigger", 20, "Bladed");
        Character william = new Character ("Sir William Marshal", 2, "Metal", 20, "Ranged");
        Character teach= new Character ("Teach", 2, "Cyclone", 20, "Magical");
        Character jeanne = new Character ("Jeanne", 2, "Luna", 20, "Bladed");
        Character paracelsus= new Character ("Paracelsus", 2, "Heat", 20, "Ranged");

        Character faust = new Character ("Faust", 3, "Joker", 20, "Ranged");
        Character clyde = new Character ("Clyde", 3, "Trigger", 20, "Magical");
        Character masamune = new Character ("Masamune", 3, "Metal", 20, "Bladed");
        Character avery= new Character ("Avery", 3, "Cyclone", 20, "Ranged");
        Character arthur = new Character ("Arthur", 3, "Luna", 20, "Magical");
        Character hermes= new Character ("Hermes", 3, "Heat", 20, "Bladed");


        var multiplier= Math.random();

        if(multiplier < 0.5){
            Character[] characterPool= {jekyll, earlRobert, countDArtagnan, stede, kaguya, vanHelmont};
            int generate= new Random().nextInt(6);
            System.out.println("A " + characterPool[generate].getRarity() + "-star " + characterPool[generate].getName() +
                    " has joined your team!");

            return characterPool[generate];
        }
        else if(multiplier < 0.65 ){
            Character[] characterPool= {gray, bonney, william, teach, jeanne, paracelsus};
            int generate= new Random().nextInt(6);
            System.out.println("A " + characterPool[generate].getRarity() + "-star " + characterPool[generate].getName() +
                    " has joined your team!");

            return characterPool[generate];

        }
        else{
            Character[] characterPool= {faust, clyde, masamune, avery, arthur, hermes};
            int generate= new Random().nextInt(6);
            System.out.println("A " + characterPool[generate].getRarity() + "-star " + characterPool[generate].getName() +
                    " has joined your team!");

            return characterPool[generate];
        }


    }

    //Pull for weapon
    public Weapon weaponPull(){

        //Instantiate all weapons as objects
        Bladed knife= new Bladed("Knife", 1, 130, 1);
        Bladed rapier= new Bladed("Rapier", 1, 140, 1);
        Ranged revolver= new Ranged("Revolver", 1, 150, 1);
        Magical mermaidTears= new Magical("Mermaid Tears", 1, 160, 1);
        Bladed clarent= new Bladed("Clarent", 1, 170, 1);
        Ranged englishLongbow= new Ranged("Longbow", 1, 180, 1);

        Magical circeStaff= new Magical("Circe Staff", 2, 150, 1);
        Magical vorpalSword= new Magical("Vorpal Sword", 2, 160, 1);
        Magical merlinStaff= new Magical("Merlin Staff", 2, 170, 1);
        Bladed fiveCrossSword= new Bladed("Five Cross Sword", 2, 180, 1);
        Ranged bashosen= new Ranged("Bashosen", 2, 190, 1);
        Golden goldenCudgel= new Golden("Golden Cudgel", 2, 200, 1);

        Magical philosopherStone= new Magical("Philosopher's Stone", 3, 180, 1);
        Ranged magicBullets= new Ranged("Magic Bullets", 3, 190, 1);
        Bladed fragarach= new Bladed("Fragarach", 3, 200, 1);
        Bladed honjoMasamune= new Bladed("Honjo Masamune", 3, 210, 1);
        Bladed excalibur= new Bladed("Excalibur", 3, 220, 1);
        Golden scythe= new Golden("Scythe of Father Time", 3, 230, 1);


        var multiplier= Math.random();

        if(multiplier < 0.5){
            Weapon[] weaponPool= {knife, rapier, revolver, mermaidTears, clarent, englishLongbow};
            int generate= new Random().nextInt(6);
            System.out.println("You obtained the power of the " + weaponPool[generate].getRarity() + "-star " +
                    weaponPool[generate].getName() + " !");

            return weaponPool[generate];
        }
        else if(multiplier < 0.65 ){
            Weapon[] weaponPool= {circeStaff, vorpalSword, merlinStaff, fiveCrossSword, bashosen, goldenCudgel};
            int generate= new Random().nextInt(6);
            System.out.println("You obtained the power of the " + weaponPool[generate].getRarity() + "-star " +
                    weaponPool[generate].getName() + " !");

            return weaponPool[generate];
        }
        else{
            Weapon[] weaponPool= {philosopherStone, magicBullets, fragarach, honjoMasamune, excalibur, scythe};
            int generate= new Random().nextInt(6);
            System.out.println("You obtained the power of the " + weaponPool[generate].getRarity() + "-star " +
                    weaponPool[generate].getName() + " !");

            return weaponPool[generate];
        }
    }

    public void characterTenPull(ArrayList<Character> list){
        for(int i=0; i<10; i++){
            list.add(characterPull());
        }
    }

    public void weaponTenPull(ArrayList<Weapon> list){
        for(int i=0; i<10; i++){
            list.add(weaponPull());
        }
    }

}
