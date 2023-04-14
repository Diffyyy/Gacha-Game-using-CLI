package MP.Inventory;

import MP.Inventory.Weapon.Weapon;

public class Character {
    private String name;
    private int rarity;
    private String element;
    private int level;
    private Weapon weapon; //Index of weapon in the WeaponArray (???); by default -1 value signifying no weapon equipped
    private String type;

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

    //Element
    public String getElement() {
        return this.element;
    }
    public void setElement(String element) {
        this.element=element;
    }

    //Level
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level= level;
    }

    //Weapon equipped by the character
    public Weapon getWeapon(){
        return this.weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon= weapon;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type= type;
    }

    //Constructor
    public Character(String name, int rarity, String element, int level, String type) {
        this.setName(name);
        this.setRarity(rarity);
        this.setElement(element);
        this.setLevel(level);
        this.setWeapon(null); //Initialize index to be null meaning no weapon equipped by default
        this.setType(type);
    }

    public String toString(){
        return String.format("Character Name: %-20s Rarity: %-2s Element: %-8s Level: %-3s", this.getName(),
                this.getRarity(), this.getElement(), this.getLevel());
    }



    //Equips the chosen weapon to the chosen character
    public void equipWeapon(Character character, Weapon weapon) {

        //Set the previous owner of the weapon to null and set the weapon of the previous character to null, if applicable
        if(weapon.getEquippedTo()!=null){
            weapon.getEquippedTo().setWeapon(null);//Sets the weapon of the previous owner to null
        }
        if(character.getWeapon()!=null){
            character.getWeapon().setEquippedTo(null); //Sets the EquippedTo of the previous weapon to null
        }

        //Equip chosen weapon to chosen character
        character.setWeapon(weapon);
        weapon.setEquippedTo(character);
    }

}



