package MP.Inventory.Weapon;
import MP.Inventory.Resource;

public class Ranged extends Weapon{
    protected double critDamage;
    protected int tempBasePower;


    public Ranged(String name, int rarity, int basePower, int level) {
        super(name, rarity, basePower, level);
        this.critDamage =1;
        tempBasePower= getTemp();
    }

    public double getCritDamage(){
        return this.critDamage;
    }

    public int getTemp(){
        return (int)Math.round(this.basePower *this.critDamage);
    }

    public void hone(int instance, Resource resource){
        int loop=0;
        if(instance>=1){
            do{
                loop++;
                this.critDamage= critDamage + 0.2;
                resource.subtractResourceValue(10);
            }while(this.critDamage <= 1.8 && instance > loop);
        }
        System.out.println("\nRemaining resource is: " + resource.getResourceValue());
    }
}
